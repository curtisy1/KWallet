package eu.curtisy.kwallet.ui.components.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.data.entities.Payment
import eu.curtisy.kwallet.data.enums.PaymentType
import eu.curtisy.kwallet.ui.components.PaymentItem

@Composable
fun <T> VerticalList(
    items: List<T>,
    generatorFunc: @Composable (item: T) -> Unit,
    modifier: Modifier = Modifier,
) {
    Column(
        modifier
//            .navigationBarsPadding()
            .height(150.dp)
    ) {
        Box(Modifier.padding(top = 4.dp)) {
            Column(Modifier.verticalScroll(rememberScrollState())) {
                Spacer(modifier = Modifier.size(12.dp))
                items.forEach {
                    generatorFunc(it)
                }
                Spacer(modifier = Modifier.size(12.dp))
            }
        }
    }
}

@Composable
@Preview
private fun VerticalListPreview() {
    val payments: List<Payment> = listOf(
        Payment("Education", 200.00, PaymentType.EDUCATION, true),
        Payment("Credit", 50.00, PaymentType.CREDIT, true),
        Payment("Food", 20.00, PaymentType.FOOD, false),
        Payment("Parking", 5.00, PaymentType.PARKING, false),
        Payment("Generic", 75.51, PaymentType.GENERIC, false)
    )

    VerticalList(items = payments, generatorFunc = {
        PaymentItem(payment = it, modifier = Modifier.padding(bottom = 24.dp))
    })
}