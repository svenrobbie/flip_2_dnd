package dev.svenrobbie.flip_2_dnd.data.repository

import android.content.Context
import android.os.PowerManager
import android.util.Log
import dev.svenrobbie.flip_2_dnd.core.ScreenStateRepository
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class ScreenStateRepositoryImpl @Inject constructor(
    @param:ApplicationContext private val context: Context
) : ScreenStateRepository {
    private val _isScreenOff = MutableStateFlow(false)

    private val powerManager = context.getSystemService(Context.POWER_SERVICE) as PowerManager

    init {
        _isScreenOff.value = !powerManager.isInteractive
    }

    override fun isScreenOff(): Flow<Boolean> = _isScreenOff

    override fun startMonitoring() {
        Log.d(TAG, "Screen state monitoring: FlipDetectorService handles this natively")
    }

    override fun stopMonitoring() {
        // FlipDetectorService handles screen state monitoring natively
    }

    companion object {
        private const val TAG = "ScreenStateRepository"
    }
}
