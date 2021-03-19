package eu.curtisy.kwallet.ui.animations

import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.runtime.Composable
import androidx.compose.runtime.State

@Composable
fun reversibleAnimation(
    from: Float,
    to: Float,
    condition: Boolean,
    duration: Int,
    easing: Easing
): State<Float> {
    return animateFloatAsState(
        targetValue = if (condition) from else to,
        // configure the animation duration and easing
        animationSpec = tween(durationMillis = duration, easing = easing)
    )
}

@Composable
fun fadingAlpha(condition: Boolean): State<Float> {
    return reversibleAnimation(
        from = 1f,
        to = 0f,
        condition = condition,
        duration = 500,
        easing = FastOutSlowInEasing
    )
}

@Composable
fun yAxisRotation(condition: Boolean): State<Float> {
    return reversibleAnimation(
        from = 0f,
        to = -360f,
        condition = condition,
        duration = 1000,
        easing = FastOutSlowInEasing
    )
}