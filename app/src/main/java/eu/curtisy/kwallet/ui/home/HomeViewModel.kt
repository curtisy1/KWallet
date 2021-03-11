package eu.curtisy.kwallet.ui.home

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.curtisy.kwallet.data.entities.CreditCard
import eu.curtisy.kwallet.data.entities.Payment
import eu.curtisy.kwallet.data.enums.PaymentType
import eu.curtisy.kwallet.interfaces.GetCoinUseCase
import eu.curtisy.kwallet.data.entities.Result
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import timber.log.Timber

class HomeViewModel(
    private val getCoinUseCase: GetCoinUseCase,
) : ViewModel() {
    var creditCards: List<CreditCard> by mutableStateOf(emptyList())
    var payments: List<Payment> by mutableStateOf(emptyList())

    init {
        collectCoin()
        payments = listOf(
            Payment(
                description = "Parking Lot",
                createdDate = 1611532800,
                type = PaymentType.PARKING,
                credit = false,
                amount = 15.3
            ),
            Payment(
                description = "Starbucks",
                createdDate = System.currentTimeMillis() / 1000,
                type = PaymentType.FOOD,
                credit = false,
                amount = 27.5
            ),
            Payment(
                description = "Mc Donalds",
                createdDate = 1611360000,
                type = PaymentType.FOOD,
                credit = false,
                amount = 33.89
            ),
            Payment(
                description = "Study Fees",
                createdDate = 1609459200,
                type = PaymentType.EDUCATION,
                credit = false,
                amount = 1200.0
            )
        )
    }

    private fun collectCoin() {
        viewModelScope.launch {
            getCoinUseCase.getCoin().collect { result ->
                when (result) {
                    is Result.Success -> {
                        Timber.d("Result: ${result.data}")
                        creditCards = result.data
                    }
                    is Result.Error -> {

                    }
                }
            }
        }
    }
}