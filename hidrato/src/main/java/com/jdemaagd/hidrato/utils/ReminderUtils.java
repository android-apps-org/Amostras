package com.jdemaagd.hidrato.utils;

import android.content.Context;
import android.util.Log;

import androidx.annotation.NonNull;

import com.firebase.jobdispatcher.Constraint;
import com.firebase.jobdispatcher.Driver;
import com.firebase.jobdispatcher.FirebaseJobDispatcher;
import com.firebase.jobdispatcher.GooglePlayDriver;
import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.Lifetime;
import com.firebase.jobdispatcher.Trigger;
import com.jdemaagd.hidrato.sync.WaterReminderFirebaseJobService;

import java.util.concurrent.TimeUnit;

public class ReminderUtils {

    private static final String LOG_TAG = ReminderUtils.class.getSimpleName();

    //  - REMINDER_INTERVAL_SECONDS should be an integer constant storing the number of seconds in 15 minutes
    //  - SYNC_FLEXTIME_SECONDS should also be an integer constant storing the number of seconds in 15 minutes
    //  - REMINDER_JOB_TAG should be a String constant, storing something like "hydration_reminder_tag"
    private static final int REMINDER_INTERVAL_MINUTES = 1;
    private static final int REMINDER_INTERVAL_SECONDS =
            (int) (TimeUnit.MINUTES.toSeconds(REMINDER_INTERVAL_MINUTES));
    private static final int SYNC_FLEXTIME_SECONDS = REMINDER_INTERVAL_SECONDS;

    private static final String REMINDER_JOB_TAG = "hydration_reminder_tag";

    private static boolean sInitialized;

    // Schedule job via FirebaseJobDispatcher that repeats every REMINDER_INTERVAL_SECONDS
    // Triggers WaterReminderFirebaseJobService:
    // https://github.com/firebase/firebase-jobdispatcher-android
    synchronized public static void scheduleChargingReminder(@NonNull final Context context) {
        if (sInitialized) return;

        Log.i(LOG_TAG, "scheduleChargingReminder called");

        Driver driver = new GooglePlayDriver(context);
        FirebaseJobDispatcher dispatcher = new FirebaseJobDispatcher(driver);

        Job constraintReminderJob = dispatcher.newJobBuilder()
                .setService(WaterReminderFirebaseJobService.class)
                .setTag(REMINDER_JOB_TAG)
                .setConstraints(Constraint.DEVICE_CHARGING)
                .setLifetime(Lifetime.FOREVER)
                .setRecurring(true)
                .setTrigger(Trigger.executionWindow(
                        REMINDER_INTERVAL_SECONDS,
                        REMINDER_INTERVAL_SECONDS + SYNC_FLEXTIME_SECONDS))
                .setReplaceCurrent(true)
                .build();

        dispatcher.schedule(constraintReminderJob);

        Log.i(LOG_TAG, "Job scheduled in scheduleChargingReminder.");

        sInitialized = true;
    }
}