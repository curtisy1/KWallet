package eu.curtisy.kwallet.ui.screens.overview

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.data.entities.Payment
import eu.curtisy.kwallet.data.repositories.CardRepository
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch

class OverviewViewModel(
    private val cardRepository: CardRepository,
) : ViewModel() {
    var creditCards: List<Card> by mutableStateOf(emptyList())
    var payments: List<Payment> by mutableStateOf(emptyList())

    init {
        getAllCreditCards()
    }

    private fun getAllCreditCards() {
        viewModelScope.launch {
            cardRepository.getAll().collect { result ->
                creditCards = result
            }
        }
    }
}