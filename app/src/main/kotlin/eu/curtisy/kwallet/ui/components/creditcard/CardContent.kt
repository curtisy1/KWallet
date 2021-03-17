package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.ui.components.CardView

@Composable
fun CardContent(
    cardNumber: Long,
    cardHolder: String,
    cvc: Short,
    iban: String,
    bic: String,
    validMonth: Short,
    validYear: Int,
    isVisa: Boolean,
    color: String? = null,
    onClick: () -> Unit = { }
) {
    CardView(
        backgroundColor = color?.toColor(),
        onClick = onClick
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            CardFrontLayer(
                cardNumber = cardNumber,
                cardHolder = cardHolder,
                validMonth = validMonth,
                validYear = validYear,
                isVisa = isVisa
            )
        }
    }
}

@Composable
fun CardFrontLayer(
    cardNumber: Long,
    cardHolder: String,
    validMonth: Short,
    validYear: Int,
    isVisa: Boolean,
) {
    Column(
        modifier = Modifier.padding(start = 5.dp)
    ) {
        Column {
            Text(
                text = if (isVisa) "VISA" else "MasterCard",
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column() {
            Text(
                text = cardNumber.toString(),
            )
            Text(
                text = "${validMonth}/${validYear}",
            )
        }
    }
}

@Composable
fun CardBackLayer(
    iban: String,
    bic: String,
    cvc: Short,
) {
    Column(
        modifier = Modifier.padding(start = 5.dp)
    ) {
        Column {
            Text(
                text = iban,
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column() {
            Text(
                text = bic,
            )
            Text(
                text = cvc.toString(),
            )
        }
    }
}

@Composable
@Preview
private fun CardContentPreview() {
    CardContent(
        iban = "DE 1234567890",
        isVisa = true,
        bic = "BELADEBXXX",
        cardHolder = "Some Cool Dude",
        cardNumber = 1234567890,
        cvc = 123,
        validMonth = 12,
        validYear = 21,
    )
}

@Composable
@Preview
private fun CardFrontLayerPreview() {
    CardFrontLayer(
        isVisa = true,
        cardHolder = "Some Cool Dude",
        cardNumber = 1234567890,
        validMonth = 12,
        validYear = 21
    )
}

@Composable
@Preview
private fun CardBackLayerPreview() {
    CardBackLayer(
        iban = "DE 1234567890",
        bic = "BELADEBXXX",
        cvc = 123,
    )
}