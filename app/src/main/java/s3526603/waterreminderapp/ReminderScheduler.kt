package s3526603.waterreminderapp

import android.content.Context
import androidx.work.ExistingPeriodicWorkPolicy
import androidx.work.PeriodicWorkRequestBuilder
import androidx.work.WorkManager
import java.util.concurrent.TimeUnit

object ReminderScheduler {

    private const val WORK_NAME = "water_reminder_work"

    fun scheduleReminder(context: Context, intervalMinutes: Long) {
        val workRequest =
            PeriodicWorkRequestBuilder<ReminderWorker>(intervalMinutes, TimeUnit.MINUTES)
                .build()

        WorkManager.getInstance(context).enqueueUniquePeriodicWork(
            WORK_NAME,
            ExistingPeriodicWorkPolicy.UPDATE,
            workRequest
        )
    }

    fun cancelReminder(context: Context) {
        WorkManager.getInstance(context).cancelUniqueWork(WORK_NAME)
    }
}