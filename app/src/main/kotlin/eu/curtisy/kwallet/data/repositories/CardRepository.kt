package eu.curtisy.kwallet.data.repositories

import eu.curtisy.kwallet.data.Card
import kotlinx.coroutines.flow.Flow

interface CardRepository {
    suspend fun getAll(): Flow<List<Card>>
    suspend fun getCard(cardNumber: Short): Flow<Card?>
    fun insertOrUpdate(card: Card)
    fun delete(cardNumber: Long)
}