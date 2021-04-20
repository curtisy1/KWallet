package eu.curtisy.kwallet.ui.screens.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.material.icons.filled.Palette
import androidx.compose.material.icons.filled.Save
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.systemBarsPadding
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.data.repositories.PreviewCardRepositoryImpl
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.extensions.toHEX
import eu.curtisy.kwallet.ui.components.appbars.TopNavBar
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.components.utils.ColorPicker
import eu.curtisy.kwallet.ui.navigation.AppRoutes
import eu.curtisy.kwallet.ui.screens.overview.CardViewModel

@Composable
fun CardCreation(navController: NavHostController, viewModel: CardViewModel) {
    val (cardState, saveCardState) = remember { mutableStateOf(viewModel.selectedCard) }
    val (colorPickerOpen, onColorPickerOpen) = remember { mutableStateOf(false) }

    Scaffold(
        modifier = Modifier.systemBarsPadding(),
        topBar = {
            TopNavBar(
                text = "Create new card",
                onBackPressed = { navController.navigate(AppRoutes.CARD_OVERVIEW) })
        },
        content = {
            Box(
                modifier = Modifier.fillMaxSize(),
            ) {
                Column(
                    modifier = Modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 50.dp)
                ) {
                    CardContent(
                        modifier = Modifier,
                        card = cardState!!,
                        isEdit = true,
                        colorPickerOpen = colorPickerOpen,
                        actionIcon = {
                            EditActionIcon(
                                card = cardState,
                                saveCardState = saveCardState,
                                colorPickerOpen = colorPickerOpen,
                                onColorPickerOpen = onColorPickerOpen
                            )
                        },
                        updateCardFun = saveCardState
                    )
                }
                Spacer(Modifier.height(20.dp))
                Column(
                    modifier = Modifier
                        .align(Alignment.BottomEnd)
                        .padding(bottom = 5.dp, end = 5.dp)
                ) {
                    FloatingActionButton(
                        onClick = {
                            viewModel.saveCard(cardState!!)
                            navController.navigate(AppRoutes.CARD_OVERVIEW)
                        }
                    ) {
                        Icon(Icons.Filled.Save, "Save card")
                    }
                }
            }
        }
    )
}


@Composable
fun EditActionIcon(
    card: Card,
    saveCardState: (card: Card?) -> Unit,
    colorPickerOpen: Boolean,
    onColorPickerOpen: (open: Boolean) -> Unit
) {
    val (selectedColor, onColorSelected) = remember { mutableStateOf(card.color.toColor()) }

    Box(modifier = Modifier) {
        if (colorPickerOpen) {
            ColorPicker(
                buttonSize = 20,
                selectedColor = selectedColor,
                onColorSelected = {
                    onColorSelected(it)
                    saveCardState(card.copy(color = it.toHEX()))
                },
                open = colorPickerOpen,
                onOpen = {
                    onColorPickerOpen(!colorPickerOpen)
                }
            )
        } else {
            IconButton(
                onClick = { onColorPickerOpen(!colorPickerOpen) },
                modifier = Modifier
                    .padding(2.dp)
                    .size(20.dp)
            ) {
                Icon(Icons.Default.Palette, "Choose color")
            }
        }
    }
}

@Preview
@Composable
private fun CardCreationPreview() {
    val viewModel = CardViewModel(
        cardRepository = PreviewCardRepositoryImpl()
    )
    viewModel.addCard()

    CardCreation(
        navController = rememberNavController(),
        viewModel = viewModel
    )
}