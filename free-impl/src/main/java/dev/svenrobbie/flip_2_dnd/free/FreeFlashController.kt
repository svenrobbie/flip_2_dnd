package dev.svenrobbie.flip_2_dnd.free

import android.content.Context
import android.hardware.camera2.CameraManager
import android.util.Log
import dev.svenrobbie.flip_2_dnd.core.FlashController
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

class FreeFlashController(context: Context) : FlashController {
    private val TAG = "FreeFlashController"
    private val cameraManager = context.getSystemService(Context.CAMERA_SERVICE) as CameraManager
    private val cameraId = try {
        cameraManager.cameraIdList.firstOrNull { id ->
            val characteristics = cameraManager.getCameraCharacteristics(id)
            characteristics.get(android.hardware.camera2.CameraCharacteristics.FLASH_INFO_AVAILABLE) == true
        }
    } catch (e: Exception) {
        null
    }
    private var flashJob: Job? = null
    private val scope = CoroutineScope(Dispatchers.IO)

    override fun flashFlashlight(pattern: LongArray, intensity: Int) {
        if (cameraId == null || pattern.isEmpty()) return

        flashJob?.cancel()
        flashJob = scope.launch {
            try {
                for (i in pattern.indices) {
                    val state = i % 2 == 0
                    try {
                        cameraManager.setTorchMode(cameraId!!, state)
                    } catch (e: Exception) {
                        Log.e(TAG, "Error setting torch mode: ${e.message}")
                        break
                    }
                    if (i < pattern.size - 1) {
                        delay(pattern[i])
                    }
                }
                try {
                    cameraManager.setTorchMode(cameraId!!, false)
                } catch (e: Exception) {
                    Log.e(TAG, "Error turning off torch: ${e.message}")
                }
            } catch (e: Exception) {
                Log.e(TAG, "Error flashing flashlight: ${e.message}")
            }
        }
    }

    override fun shouldSkipFeedback(isFlashlightOn: Boolean, feedbackWithFlashlightOn: Boolean): Boolean {
        return isFlashlightOn && !feedbackWithFlashlightOn
    }
}
