package eu.curtisy.kwallet.ui.components

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.ui.theme.onPurple
import java.util.*

@Composable
fun CardView(
    accentColor: Color,
    isVisa: Boolean = true,
    iban: Int = 1234567890,
    expiry: Calendar = Calendar.getInstance(),
    cvv: Int = 890
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
        elevation = 8.dp,
        modifier = Modifier.width(240.dp).height(120.dp)
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
                text = iban.toString(),
                style = MaterialTheme.typography.body1.copy(
                    color = onPurple,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 50.dp)
            )
            Text(
                text = "${expiry.get(Calendar.MONTH)}/${expiry.get(Calendar.YEAR)}",
                style = MaterialTheme.typography.body1.copy(
                    color = onPurple,
                    fontWeight = FontWeight.Medium
                ),
                modifier = Modifier.padding(top = 70.dp)
            )
        }
    }
}

@Composable
@Preview("Preview")
private fun CardGradientPreview() {
    CardView(accentColor = Color.Transparent)
}
