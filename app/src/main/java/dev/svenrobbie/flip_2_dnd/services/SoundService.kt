package dev.svenrobbie.flip_2_dnd.services

import android.content.Context
import dev.svenrobbie.flip_2_dnd.core.SettingsRepository
import dev.svenrobbie.flip_2_dnd.core.Sound
import dev.svenrobbie.flip_2_dnd.core.SoundController
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.first
import kotlinx.coroutines.withContext

class SoundService(
    private val context: Context,
    private val settingsRepository: SettingsRepository,
    private val soundController: SoundController
) {
    
    companion object {
        private const val TAG = "SoundService"
    }

    suspend fun playDndSound(isEnabled: Boolean) = withContext(Dispatchers.IO) {
        val isSoundEnabled = settingsRepository.getSoundEnabled().first()
        if (!isSoundEnabled) return@withContext

        try {
            val sound = if (isEnabled) {
                settingsRepository.getDndOnSound().first()
            } else {
                settingsRepository.getDndOffSound().first()
            }

            if (sound == Sound.NONE) return@withContext

            val uri = if (isEnabled) {
                settingsRepository.getDndOnCustomSoundUri().first()
            } else {
                settingsRepository.getDndOffCustomSoundUri().first()
            }

            val volume = settingsRepository.getCustomVolume().first()
            val useCustomVolume = settingsRepository.getUseCustomVolume().first()

            soundController.playSound(sound, uri, volume, useCustomVolume)
        } catch (e: Exception) {
            android.util.Log.e(TAG, "Error playing sound: ${e.message}", e)
        }
    }

    fun release() {
        soundController.release()
    }
}
