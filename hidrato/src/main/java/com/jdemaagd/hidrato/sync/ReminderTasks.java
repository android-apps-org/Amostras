package com.jdemaagd.hidrato.sync;

import android.content.Context;

import com.jdemaagd.hidrato.utils.NotificationUtils;
import com.jdemaagd.hidrato.utils.PreferenceUtils;

public class ReminderTasks {

    public static final String ACTION_INCREMENT_WATER_COUNT = "increment-water-count";
    public static final String ACTION_DISMISS_NOTIFICATION = "dismiss-notification";

    public static void executeTask(Context context, String action) {
        if (ACTION_INCREMENT_WATER_COUNT.equals(action)) {
            incrementWaterCount(context);
        } else if (ACTION_DISMISS_NOTIFICATION.equals(action)) {
            NotificationUtils.clearAllNotifications(context);
        }
    }

    private static void incrementWaterCount(Context context) {
        PreferenceUtils.incrementWaterCount(context);
        NotificationUtils.clearAllNotifications(context);
    }
}