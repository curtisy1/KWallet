package eu.curtisy.kwallet.ui.components.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.constraintlayout.compose.ConstraintLayout
import eu.curtisy.kwallet.data.entities.CreditCard
import eu.curtisy.kwallet.ui.components.CardView
import eu.curtisy.kwallet.extensions.toColor
import java.util.*

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
    val creditCards: List<CreditCard> = listOf(
        CreditCard("One", 1, Calendar.getInstance(), 1, true, 1.0, "#FFFFFF"),
        CreditCard("Two", 2, Calendar.getInstance(), 2, false, 2.0, "#000000"),
        CreditCard("Three", 3, Calendar.getInstance(), 3, true, 3.0, "#555555")
    )

    HorizontalList(items = creditCards, generatorFunc = {
        CardView(accentColor = it.color.toColor())
        Spacer(Modifier.width(5.dp))
    })
}
