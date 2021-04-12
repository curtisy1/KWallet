package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Text
import androidx.compose.material.TextButton
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
import eu.curtisy.kwallet.ui.components.utils.ColorPicker
import timber.log.Timber

@Composable
fun CardContent(
    modifier: Modifier = Modifier,
    card: Card,
    isEdit: Boolean = false,
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
            Box(
                modifier = Modifier.fillMaxSize()
            ) {
                CardFrontLayer(
                    modifier = Modifier.graphicsLayer(alpha = frontLayerAlpha),
                    card = card,
                    isEdit = isEdit,
                    updateCardFun = updateCardFun
                )
                CardBackLayer(
                    modifier = Modifier.graphicsLayer(alpha = backLayerAlpha),
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
    updateCardFun: (card: Card) -> Unit,
) {
    val (cardNumber, fullName, _, _, _, validMonth, validYear, color, isVisa) = card
    val (selectedColor, onColorSelected) = remember { mutableStateOf(color.toColor()) }
    val (colorPickerOpen, onColorPickerOpen) = remember { mutableStateOf(false) }

    // TODO: Ideally this would be 3 Columns with 1 row each
    // Or 1 Column with 3 rows.. Both don't seem to work though
    // Fiddle around more once the initial state of design is done
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            if (isEdit) {
                TextButton(onClick = {
                    updateCardFun(card.copy(isVisa = !isVisa))
                }) {
                    Text(text = if (isVisa) "VISA" else "MasterCard")
                }
            } else {
                Text(text = if (isVisa) "VISA" else "MasterCard")
            }
        }
        Column(modifier = Modifier.align(Alignment.TopEnd)) {
            ColorPicker(
                buttonSize = 20,
                selectedColor = selectedColor,
                onColorSelected = {
                    onColorSelected(it)
                    updateCardFun(card.copy(color = it.toString()))
                },
                open = colorPickerOpen,
                onOpen = {
                    onColorPickerOpen(!colorPickerOpen)
                }
            )
        }
        Column(modifier = Modifier.align(Alignment.Center)) {
            if (isEdit) {
                BasicTextField(
                    value = cardNumber.toString(),
                    onValueChange = {
                        val newCardNumber = it.toLongOrNull()
                        if (newCardNumber != null) {
                            updateCardFun(card.copy(cardNumber = newCardNumber))
                        }
                    }
                )
            } else {
                Text(
                    text = cardNumber.toString(),
                )
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            if (isEdit) {
                BasicTextField(
                    value = fullName,
                    onValueChange = {
                        updateCardFun(card.copy(fullName = it))
                    }
                )
            } else {
                Text(
                    text = fullName,
                )
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
            if (isEdit) {
                Row {
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(0.05f),
                        value = validMonth.toString(),
                        onValueChange = {
                            val newValidMonth = it.toShortOrNull()
                            if (newValidMonth != null) {
                                updateCardFun(card.copy(validMonth = newValidMonth))
                            }
                        }
                    )
                    Text(
                        text = "/",
                    )
                    BasicTextField(
                        modifier = Modifier.fillMaxWidth(0.1f),
                        value = validYear.toString(),
                        onValueChange = {
                            val newValidYear = it.toIntOrNull()
                            if (newValidYear != null) {
                                updateCardFun(card.copy(validYear = newValidYear))
                            }
                        }
                    )
                }
            } else {
                Text(
                    text = "${validMonth}/${validYear}",
                )
            }
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
    Box(
        modifier = modifier
            .fillMaxSize()
            .padding(5.dp)
    ) {
        Column(modifier = Modifier.align(Alignment.TopStart)) {
            if (isEdit) {
                BasicTextField(
                    value = iban,
                    onValueChange = {
                        updateCardFun(card.copy(iban = it))
                    }
                )
            } else {
                Text(
                    text = iban,
                )
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomStart)) {
            if (isEdit) {
                BasicTextField(
                    value = bic,
                    onValueChange = {
                        updateCardFun(card.copy(bic = it))
                    }
                )
            } else {
                Text(
                    text = bic,
                )
            }
        }
        Column(modifier = Modifier.align(Alignment.BottomEnd)) {
            if (isEdit) {
                BasicTextField(
                    value = cvc.toString(),
                    onValueChange = {
                        val newCvc = it.toShortOrNull()
                        if (newCvc != null) {
                            updateCardFun(card.copy(cvc = newCvc))
                        }
                    }
                )
            } else {
                Text(
                    text = cvc.toString(),
                )
            }
        }
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
            fullName = "Some Cool Dude",
            cardNumber = 1234567890,
            cvc = 123,
            validMonth = 12,
            validYear = 21,
            color = "#FFFFFF"
        )
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