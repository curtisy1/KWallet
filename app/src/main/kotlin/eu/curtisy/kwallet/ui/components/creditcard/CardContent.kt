package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.ui.components.CardView
import eu.curtisy.kwallet.ui.theme.onPurple

@Composable
fun CardContent(
    cardNumber: Long,
    cardHolder: String,
    cvc: Short,
    iban: String,
    bic: String,
    validMonth: Short,
    validYear: Int,
    isVisa: Boolean = true,
) {
    Box(Modifier.padding(top = 16.dp, bottom = 12.dp, start = 12.dp, end = 12.dp)) {
        Text(
            text = if(isVisa) "VISA" else "MasterCard",
            style = MaterialTheme.typography.h6.copy(
                color = MaterialTheme.colors.onBackground,
                fontWeight = FontWeight.Medium
            )
        )
        // TODO: Use something other than padding here? Like stick to bottom?
        Text(
            text = cardNumber.toString(),
            style = MaterialTheme.typography.body1.copy(
                color = onPurple,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(top = 50.dp)
        )
        Text(
            text = "${validMonth}/${validYear}",
            style = MaterialTheme.typography.body1.copy(
                color = onPurple,
                fontWeight = FontWeight.Medium
            ),
            modifier = Modifier.padding(top = 70.dp)
        )
    }
}

@Composable
@Preview
private fun CardContentPreview() {
    CardView(accentColor = Color.LightGray) {
        CardContent(
            iban = "DE 1234567890",
            isVisa = true,
            bic = "BELADEBXXX",
            cardHolder = "Some Cool Dude",
            cardNumber = 1234567890,
            cvc = 123,
            validMonth = 12,
            validYear = 21
        )
    }
}