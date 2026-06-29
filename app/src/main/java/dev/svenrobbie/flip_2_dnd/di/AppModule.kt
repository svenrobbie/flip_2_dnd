package dev.svenrobbie.flip_2_dnd.di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import dev.svenrobbie.flip_2_dnd.core.DetectionManager
import dev.svenrobbie.flip_2_dnd.core.FlashController
import dev.svenrobbie.flip_2_dnd.core.PowerController
import dev.svenrobbie.flip_2_dnd.core.ProFeatureManager
import dev.svenrobbie.flip_2_dnd.core.ScheduleManager
import dev.svenrobbie.flip_2_dnd.core.SensorManagerPro
import dev.svenrobbie.flip_2_dnd.core.SoundController
import dev.svenrobbie.flip_2_dnd.core.SoundPicker
import dev.svenrobbie.flip_2_dnd.data.local.AppDatabase
import dev.svenrobbie.flip_2_dnd.data.local.dao.HistoryDao
import dev.svenrobbie.flip_2_dnd.free.FreeDetectionManager
import dev.svenrobbie.flip_2_dnd.free.FreeFlashController
import dev.svenrobbie.flip_2_dnd.free.FreePowerController
import dev.svenrobbie.flip_2_dnd.free.FreeProFeatures
import dev.svenrobbie.flip_2_dnd.free.FreeScheduleManager
import dev.svenrobbie.flip_2_dnd.free.FreeSensorManagerPro
import dev.svenrobbie.flip_2_dnd.free.FreeSoundController
import dev.svenrobbie.flip_2_dnd.free.FreeSoundPicker
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
    @Provides
    @Singleton
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase {
        return Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "flip2dnd_db"
        ).build()
    }

    @Provides
    fun provideHistoryDao(database: AppDatabase): HistoryDao {
        return database.historyDao()
    }

    @Provides
    @Singleton
    fun provideSoundController(@ApplicationContext context: Context): SoundController {
        return FreeSoundController(context)
    }

    @Provides
    @Singleton
    fun provideFlashController(@ApplicationContext context: Context): FlashController {
        return FreeFlashController(context)
    }

    @Provides
    @Singleton
    fun providePowerController(@ApplicationContext context: Context): PowerController {
        return FreePowerController(context)
    }

    @Provides
    @Singleton
    fun provideScheduleManager(@ApplicationContext context: Context): ScheduleManager {
        return FreeScheduleManager(context)
    }

    @Provides
    @Singleton
    fun provideDetectionManager(@ApplicationContext context: Context): DetectionManager {
        return FreeDetectionManager(context)
    }

    @Provides
    @Singleton
    fun provideSoundPicker(@ApplicationContext context: Context): SoundPicker {
        return FreeSoundPicker(context)
    }

    @Provides
    @Singleton
    fun provideFeatureManager(@ApplicationContext context: Context): ProFeatureManager {
        return FreeProFeatures(context)
    }

    @Provides
    @Singleton
    fun provideSensorManagerPro(@ApplicationContext context: Context): SensorManagerPro {
        return FreeSensorManagerPro(context)
    }
}
