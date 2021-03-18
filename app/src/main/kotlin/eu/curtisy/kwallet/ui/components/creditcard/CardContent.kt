package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.animation.core.*
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.ui.components.CardView

@Composable
fun CardContent(
    cardNumber: Long,
    cardHolder: String,
    cvc: Short,
    iban: String,
    bic: String,
    validMonth: Short,
    validYear: Int,
    isVisa: Boolean,
    color: String? = null
) {
    var isFrontVisible by remember {
        mutableStateOf(true)
    }

    // TODO: Refactor to make these dynamic
    val frontLayerAlpha: Float by animateFloatAsState(
        targetValue = if (isFrontVisible) 1f else 0f,
        // configure the animation duration and easing
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    val backLayerAlpha: Float by animateFloatAsState(
        targetValue = if (isFrontVisible) 0f else 1f,
        // configure the animation duration and easing
        animationSpec = tween(durationMillis = 500, easing = FastOutSlowInEasing)
    )
    val cardRotation: Float by animateFloatAsState(
        // TODO: Double animation. If changing to single we need to reverse the content in the back layer
        targetValue = if (isFrontVisible) 0f else -360f,
        // configure the animation duration and easing
        animationSpec = tween(durationMillis = 1000, easing = FastOutSlowInEasing)
    )

    CardView(
        modifier = Modifier.graphicsLayer(rotationY = cardRotation),
        backgroundColor = color?.toColor(),
        onClick = {
            isFrontVisible = !isFrontVisible
        }
    ) {
        Column(
            verticalArrangement = Arrangement.SpaceEvenly,
        ) {
            CardFrontLayer(
                modifier = Modifier.graphicsLayer(alpha = frontLayerAlpha),
                cardNumber = cardNumber,
                cardHolder = cardHolder,
                validMonth = validMonth,
                validYear = validYear,
                isVisa = isVisa
            )
            CardBackLayer(
                modifier = Modifier.graphicsLayer(alpha = backLayerAlpha),
                iban = iban,
                bic = bic,
                cvc = cvc
            )
        }
    }
}

@Composable
fun CardFrontLayer(
    modifier: Modifier = Modifier,
    cardNumber: Long,
    cardHolder: String,
    validMonth: Short,
    validYear: Int,
    isVisa: Boolean,
) {
    Column(
        modifier = modifier.padding(start = 5.dp)
    ) {
        Column {
            Text(
                text = if (isVisa) "VISA" else "MasterCard",
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column() {
            Text(
                text = cardNumber.toString(),
            )
            Text(
                text = "${validMonth}/${validYear}",
            )
        }
    }
}

@Composable
fun CardBackLayer(
    modifier: Modifier = Modifier,
    iban: String,
    bic: String,
    cvc: Short,
) {
    Column(
        modifier = modifier.padding(start = 5.dp)
    ) {
        Column {
            Text(
                text = iban,
            )
        }
        Spacer(modifier = Modifier.height(25.dp))
        Column() {
            Text(
                text = bic,
            )
            Text(
                text = cvc.toString(),
            )
        }
    }
}

@Composable
@Preview
private fun CardContentPreview() {
    CardContent(
        iban = "DE 1234567890",
        isVisa = true,
        bic = "BELADEBXXX",
        cardHolder = "Some Cool Dude",
        cardNumber = 1234567890,
        cvc = 123,
        validMonth = 12,
        validYear = 21,
    )
}

@Composable
@Preview
private fun CardFrontLayerPreview() {
    CardFrontLayer(
        isVisa = true,
        cardHolder = "Some Cool Dude",
        cardNumber = 1234567890,
        validMonth = 12,
        validYear = 21
    )
}

@Composable
@Preview
private fun CardBackLayerPreview() {
    CardBackLayer(
        iban = "DE 1234567890",
        bic = "BELADEBXXX",
        cvc = 123,
    )
}