package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.ui.animations.fadingAlpha
import eu.curtisy.kwallet.ui.animations.yAxisRotation
import eu.curtisy.kwallet.ui.components.CardView
import timber.log.Timber

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    card: Card,
    isEdit: Boolean = false,
    colorPickerOpen: Boolean = false,
    actionIcon: @Composable () -> Unit,
    updateCardFun: (card: Card) -> Unit = { }
) {
    var isFrontVisible by remember {
        mutableStateOf(true)
    }

    val frontLayerAlpha by fadingAlpha(
        condition = isFrontVisible
    )
    val backLayerAlpha by fadingAlpha(
        condition = !isFrontVisible
    )
    val cardRotation by yAxisRotation(
        condition = isFrontVisible,
    )

    Column(modifier = modifier) {
        CardView(
            modifier = Modifier.graphicsLayer(rotationY = cardRotation),
            backgroundColor = card.color.toColor(),
            onClick = {
                Timber.i("Card clicked. Flipping")
                isFrontVisible = !isFrontVisible
            }
        ) {
            val layoutModifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .graphicsLayer(alpha = if (isFrontVisible) frontLayerAlpha else backLayerAlpha)

            if (isFrontVisible) {
                CardFrontLayer(
                    modifier = layoutModifier,
                    card = card,
                    isEdit = isEdit,
                    contentVisible = !colorPickerOpen,
                    actionIcon = actionIcon,
                    updateCardFun = updateCardFun
                )
            } else {
                CardBackLayer(
                    modifier = layoutModifier,
                    card = card,
                    isEdit = isEdit,
                    updateCardFun = updateCardFun
                )
            }
        }
    }
}

@Composable
fun CardFrontLayer(
    modifier: Modifier = Modifier,
    card: Card,
    isEdit: Boolean,
    contentVisible: Boolean = true,
    actionIcon: @Composable () -> Unit,
    updateCardFun: (card: Card) -> Unit,
) {
    val (cardNumber, fullName, _, _, _, validMonth, validYear, _, isVisa) = card

    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        if (contentVisible) {
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                TextButton(
                    enabled = isEdit,
                    onClick = {
                        updateCardFun(card.copy(isVisa = !isVisa))
                    }) {
                    Text(text = if (isVisa) "VISA" else "MasterCard")
                }
                actionIcon()
            }
            Row(
                modifier = Modifier
                    .fillMaxWidth()
                    .wrapContentWidth(Alignment.CenterHorizontally)
            ) {
                BasicTextField(
                    readOnly = !isEdit,
                    enabled = isEdit,
                    value = cardNumber.toString(),
                    onValueChange = {
                        val newCardNumber = it.toLongOrNull()
                        if (newCardNumber != null) {
                            updateCardFun(card.copy(cardNumber = newCardNumber))
                        }
                    }
                )
            }
            Row(modifier = Modifier.height(IntrinsicSize.Min)) {
                BasicTextField(
                    modifier = Modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.Start),
                    readOnly = !isEdit,
                    enabled = isEdit,
                    value = fullName,
                    onValueChange = {
                        updateCardFun(card.copy(fullName = it))
                    }
                )
                Row(
                    modifier = modifier
                        .weight(1f)
                        .wrapContentWidth(Alignment.End)
                ) {
                    BasicTextField(
                        modifier = Modifier.width(IntrinsicSize.Min),
                        readOnly = !isEdit,
                        enabled = isEdit,
                        value = validMonth.toString(),
                        onValueChange = {
                            val newValidMonth = it.toShortOrNull()
                            if (newValidMonth != null) {
                                updateCardFun(card.copy(validMonth = newValidMonth))
                            }
                        }
                    )
                    Text(
                        modifier = Modifier.width(IntrinsicSize.Min),
                        text = "/",
                    )
                    BasicTextField(
                        modifier = Modifier.width(IntrinsicSize.Min),
                        readOnly = !isEdit,
                        enabled = isEdit,
                        value = validYear.toString(),
                        onValueChange = {
                            val newValidYear = it.toIntOrNull()
                            if (newValidYear != null) {
                                updateCardFun(card.copy(validYear = newValidYear))
                            }
                        }
                    )
                }
            }
        } else {
            actionIcon()
        }
    }
}

@Composable
fun CardBackLayer(
    modifier: Modifier = Modifier,
    card: Card,
    isEdit: Boolean,
    updateCardFun: (card: Card) -> Unit,
) {
    val (_, _, cvc, iban, bic) = card

    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEdit,
            enabled = isEdit,
            value = iban,
            onValueChange = {
                updateCardFun(card.copy(iban = it))
            }
        )
        Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
            BasicTextField(
                readOnly = !isEdit,
                enabled = isEdit,
                value = bic,
                onValueChange = {
                    updateCardFun(card.copy(bic = it))
                },
            )
        }
        BasicTextField(
            modifier = Modifier.fillMaxWidth(),
            readOnly = !isEdit,
            enabled = isEdit,
            value = cvc.toString(),
            onValueChange = {
                val newCvc = it.toShortOrNull()
                if (newCvc != null) {
                    updateCardFun(card.copy(cvc = newCvc))
                }
            }
        )
    }
}

@Composable
@Preview
private fun CardContentPreview() {
    CardContent(
        card = Card(
            iban = "DE 1234567890",
            isVisa = true,
            bic = "BELADEBXXX",
            fullName = "Alexander Oberländer",
            cardNumber = 1234567890,
            cvc = 123,
            validMonth = 12,
            validYear = 21,
            color = "#FFFFFF"
        ),
        actionIcon = {
            IconButton(onClick = { }) {
                Icon(Icons.Default.MoreHoriz, "More")
            }
        },
    )
}

@Composable
@Preview
private fun CardFrontLayerPreview() {
    CardFrontLayer(
        card = Card(
            isVisa = true,
            fullName = "Some Cool Dude",
            cardNumber = 1234567890,
            validMonth = 12,
            validYear = 21,
            iban = "DE 1234567890",
            bic = "BELADEBXXX",
            cvc = 123,
            color = "#FFFFFF"
        ),
        isEdit = true,
        actionIcon = { },
        updateCardFun = { }
    )
}

@Composable
@Preview
private fun CardBackLayerPreview() {
    CardBackLayer(
        card = Card(
            isVisa = true,
            fullName = "Some Cool Dude",
            cardNumber = 1234567890,
            validMonth = 12,
            validYear = 21,
            iban = "DE 1234567890",
            bic = "BELADEBXXX",
            cvc = 123,
            color = "#FFFFFF"
        ),
        isEdit = false,
        updateCardFun = { }
    )
}