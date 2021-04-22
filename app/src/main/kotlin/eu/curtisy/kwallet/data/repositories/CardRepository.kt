package eu.curtisy.kwallet.data.repositories

import eu.curtisy.kwallet.data.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getAll(): Flow<List<Card>>
    fun insertOrUpdate(card: Card)
    fun delete(id: Int)
}