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
        Card(1234567890, "One", 123, 12, 21, "#FFFFFF", true),
        Card(987654321, "Two", 234, 1, 20, "#000000", false),
        Card(6789054321, "Three", 345, 3, 19, "#555555", false)
    )

    HorizontalList(items = creditCards, generatorFunc = {
        CardView(accentColor = it.color.toColor()) {
            CardContent()
        }
        Spacer(Modifier.width(5.dp))
    })
}
