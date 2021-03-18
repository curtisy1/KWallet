package eu.curtisy.kwallet.ui.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun CardView(
    modifier: Modifier = Modifier,
    backgroundColor: Color? = Color.LightGray,
    contentColor: Color = Color.Black,
    onClick: () -> Unit = { },
    content: @Composable () -> Unit,
) {

    Card(
        shape = RoundedCornerShape(10.dp),
        backgroundColor = backgroundColor ?: Color.LightGray,
        contentColor = contentColor,
        elevation = 8.dp,
        modifier = modifier
            .width(240.dp)
            .height(120.dp)
            .clickable { onClick() }
    ) {
        content()
    }
}

@Composable
@Preview("Preview")
private fun CardGradientPreview() {
    CardView(content = { })
}
