package dev.svenrobbie.flip_2_dnd.free

import android.widget.Toast
import android.content.Context
import dev.svenrobbie.flip_2_dnd.core.ProFeatureManager
import dev.svenrobbie.flip_2_dnd.core.UpdateResponse
import dev.svenrobbie.flip_2_dnd.core.UpdateState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow

class FreeProFeatures(context: Context) : ProFeatureManager {
    override fun autoStartEnabled() = true
    override fun advancedSensitivityEnabled() = true
    override fun delayCustomizationEnabled() = true
    override fun scheduleEnabled() = true
    override fun customSoundEnabled() = true
    override fun batterySaverSyncEnabled() = true
    override fun detectionFiltersEnabled() = true
    override fun telegramSupportEnabled() = true
    override fun flashlightFeedbackEnabled() = true
    override fun isPro() = true

    private val _updateState = MutableStateFlow<UpdateState>(UpdateState.None)
    override fun getUpdateState() = _updateState.asStateFlow()
    override fun checkForUpdate(manual: Boolean) {
        // No-op
    }
    
    override fun downloadAndInstall(context: Context, update: UpdateResponse) {
        // Downloads handled by app module
    }
}
