package eu.curtisy.kwallet.ui.components.creditcard

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.text.BasicTextField
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.More
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.graphicsLayer
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.extensions.toHEX
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
            val layoutModifier = Modifier
                .fillMaxSize()
                .padding(5.dp)
                .graphicsLayer(alpha = if (isFrontVisible) frontLayerAlpha else backLayerAlpha)

            if (isFrontVisible) {
                CardFrontLayer(
                    modifier = layoutModifier,
                    card = card,
                    isEdit = isEdit,
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
    updateCardFun: (card: Card) -> Unit,
) {
    val (cardNumber, fullName, _, _, _, validMonth, validYear, color, isVisa) = card
    val (selectedColor, onColorSelected) = remember { mutableStateOf(color.toColor()) }
    val (colorPickerOpen, onColorPickerOpen) = remember { mutableStateOf(false) }

    Column(verticalArrangement = Arrangement.SpaceBetween, modifier = modifier) {
        if (colorPickerOpen && isEdit) {
            ColorPicker(
                buttonSize = 20,
                selectedColor = selectedColor,
                onColorSelected = {
                    onColorSelected(it)
                    updateCardFun(card.copy(color = it.toHEX()))
                },
                open = colorPickerOpen,
                onOpen = {
                    onColorPickerOpen(!colorPickerOpen)
                }
            )
        } else {
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
                if (isEdit) {
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
                } else {
                    IconButton(
                        onClick = { /*TODO*/ },
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .size(20.dp)
                    ) {
                        Icon(Icons.Default.More, "Options")
                    }
                }
            }
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()) {
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
            Row(
                horizontalArrangement = Arrangement.SpaceBetween,
                modifier = Modifier.fillMaxWidth()
            ) {
                BasicTextField(
                    readOnly = !isEdit,
                    enabled = isEdit,
                    value = fullName,
                    onValueChange = {
                        updateCardFun(card.copy(fullName = it))
                    }
                )
                Row {
                    BasicTextField(
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
                        text = "/",
                    )
                    BasicTextField(
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