package eu.curtisy.kwallet.data.repositories

import eu.curtisy.kwallet.data.entities.CreditCard
import eu.curtisy.kwallet.interfaces.CoinRepository
import eu.curtisy.kwallet.extensions.toHEX
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flowOf
import java.util.*

class CoinRepositoryImpl : CoinRepository {
    override suspend fun getCoins(): Flow<List<CreditCard>> {
        return flowOf(listOf(
            CreditCard(
                name = "Tomorrow",
                isVisa = true,
                cvv = 123,
                balance = 872685.85,
                color = eu.curtisy.kwallet.ui.theme.yellow400.toHEX(),
                expiry = Calendar.getInstance(),
                iban = 1234567890,
            ),
            CreditCard(
                name = "Sparkasse",
                isVisa = true,
                cvv = 456,
                balance = 6005.061,
                color = eu.curtisy.kwallet.ui.theme.red600.toHEX(),
                iban = 987654321,
                expiry = Calendar.getInstance()
            ),
            CreditCard(
                name = "Postbank",
                isVisa = false,
                cvv = 789,
                balance = 777.77,
                color = eu.curtisy.kwallet.ui.theme.red600.toHEX(),
                iban = 567894321,
                expiry = Calendar.getInstance()
            ),
        ))
    }
}