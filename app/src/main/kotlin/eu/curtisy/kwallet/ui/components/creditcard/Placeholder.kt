package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import eu.curtisy.kwallet.ui.components.CardView

@Composable
fun Placeholder(onClick: () -> Unit) {
    CardView() {
        Column(
            modifier = Modifier
                .clickable { onClick() },
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
        ) {
            Text(
                text = "Add credit card",
            )
        }
    }
}

@Composable
@Preview
private fun PlaceholderPreview() {
    Placeholder(onClick = { })
}