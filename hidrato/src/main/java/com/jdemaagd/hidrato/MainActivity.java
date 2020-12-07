package com.jdemaagd.hidrato;

import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.content.SharedPreferences;
import android.os.BatteryManager;
import android.os.Build;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.jdemaagd.hidrato.sync.ReminderTasks;
import com.jdemaagd.hidrato.sync.WaterReminderIntentService;
import com.jdemaagd.hidrato.utils.PreferenceUtils;
import com.jdemaagd.hidrato.utils.ReminderUtils;

public class MainActivity extends AppCompatActivity implements
        SharedPreferences.OnSharedPreferenceChangeListener {

    private TextView mWaterCountDisplay;
    private TextView mChargingCountDisplay;
    private ImageView mChargingImageView;

    private Toast mToast;

    ChargingBroadcastReceiver mChargingReceiver;
    IntentFilter mChargingIntentFilter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mWaterCountDisplay = findViewById(R.id.tv_water_count);
        mChargingCountDisplay = findViewById(R.id.tv_charging_reminder_count);
        mChargingImageView = findViewById(R.id.iv_power_increment);

        updateWaterCount();
        updateChargingReminderCount();
        ReminderUtils.scheduleChargingReminder(this);

        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.registerOnSharedPreferenceChangeListener(this);

        mChargingIntentFilter = new IntentFilter();
        mChargingReceiver = new ChargingBroadcastReceiver();
        mChargingIntentFilter.addAction(Intent.ACTION_POWER_CONNECTED);
        mChargingIntentFilter.addAction(Intent.ACTION_POWER_DISCONNECTED);
    }

    /**
     * Listener for preference changes on water count or charging reminder counts
     */
    @Override
    public void onSharedPreferenceChanged(SharedPreferences sharedPreferences, String key) {
        if (PreferenceUtils.KEY_WATER_COUNT.equals(key)) {
            updateWaterCount();
        } else if (PreferenceUtils.KEY_CHARGING_REMINDER_COUNT.equals(key)) {
            updateChargingReminderCount();
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        SharedPreferences prefs = PreferenceManager.getDefaultSharedPreferences(this);
        prefs.unregisterOnSharedPreferenceChangeListener(this);
    }

    @Override
    protected void onPause() {
        super.onPause();
        unregisterReceiver(mChargingReceiver);
    }

    @Override
    protected void onResume() {
        super.onResume();

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {  // M+ (Marshmallow)
            BatteryManager batteryManager = (BatteryManager) getSystemService(BATTERY_SERVICE);
            showCharging(batteryManager.isCharging());
        } else {
            // Sticky broadcast that contains information about battery state
            IntentFilter ifilter = new IntentFilter(Intent.ACTION_BATTERY_CHANGED);

            // Passing in null gets current state of a sticky broadcast
            // intent returned will contain battery information you need
            Intent currentBatteryStatusIntent = registerReceiver(null, ifilter);

            // Get BatteryManager.EXTRA_STATUS state
            // Check against BatteryManager.BATTERY_STATUS_CHARGING or BatteryManager.BATTERY_STATUS_FULL
            int batteryStatus = currentBatteryStatusIntent.getIntExtra(BatteryManager.EXTRA_STATUS, -1);
            boolean isCharging = batteryStatus == BatteryManager.BATTERY_STATUS_CHARGING ||
                    batteryStatus == BatteryManager.BATTERY_STATUS_FULL;

            showCharging(isCharging);
        }

        registerReceiver(mChargingReceiver, mChargingIntentFilter);
    }

    public void incrementWater(View view) {
        if (mToast != null) mToast.cancel();
        mToast = Toast.makeText(this, R.string.water_chug_toast, Toast.LENGTH_SHORT);
        mToast.show();

        Intent incrementWaterCountIntent = new Intent(this, WaterReminderIntentService.class);
        incrementWaterCountIntent.setAction(ReminderTasks.ACTION_INCREMENT_WATER_COUNT);
        startService(incrementWaterCountIntent);
    }

    // Update UI when our broadcast receiver is triggered when charging state changes
    private void showCharging(boolean isCharging){
        if (isCharging) {
            mChargingImageView.setImageResource(R.drawable.ic_power_pink_80px);

        } else {
            mChargingImageView.setImageResource(R.drawable.ic_power_grey_80px);
        }
    }

    /**
     * Update TextView to display new charging reminder count from SharedPreferences
     */
    private void updateChargingReminderCount() {
        int chargingReminders = PreferenceUtils.getChargingReminderCount(this);
        String formattedChargingReminders = getResources().getQuantityString(
                R.plurals.charge_notification_count, chargingReminders, chargingReminders);
        mChargingCountDisplay.setText(formattedChargingReminders);
    }

    /**
     * Update TextView to display new water count from SharedPreferences
     */
    private void updateWaterCount() {
        int waterCount = PreferenceUtils.getWaterCount(this);
        mWaterCountDisplay.setText(waterCount+"");
    }

    private class ChargingBroadcastReceiver extends BroadcastReceiver {
        @Override
        public void onReceive(Context context, Intent intent) {
            String action = intent.getAction();
            boolean isCharging = (action.equals(Intent.ACTION_POWER_CONNECTED));

            showCharging(isCharging);
        }
    }
}