package eu.curtisy.kwallet.interfaces

import eu.curtisy.kwallet.data.entities.CreditCard
import kotlinx.coroutines.flow.Flow

interface GetCoinUseCase {
    suspend fun getCoin(): Flow<eu.curtisy.kwallet.data.entities.Result<List<CreditCard>>>
}