package eu.curtisy.kwallet.data.repositories

import com.squareup.sqldelight.runtime.coroutines.asFlow
import com.squareup.sqldelight.runtime.coroutines.mapToList
import com.squareup.sqldelight.runtime.coroutines.mapToOneOrNull
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.data.CardDetailsQueries
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.withContext

class CardRepositoryImpl(private val queries: CardDetailsQueries) : CardRepository {
    override suspend fun getAll() = withContext(Dispatchers.IO) {
        queries.selectAllCards().asFlow().mapToList()
    }

    override suspend fun getCard(cardNumber: Short) = withContext(Dispatchers.IO) {
        queries.selectByCardNumber(cardNumber.toLong()).asFlow().mapToOneOrNull()
    }

    override fun insertOrUpdate(card: Card) {
        queries.insertOrUpdateCard(card)
    }

    override fun delete(cardNumber: Short) {
        queries.deleteCard(cardNumber.toLong())
    }
}