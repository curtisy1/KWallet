package eu.curtisy.kwallet.interfaces

import eu.curtisy.kwallet.data.entities.CreditCard
import kotlinx.coroutines.flow.Flow

interface CoinRepository {
    suspend fun getCoins(): Flow<List<CreditCard>>
}