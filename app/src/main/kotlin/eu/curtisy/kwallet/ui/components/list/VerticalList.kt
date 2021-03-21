package eu.curtisy.kwallet.ui.components.list

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.data.enums.PaymentType

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
    val payments = listOf(
        "Education",
        "Credit",
        "Food",
        "Parking",
        "Generic"
    )

    VerticalList(items = payments, generatorFunc = {
        Text(text = it, modifier = Modifier.padding(bottom = 24.dp))
    })
}