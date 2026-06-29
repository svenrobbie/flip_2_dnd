package dev.svenrobbie.flip_2_dnd.free

import android.content.Context
import android.provider.Settings
import android.util.Log
import dev.svenrobbie.flip_2_dnd.core.PowerController

class FreePowerController(context: Context) : PowerController {
    private val contentResolver = context.contentResolver

    override fun setBatterySaverEnabled(enabled: Boolean) {
        try {
            Settings.Global.putInt(contentResolver, "low_power", if (enabled) 1 else 0)
        } catch (e: Exception) {
            Log.e("FreePowerController", "Failed to set battery saver: ${e.message}")
        }
    }
}
