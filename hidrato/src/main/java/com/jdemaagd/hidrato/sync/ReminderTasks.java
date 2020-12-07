package com.jdemaagd.hidrato.sync;

import android.content.Context;
import android.util.Log;

import com.jdemaagd.hidrato.utils.NotificationUtils;
import com.jdemaagd.hidrato.utils.PreferenceUtils;

public class ReminderTasks {

    private static final String LOG_TAG = ReminderTasks.class.getSimpleName();

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";
    static final String ACTION_CHARGING_REMINDER = "charging-reminder";

    public static void executeTask(Context context, String action) {
        Log.i(LOG_TAG, "ExecuteTask reminder called");

        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        } else if (ACTION_CHARGING_REMINDER.equals(action)) {
            issueChargingReminder(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        PreferenceUtils.incrementWaterCount(context);
        NotificationUtils.clearAllNotifications(context);
    }

    private static void issueChargingReminder(Context context) {
        PreferenceUtils.incrementChargingReminderCount(context);
        NotificationUtils.remindUserBecauseCharging(context);
    }
}