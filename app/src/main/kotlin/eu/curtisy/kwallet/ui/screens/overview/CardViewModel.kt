package eu.curtisy.kwallet.ui.screens.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.data.repositories.CardRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class CardViewModel(
    private val cardRepository: CardRepository,
) : ViewModel() {
    var creditCards by mutableStateOf(listOf<Card>())
    var selectedCard: Card? by mutableStateOf(null)

    init {
        getAllCreditCards()
    }

    fun addCard() {
        val dummyCard = Card(
            iban = "XX 1234567890",
            isVisa = true,
            bic = "XXXX",
            fullName = "Cardholder name",
            cardNumber = 1234567890,
            cvc = 123,
            validMonth = 1,
            validYear = 21,
            color = "#FFFFFF"
        )

        selectedCard = dummyCard
    }

    fun saveCard(card: Card) {
        cardRepository.insertOrUpdate(card)
    }

    private fun getAllCreditCards() {
        viewModelScope.launch {
            cardRepository.getAll().collect { result ->
                creditCards = result
            }
        }
    }
}