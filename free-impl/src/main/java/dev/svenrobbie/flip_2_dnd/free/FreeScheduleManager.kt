package dev.svenrobbie.flip_2_dnd.free

import android.content.Context
import android.util.Log
import dev.svenrobbie.flip_2_dnd.core.ScheduleManager
import java.util.Calendar

class FreeScheduleManager(context: Context) : ScheduleManager {
    private val TAG = "FreeScheduleManager"

    override fun isWithinSchedule(startTime: String, endTime: String, days: Set<Int>): Boolean {
        return try {
            val now = Calendar.getInstance()
            val currentDayOfWeek = (now.get(Calendar.DAY_OF_WEEK) + 5) % 7

            if (days.isNotEmpty() && currentDayOfWeek !in days) {
                Log.d(TAG, "Current day not in schedule days")
                return false
            }

            val currentMinutes = now.get(Calendar.HOUR_OF_DAY) * 60 + now.get(Calendar.MINUTE)

            val startParts = startTime.split(":")
            val endParts = endTime.split(":")
            val startMinutes = startParts[0].toInt() * 60 + startParts[1].toInt()
            val endMinutes = endParts[0].toInt() * 60 + endParts[1].toInt()

            if (startMinutes <= endMinutes) {
                currentMinutes in startMinutes until endMinutes
            } else {
                currentMinutes >= startMinutes || currentMinutes < endMinutes
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error checking schedule: ${e.message}")
            true
        }
    }
}
