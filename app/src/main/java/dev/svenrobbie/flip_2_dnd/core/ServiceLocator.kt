package dev.svenrobbie.flip_2_dnd.core

import android.content.Context
import dev.svenrobbie.flip_2_dnd.free.FreeDetectionManager
import dev.svenrobbie.flip_2_dnd.free.FreeFlashController
import dev.svenrobbie.flip_2_dnd.free.FreePowerController
import dev.svenrobbie.flip_2_dnd.free.FreeProFeatures
import dev.svenrobbie.flip_2_dnd.free.FreeScheduleManager
import dev.svenrobbie.flip_2_dnd.free.FreeSensorManagerPro
import dev.svenrobbie.flip_2_dnd.free.FreeSoundController
import dev.svenrobbie.flip_2_dnd.free.FreeSoundPicker

object ServiceLocator {
    private var _featureManager: ProFeatureManager? = null
    private var _flashController: FlashController? = null
    private var _powerController: PowerController? = null
    private var _scheduleManager: ScheduleManager? = null
    private var _detectionManager: DetectionManager? = null
    private var _sensorManagerPro: SensorManagerPro? = null
    private var _soundController: SoundController? = null
    private var _soundPicker: SoundPicker? = null

    fun getFeatureManager(context: Context): ProFeatureManager {
        return _featureManager ?: FreeProFeatures(context).also { _featureManager = it }
    }

    fun getFlashController(context: Context): FlashController {
        return _flashController ?: FreeFlashController(context).also { _flashController = it }
    }

    fun getPowerController(context: Context): PowerController {
        return _powerController ?: FreePowerController(context).also { _powerController = it }
    }

    fun getScheduleManager(context: Context): ScheduleManager {
        return _scheduleManager ?: FreeScheduleManager(context).also { _scheduleManager = it }
    }

    fun getDetectionManager(context: Context): DetectionManager {
        return _detectionManager ?: FreeDetectionManager(context).also { _detectionManager = it }
    }

    fun getSensorManagerPro(context: Context): SensorManagerPro {
        return _sensorManagerPro ?: FreeSensorManagerPro(context).also { _sensorManagerPro = it }
    }

    fun getSoundController(context: Context): SoundController {
        return _soundController ?: FreeSoundController(context).also { _soundController = it }
    }

    fun getSoundPicker(context: Context): SoundPicker {
        return _soundPicker ?: FreeSoundPicker(context).also { _soundPicker = it }
    }
}
