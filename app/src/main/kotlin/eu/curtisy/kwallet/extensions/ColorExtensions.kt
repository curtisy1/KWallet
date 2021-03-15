package eu.curtisy.kwallet.extensions

import androidx.compose.ui.graphics.Color
import kotlin.math.roundToInt

fun Color.toHEX(): String {
    return try {
        String.format(
            "#%02x%02x%02x",
            (red * 255).roundToInt(),
            (green * 255).roundToInt(),
            (blue * 255).roundToInt()
        )
    } catch (e: Exception) {
        "#000000"
    }
}



