package eu.curtisy.kwallet.ui.components.utils

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.MaterialTheme
import androidx.compose.material.contentColorFor
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Check
import androidx.compose.material.icons.filled.Palette
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.luminance
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.google.accompanist.flowlayout.FlowRow
import com.google.accompanist.flowlayout.MainAxisAlignment
import com.google.accompanist.flowlayout.SizeMode

val ColorPickerColors = listOf(
    Color(0xFFFFFFFF),
    Color(0xFFFAFAFA),
    Color(0x80FF4444),
    Color(0xFFEF5350),
    Color(0xFFEC407A),
    Color(0xFFAB47BC),
    Color(0xFF7E57C2),
    Color(0xFF5C6BC0),
    Color(0xFF42A5F5),
    Color(0xFF29B6F6),
    Color(0xFF26C6DA),
    Color(0xFF26A69A),
    Color(0xFF66BB6A),
    Color(0xFF9CCC65),
    Color(0xFFD4E157),
    Color(0xFFFFEE58),
    Color(0xFFFFCA28),
    Color(0xFFFFA726),
    Color(0xFFFF7043),
    Color(0xFF000000),
)

@Composable
fun ColorPicker(
    modifier: Modifier = Modifier,
    selectedColor: Color,
    onColorSelected: (color: Color) -> Unit,
    open: Boolean,
    onOpen: () -> Unit,
) {
    Column(modifier = modifier) {
        FlowRow(
            mainAxisAlignment = MainAxisAlignment.Start,
            mainAxisSize = SizeMode.Wrap,
            crossAxisSpacing = 4.dp,
            mainAxisSpacing = 4.dp
        ) {
            ColorPickerToggleButton(open, onOpen)
            if(open) {
                ColorPickerColors.distinct().forEach { color ->
                    ColorItem(
                        selected = color == selectedColor,
                        color = color,
                        onClick = { onColorSelected(color) }
                    )
                }
            }
        }
    }
}

@Composable
fun ColorItem(
    selected: Boolean,
    color: Color,
    onClick: () -> Unit
) {
    val grey400 = Color(0xFFBDBDBD)

    Box(
        modifier = Modifier
            .padding(4.dp)
            .clip(CircleShape)
            .size(40.dp)
            .clickable(onClick = onClick)
    ) {
        // Transparent background pattern
        Box(
            modifier = Modifier
                .width(20.dp)
                .fillMaxHeight()
                .background(grey400)
        )
        // Color indicator
        var colorModifier = Modifier
            .fillMaxSize()
            .background(color)
        if (color.luminance() < 0.1 || color.luminance() > 0.9) {
            colorModifier = colorModifier
                .border(
                    width = 1.dp,
                    color = MaterialTheme.colors.onSurface,
                    shape = CircleShape
                )
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
    }
}

@Composable
fun ColorPickerToggleButton(
    open: Boolean,
    onOpen: () -> Unit,
) {
    var buttonModifier = Modifier
        .padding(4.dp)
        .clip(CircleShape)
        .size(40.dp)
    if (open) {
        buttonModifier = buttonModifier.background(MaterialTheme.colors.background)
    }
    Box(
        modifier = buttonModifier
    ) {
        IconButton(
            onClick = {
                onOpen()
            }
        ) {
            Icon(
                Icons.Default.Palette,
                "",
                tint = contentColorFor(MaterialTheme.colors.surface)
            )
        }
    }
}

@Composable
@Preview
fun ColorPickerPreview() {
    val (colorPickerOpen, onColorPickerOpen) = remember { mutableStateOf(false) }
    ColorPicker(
        selectedColor = ColorPickerColors[0],
        onColorSelected = { },
        open = colorPickerOpen,
        onOpen = { onColorPickerOpen(!colorPickerOpen) })
}

@Composable
@Preview
fun ColorItemPreview() {
    ColorItem(selected = false, color = ColorPickerColors[0], onClick = { })
}