package eu.curtisy.kwallet.ui.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.rounded.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.data.entities.Payment
import eu.curtisy.kwallet.data.enums.PaymentType
import eu.curtisy.kwallet.extensions.currency
import eu.curtisy.kwallet.extensions.getDayFormatted


@Composable
fun PaymentItem(
    payment: Payment,
    modifier: Modifier = Modifier
) {
    Column(modifier.padding(horizontal = 16.dp)) {
        Text(
            text = payment.createdDate.getDayFormatted(),
            style = MaterialTheme.typography.body2.copy(
                fontWeight = FontWeight.Medium,
            ),
        )

        Row(Modifier.padding(top = 8.dp), verticalAlignment = Alignment.CenterVertically) {
            Image(
                imageVector = when (payment.type) {
                    PaymentType.FOOD -> Icons.Rounded.Fastfood
                    PaymentType.EDUCATION -> Icons.Rounded.School
                    PaymentType.GENERIC -> Icons.Rounded.ShoppingCart
                    PaymentType.PARKING -> Icons.Rounded.LocalParking
                    PaymentType.CREDIT -> Icons.Rounded.Payment
                },
                null,
                contentScale = ContentScale.Inside,
                modifier = Modifier
                    .clip(MaterialTheme.shapes.medium)
                    .padding(8.dp)
                    .size(20.dp)
            )
            Column(
                Modifier
                    .padding(horizontal = 24.dp)
                    .weight(1f)
                    .fillMaxWidth()
            ) {
                Text(
                    text = payment.description ?: if (payment.credit) "Credit" else "Debit",
                    style = MaterialTheme.typography.body1.copy(
                        color = MaterialTheme.colors.onBackground,
                        fontWeight = FontWeight.Medium,
                    )
                )
                Text(
                    text = when (payment.type) {
                        PaymentType.FOOD -> "Food"
                        PaymentType.EDUCATION -> "Education"
                        PaymentType.PARKING -> "Parking"
                        PaymentType.GENERIC -> "Generic"
                        PaymentType.CREDIT -> "Payments"
                    }
                )
            }
            Text(
                text = "${if (payment.credit) "+" else "-"} ${payment.amount.currency()}",
                style = MaterialTheme.typography.body1.copy(
                    fontWeight = FontWeight.Bold,
                ),
            )
        }
    }
}

@Composable
@Preview
private fun PaymentItemPreview() {
    val payment = Payment("Test", 200.00, PaymentType.EDUCATION, true)
    PaymentItem(payment = payment)
}