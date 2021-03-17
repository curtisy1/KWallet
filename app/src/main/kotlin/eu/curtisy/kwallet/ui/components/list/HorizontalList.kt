package eu.curtisy.kwallet.ui.components.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.ui.components.CardView
import eu.curtisy.kwallet.ui.components.creditcard.CardContent

@Composable
fun <T> HorizontalList(
    items: List<T>,
    generatorFunc: @Composable (item: T) -> Unit,
    modifier: Modifier = Modifier
) {
    ConstraintLayout(
        modifier
    ) {
        Row(
            Modifier
                .horizontalScroll(rememberScrollState())
        ) {
            items.forEach {
                generatorFunc(it)
            }
        }
    }
}

@Composable
@Preview
private fun HorizontalListPreview() {
    val creditCards: List<Card> = listOf(
        Card(1234567890,
            "One",
            123,
            "DE 123456789",
            "BDERFGT",
            12,
            21,
            "#FFFFFF",
            true
        ),
        Card(1234567890,
            "One",
            123,
            "DE 123456789",
            "BDERFGT",
            12,
            21,
            "#FFFFFF",
            false
        ),
        Card(1234567890,
            "One",
            123,
            "DE 123456789",
            "BDERFGT",
            12,
            21,
            "#FFFFFF",
            true
        ),
    )

    HorizontalList(items = creditCards, generatorFunc = {
        CardView(backgroundColor = it.color.toColor()) {
            CardContent(
                it.cardNumber,
                it.fullName,
                it.cvc,
                it.iban,
                it.bic,
                it.validMonth,
                it.validYear,
                it.isVisa
            )
        }
        Spacer(Modifier.width(5.dp))
    })
}
