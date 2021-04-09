package eu.curtisy.kwallet.ui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

@Composable
fun ColorPicker(
    items: List<Color?>,
    selectedColor: Color?,
    onColorSelected: (color: Color?) -> Unit,
    modifier: Modifier = Modifier
) {
    Column(modifier = modifier) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Start,
            mainAxisSize = SizeMode.Wrap,
            crossAxisSpacing = 4.dp,
            mainAxisSpacing = 4.dp
        ) {
            items.distinct().forEach { color ->
                ColorItem(
                    selected = color == selectedColor,
                    color = color,
                    onClick = { onColorSelected(color) }
                )
            }
        }
    }
}

@Composable
fun ColorItem(
    selected: Boolean,
    color: Color?,
    onClick: () -> Unit
) {
    val grey400 = Color(0xFFBDBDBD)
    val blackAlpha20 = Color(0x33000000)
    val whiteAlpha20 = Color(0x33FFFFFF)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .size(40.dp)
            .clickable(onClick = onClick)
    ) {
        if (color != null) {
            // Transparent background pattern
            Box(modifier = Modifier.width(20.dp).fillMaxHeight().background(grey400))
            // Color indicator
            val colorModifier =
                if (color.luminance() < 0.1 || color.luminance() > 0.9) {
                    Modifier
                        .fillMaxSize()
                        .background(color)
                        .border(
                            width = 1.dp,
                            color = MaterialTheme.colors.onSurface,
                            shape = CircleShape
                        )
                } else {
                    Modifier
                        .fillMaxSize()
                        .background(color)
                }
            Box(
                modifier = colorModifier
            ) {
                if (selected) {
                    Icon(
                        Icons.Default.Check,
                        "",
                        tint = if (color.luminance() < 0.5) Color.White else Color.Black,
                        modifier = Modifier.align(Alignment.Center)
                    )
                }
            }
        } else {
            if (selected) {
                Box(
                    modifier = Modifier
                        .fillMaxSize()
                        .align(Alignment.Center)
                        .background(if (isSystemInDarkTheme()) whiteAlpha20 else blackAlpha20)
                )
            }
            // Color null indicator
            Icon(
                Icons.Default.Palette,
                "",
                modifier = Modifier.align(Alignment.Center),
                tint = contentColorFor(MaterialTheme.colors.surface)
            )
        }
    }
}