package dev.svenrobbie.flip_2_dnd.data.repository

import dev.svenrobbie.flip_2_dnd.data.local.dao.HistoryDao
import dev.svenrobbie.flip_2_dnd.data.local.entity.HistoryEntity
import dev.svenrobbie.flip_2_dnd.core.HistoryRepository
import dev.svenrobbie.flip_2_dnd.core.HistoryItem
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map
import javax.inject.Inject
import javax.inject.Singleton

@Singleton
class HistoryRepositoryImpl @Inject constructor(
    private val historyDao: HistoryDao
) : HistoryRepository {
    override fun getAllHistory(): Flow<List<HistoryItem>> {
        return historyDao.getAllHistory().map { entities ->
            entities.map { entity ->
                HistoryItem(
                    id = entity.id,
                    timestamp = entity.timestamp,
                    isEnabled = entity.isEnabled,
                    dndMode = entity.dndMode
                )
            }
        }
    }

    override suspend fun addHistory(isEnabled: Boolean, dndMode: Int) {
        val history = HistoryEntity(
            timestamp = System.currentTimeMillis(),
            isEnabled = isEnabled,
            dndMode = dndMode
        )
        historyDao.insertHistory(history)
        historyDao.trimHistory() // Keep only last 100 entries
    }

    override suspend fun clearHistory() {
        historyDao.clearHistory()
    }
}
