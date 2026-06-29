package dev.svenrobbie.flip_2_dnd.free

import android.content.Context
import android.content.Intent
import dev.svenrobbie.flip_2_dnd.core.SoundPicker

class FreeSoundPicker(private val context: Context) : SoundPicker {
    override fun launchPicker(context: Context, isDndOn: Boolean) {
        val intent = Intent(Intent.ACTION_OPEN_DOCUMENT).apply {
            addCategory(Intent.CATEGORY_OPENABLE)
            type = "audio/*"
            flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_GRANT_READ_URI_PERMISSION
            putExtra(Intent.EXTRA_ALLOW_MULTIPLE, false)
        }
        try {
            context.startActivity(intent)
        } catch (e: Exception) {
            // Fallback to ringtone picker
            val fallbackIntent = Intent(
                android.media.RingtoneManager.ACTION_RINGTONE_PICKER
            ).apply {
                flags = Intent.FLAG_ACTIVITY_NEW_TASK
                putExtra(
                    android.media.RingtoneManager.EXTRA_RINGTONE_TYPE,
                    android.media.RingtoneManager.TYPE_NOTIFICATION
                )
                putExtra(android.media.RingtoneManager.EXTRA_RINGTONE_SHOW_DEFAULT, false)
                putExtra(android.media.RingtoneManager.EXTRA_RINGTONE_SHOW_SILENT, false)
            }
            context.startActivity(fallbackIntent)
        }
    }
}
