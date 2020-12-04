package com.jdemaagd.ciclodavida;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    private static final String TAG = MainActivity.class.getSimpleName();

    private static final String LIFECYCLE_CALLBACKS_TEXT_KEY = "callbacks";

    private static final String ON_CREATE = "onCreate";
    private static final String ON_START = "onStart";
    private static final String ON_RESUME = "onResume";
    private static final String ON_PAUSE = "onPause";
    private static final String ON_STOP = "onStop";
    private static final String ON_RESTART = "onRestart";
    private static final String ON_DESTROY = "onDestroy";
    private static final String ON_SAVE_INSTANCE_STATE = "onSaveInstanceState";

    private TextView mLifecycleDisplay;

    private static final ArrayList<String> mLifecycleCallbacks = new ArrayList<>();

    /**
     * Called when the activity is first created
     * This is where you should do all of your normal static set up:
     *      create views, bind data to lists, etc.
     *
     * Always followed by onStart()
     *
     * @param savedInstanceState The Activity's previously frozen state, if there was one
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mLifecycleDisplay = (TextView) findViewById(R.id.tv_lifecycle_events_display);

        if (savedInstanceState != null) {
            if (savedInstanceState.containsKey(LIFECYCLE_CALLBACKS_TEXT_KEY)) {
                String allPreviousLifecycleCallbacks = savedInstanceState
                        .getString(LIFECYCLE_CALLBACKS_TEXT_KEY);
                mLifecycleDisplay.setText(allPreviousLifecycleCallbacks);
            }
        }

        for (int i = mLifecycleCallbacks.size() - 1; i >= 0; i--) {
            mLifecycleDisplay.append(mLifecycleCallbacks.get(i) + "\n");
        }

        mLifecycleCallbacks.clear();

        logAndAppend(ON_CREATE);
    }

    /**
     * Called when activity is becoming visible to user
     *
     * Followed by onResume() if activity comes to foreground, or onStop() if it becomes hidden
     */
    @Override
    protected void onStart() {
        super.onStart();

        logAndAppend(ON_START);
    }

    /**
     * Called when activity will start interacting with user
     * At this point activity is at top of activity stack, with user input going to it
     *
     * Always followed by onPause()
     */
    @Override
    protected void onResume() {
        super.onResume();

        logAndAppend(ON_RESUME);
    }

    /**
     * Called when system is about to start resuming a previous activity
     * Typically used to commit unsaved changes to persistent data,
     *      stop animations and other things that may be consuming CPU, etc.
     * Implementations of this method must be very quick
     *      because the next activity will not be resumed until this method returns
     *
     * Followed by either onResume() if activity returns back to front, or onStop() if it
     * becomes invisible to user
     */
    @Override
    protected void onPause() {
        super.onPause();

        logAndAppend(ON_PAUSE);
    }

    /**
     * Called when activity is no longer visible to user,
     *      because another activity has been resumed and is covering this one
     * This may happen either because a new activity is being started,
     *      an existing one is being brought in front of this one,
     *      or this one is being destroyed
     *
     * Followed by either onRestart() if this activity is coming back to interact with the user,
     *      or onDestroy() if this activity is going away
     */
    @Override
    protected void onStop() {
        super.onStop();

        mLifecycleCallbacks.add(0, ON_STOP);

        logAndAppend(ON_STOP);
    }

    /**
     * Called after activity has been stopped, prior to it being started again
     *
     * Always followed by onStart()
     */
    @Override
    protected void onRestart() {
        super.onRestart();

        logAndAppend(ON_RESTART);
    }

    /**
     * Called before activity is destroyed
     * This can happen either because activity is finishing (someone called finish() on it,
     *      or because system is temporarily destroying this instance of activity to save space
     * You can distinguish between these two scenarios with isFinishing() method
     */
    @Override
    protected void onDestroy() {
        super.onDestroy();

        mLifecycleCallbacks.add(0, ON_DESTROY);

        logAndAppend(ON_DESTROY);
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        logAndAppend(ON_SAVE_INSTANCE_STATE);

        String lifecycleDisplayTextViewContents = mLifecycleDisplay.getText().toString();
        outState.putString(LIFECYCLE_CALLBACKS_TEXT_KEY, lifecycleDisplayTextViewContents);
    }

    /**
     * Logs to console and appends lifecycle method name to TextView
     *      so that you can view the series of method callbacks that are called
     *      both from the app and from within Android Studio Logcat
     *
     * @param lifecycleEvent The name of the event to be logged
     */
    private void logAndAppend(String lifecycleEvent) {
        Log.d(TAG, "Lifecycle Event: " + lifecycleEvent);

        mLifecycleDisplay.append(lifecycleEvent + "\n");
    }

    /**
     * Resets contents of TextView to its default text of "Lifecycle callbacks"
     *
     * @param view The View that was clicked
     */
    public void resetLifecycleDisplay(View view) {
        mLifecycleDisplay.setText("Lifecycle callbacks:\n");
    }
}