package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.padding
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.ui.components.CardView

@Composable
fun Placeholder(onClick: () -> Unit) {
    CardView(accentColor = Color.Blue) {
        Box(
            Modifier
                .padding(
                    top = 16.dp,
                    bottom = 12.dp,
                    start = 12.dp,
                    end = 12.dp
                )
                .clickable { onClick() },
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
    Placeholder(onClick = {  })
}