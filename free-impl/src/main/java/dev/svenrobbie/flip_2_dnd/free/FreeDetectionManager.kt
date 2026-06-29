package dev.svenrobbie.flip_2_dnd.free

import android.content.Context
import android.hardware.Sensor
import android.hardware.SensorEvent
import android.hardware.SensorEventListener
import android.hardware.SensorManager
import android.media.AudioDeviceInfo
import android.media.AudioManager
import android.os.Build
import android.util.Log
import dev.svenrobbie.flip_2_dnd.core.DetectionManager

class FreeDetectionManager(context: Context) : DetectionManager {
    private val TAG = "FreeDetectionManager"
    private val audioManager = context.getSystemService(Context.AUDIO_SERVICE) as AudioManager
    private val sensorManager = context.getSystemService(Context.SENSOR_SERVICE) as SensorManager
    private var proximityListener: ((Boolean) -> Unit)? = null
    private var proximityRegistered = false
    private var lastProximityState = false

    private val proximityEventListener = object : SensorEventListener {
        override fun onSensorChanged(event: SensorEvent) {
            if (event.sensor.type == Sensor.TYPE_PROXIMITY) {
                lastProximityState = event.values[0] < event.sensor.maximumRange
                proximityListener?.invoke(lastProximityState)
            }
        }

        override fun onAccuracyChanged(sensor: Sensor, accuracy: Int) {}
    }

    override fun isMediaPlaying(): Boolean {
        return try {
            audioManager.isMusicActive
        } catch (e: Exception) {
            Log.e(TAG, "Error checking media playback: ${e.message}")
            false
        }
    }

    override fun areHeadphonesConnected(): Boolean {
        return try {
            @Suppress("DEPRECATION")
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                val devices = audioManager.getDevices(AudioManager.GET_DEVICES_OUTPUTS)
                devices.any { device ->
                    device.type == AudioDeviceInfo.TYPE_WIRED_HEADSET ||
                    device.type == AudioDeviceInfo.TYPE_WIRED_HEADPHONES ||
                    device.type == AudioDeviceInfo.TYPE_BLUETOOTH_A2DP ||
                    device.type == AudioDeviceInfo.TYPE_BLUETOOTH_SCO ||
                    device.type == AudioDeviceInfo.TYPE_USB_HEADSET ||
                    device.type == AudioDeviceInfo.TYPE_USB_DEVICE
                }
            } else {
                audioManager.isWiredHeadsetOn || audioManager.isBluetoothA2dpOn
            }
        } catch (e: Exception) {
            Log.e(TAG, "Error checking headphones: ${e.message}")
            false
        }
    }

    override fun isProximityCovered(): Boolean {
        return lastProximityState
    }

    override fun registerProximityListener(callback: (Boolean) -> Unit) {
        if (proximityRegistered) {
            sensorManager.unregisterListener(proximityEventListener)
        }
        proximityListener = callback
        val sensor = sensorManager.getDefaultSensor(Sensor.TYPE_PROXIMITY)
        if (sensor != null) {
            sensorManager.registerListener(
                proximityEventListener,
                sensor,
                SensorManager.SENSOR_DELAY_NORMAL
            )
            proximityRegistered = true
        }
    }

    override fun unregisterProximityListener() {
        if (proximityRegistered) {
            sensorManager.unregisterListener(proximityEventListener)
            proximityRegistered = false
        }
        proximityListener = null
    }
}
