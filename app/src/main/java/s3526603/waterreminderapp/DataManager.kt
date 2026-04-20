package s3526603.waterreminderapp

import android.content.Context

object DataManager {

    private const val PREF_NAME = "water_app_prefs"
    private const val KEY_GOAL = "goal"
    private const val KEY_INTERVAL = "interval"
    private const val KEY_NOTIFICATIONS = "notifications_enabled"
    private const val KEY_INTAKE = "intake"

    fun saveGoal(context: Context, goal: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_GOAL, goal).apply()
    }

    fun getGoal(context: Context): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_GOAL, 2000)
    }

    fun saveInterval(context: Context, interval: String) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putString(KEY_INTERVAL, interval).apply()
    }

    fun getInterval(context: Context): String {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getString(KEY_INTERVAL, "2 hours") ?: "2 hours"
    }

    fun saveNotificationsEnabled(context: Context, enabled: Boolean) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putBoolean(KEY_NOTIFICATIONS, enabled).apply()
    }

    fun getNotificationsEnabled(context: Context): Boolean {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getBoolean(KEY_NOTIFICATIONS, true)
    }

    fun saveIntake(context: Context, intake: Int) {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        prefs.edit().putInt(KEY_INTAKE, intake).apply()
    }

    fun getIntake(context: Context): Int {
        val prefs = context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return prefs.getInt(KEY_INTAKE, 0)
    }
}
