package eu.curtisy.kwallet.data.usecase

import eu.curtisy.kwallet.data.entities.CreditCard
import eu.curtisy.kwallet.interfaces.CoinRepository
import eu.curtisy.kwallet.interfaces.ErrorHandler
import eu.curtisy.kwallet.interfaces.GetCoinUseCase
import eu.curtisy.kwallet.extensions.handleResult
import kotlinx.coroutines.flow.Flow

class GetCoinUseCaseImpl(
    private val coinRepository: CoinRepository,
    private val errorHandler: ErrorHandler,
) : GetCoinUseCase {
    override suspend fun getCoin(): Flow<eu.curtisy.kwallet.data.entities.Result<List<CreditCard>>> {
        return coinRepository.getCoins().handleResult(errorHandler)
    }
}