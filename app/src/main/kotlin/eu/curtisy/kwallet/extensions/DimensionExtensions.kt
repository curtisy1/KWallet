package eu.curtisy.kwallet.extensions

import android.util.TypedValue
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.Dp

@Composable
fun Dp.toPx() : Float{
    val dp = this.value
    return TypedValue.applyDimension(
        TypedValue.COMPLEX_UNIT_DIP,
        dp,
        LocalContext.current.resources.displayMetrics,
    )
}