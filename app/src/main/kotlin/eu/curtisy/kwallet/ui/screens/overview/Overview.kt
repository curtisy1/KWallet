package eu.curtisy.kwallet.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.MoreHoriz
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import com.google.accompanist.insets.systemBarsPadding
import eu.curtisy.kwallet.data.Card
import eu.curtisy.kwallet.data.repositories.PreviewCardRepositoryImpl
import eu.curtisy.kwallet.ui.components.list.HorizontalList
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.components.creditcard.Placeholder
import eu.curtisy.kwallet.ui.navigation.AppRoutes

@Composable
fun Overview(navController: NavHostController, viewModel: CardViewModel) {
    val cards = viewModel.creditCards

    Column(Modifier.systemBarsPadding()) {
        Row {
            if (cards.isEmpty()) {
                PlaceholderRender(
                    viewModel = viewModel,
                    navController = navController
                )
            } else {
                HorizontalList(
                    items = cards,
                    generatorFunc = {
                        CardContent(card = it, actionIcon = {
                            ViewActionIcon(it, viewModel, navController)
                        })
                        Spacer(Modifier.width(5.dp))

                        if (cards.indexOf(it) == cards.size - 1) {
                            PlaceholderRender(
                                viewModel = viewModel,
                                navController = navController
                            )
                        }
                    },
                    Modifier
                        .fillMaxWidth()
                )
            }
        }
    }
}

@Composable
fun PlaceholderRender(viewModel: CardViewModel, navController: NavHostController) {
    Placeholder(
        onClick = {
            viewModel.addCard()
            navController.navigate(AppRoutes.CARD_CREATION)
        }
    )
    Spacer(Modifier.width(5.dp))
}

@Composable
@Preview
private fun HomePreview() {
    val viewModel = CardViewModel(
        cardRepository = PreviewCardRepositoryImpl()
    )

    viewModel.creditCards = listOf(
        Card(
            id = 1,
            iban = "DE 1234567890",
            cardNumber = 1234567890,
            isVisa = true,
            validMonth = 1,
            validYear = 2345,
            fullName = "Cool Dude",
            bic = "XXXXXX",
            cvc = 123,
            color = "#FFFFFF",
        ),
        Card(
            id = 1,
            iban = "DE 1234567890",
            cardNumber = 1234567890,
            isVisa = false,
            validMonth = 1,
            validYear = 2345,
            fullName = "Cool Dude",
            bic = "XXXXXX",
            cvc = 123,
            color = "#FFFFFF",
        ),
    )

    Overview(
        navController = rememberNavController(),
        viewModel = viewModel
    )
}

@Composable
fun ViewActionIcon(card: Card, viewModel: CardViewModel, navController: NavHostController) {
    val (menuExpanded, onMenuExpanded) = remember { mutableStateOf(false) }

    Box(modifier = Modifier) {
        IconButton(
            onClick = { onMenuExpanded(!menuExpanded) },
            modifier = Modifier
                .padding(2.dp)
                .size(20.dp)
        ) {
            Icon(Icons.Default.MoreHoriz, "Options")
        }
        DropdownMenu(expanded = menuExpanded, onDismissRequest = { onMenuExpanded(false) }) {
            DropdownMenuItem(onClick = {
                onMenuExpanded(false)
                viewModel.selectedCard = card
                navController.navigate(AppRoutes.CARD_CREATION)
            }) {
                Text("Edit")
            }
            DropdownMenuItem(onClick = {
                onMenuExpanded(false)
                viewModel.deleteCard(card)
            }) {
                Text("Delete")
            }
        }
    }
}

@Composable
@Preview
private fun PlaceholderRenderPreview() {
    val viewModel = CardViewModel(
        cardRepository = PreviewCardRepositoryImpl()
    )

    PlaceholderRender(
        navController = rememberNavController(),
        viewModel = viewModel
    )
}

