package dev.svenrobbie.flip_2_dnd.presentation.main

import dev.svenrobbie.flip_2_dnd.R
import dev.svenrobbie.flip_2_dnd.core.PhoneOrientation

data class MainState(
    val orientation: PhoneOrientation = PhoneOrientation.UNKNOWN,
    val isDndEnabled: Boolean = false,
    val dndMode: Int = R.string.dnd_mode_all,
    val isScreenOffOnly: Boolean = false,
    val isVibrationEnabled: Boolean = true,
    val isSoundEnabled: Boolean = false,
    val isServiceRunning: Boolean = true
)
