package s3526603.waterreminderapp

import android.content.Context
import androidx.work.Worker
import androidx.work.WorkerParameters

class ReminderWorker(
    context: Context,
    workerParams: WorkerParameters
) : Worker(context, workerParams) {

    override fun doWork(): Result {
        NotificationHelper.showNotification(applicationContext)
        return Result.success()
    }
}