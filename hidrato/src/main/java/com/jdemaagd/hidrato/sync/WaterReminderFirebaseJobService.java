package com.jdemaagd.hidrato.sync;

import android.content.Context;
import android.os.AsyncTask;
import android.util.Log;

import com.firebase.jobdispatcher.Job;
import com.firebase.jobdispatcher.JobParameters;
import com.firebase.jobdispatcher.JobService;
import com.firebase.jobdispatcher.RetryStrategy;

public class WaterReminderFirebaseJobService extends JobService {

    private static final String LOG_TAG = WaterReminderFirebaseJobService.class.getSimpleName();

    private AsyncTask mBackgroundTask;

    /**
     * Implementations should offload work to another thread of execution ASAP
     * <p>
     * Called by Job Dispatcher to notify to start our job
     * Keep in mind this is run on app main thread so offload work to background thread
     *
     * @return whether there is more work remaining
     */
    @Override
    public boolean onStartJob(final JobParameters jobParameters) {

        mBackgroundTask = new AsyncTask() {

            @Override
            protected Object doInBackground(Object[] params) {
                Context context = WaterReminderFirebaseJobService.this;
                ReminderTasks.executeTask(context, ReminderTasks.ACTION_CHARGING_REMINDER);

                Log.i(LOG_TAG, "background task started in onStartJob.doInBackground");

                return null;
            }

            @Override
            protected void onPostExecute(Object o) {
                Log.i(LOG_TAG, "background task finishes in onStartJob.onPostExecute.");
                jobFinished(jobParameters, false);
            }
        };

        mBackgroundTask.execute();

        Log.i(LOG_TAG, "background task executed in onStartJob.");

        return true;
    }

    /**
     * Called when scheduling engine has decided to interrupt execution of running job
     * most likely because runtime constraints associated with job are no longer satisfied
     *
     * @return whether the job should be retried
     * @see Job.Builder#setRetryStrategy(RetryStrategy)
     * @see RetryStrategy
     */
    @Override
    public boolean onStopJob(JobParameters jobParameters) {
        if (mBackgroundTask != null) mBackgroundTask.cancel(true);

        return true;
    }
}