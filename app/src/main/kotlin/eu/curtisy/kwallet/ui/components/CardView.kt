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
    content: @Composable () -> Unit
) {
    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = Color.LightGray,
        contentColor = Color.Black,
        elevation = 8.dp,
        modifier = Modifier
            .width(240.dp)
            .height(120.dp)
    ) {
        content()
    }
}

@Composable
@Preview("Preview")
private fun CardGradientPreview() {
    CardView(accentColor = Color.Transparent, content = { Text("TestContent") })
}
