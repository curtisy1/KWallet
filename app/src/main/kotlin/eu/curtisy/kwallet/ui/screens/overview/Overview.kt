package eu.curtisy.kwallet.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import eu.curtisy.kwallet.data.repositories.PreviewCardRepositoryImpl
import eu.curtisy.kwallet.ui.components.list.HorizontalList
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.components.creditcard.Placeholder
import eu.curtisy.kwallet.ui.navigation.AppRoutes

@Composable
fun Overview(navController: NavHostController, viewModel: CardViewModel) {
    val cards = viewModel.creditCards

    Column(Modifier.padding(top = 80.dp)) {
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
                        CardContent(it)
                        Spacer(Modifier.width(5.dp))

                        if (cards.indexOf(it) == cards.size) {
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
fun PlaceholderRender(viewModel: CardViewModel, navController: NavHostController): Unit {
    Placeholder(
        onClick = {
            viewModel.addCard()
            navController.navigate(AppRoutes.CARD_CREATION)
        }
    )
}

@Composable
@Preview
private fun HomePreview() {
    val viewModel = CardViewModel(
        cardRepository = PreviewCardRepositoryImpl()
    )

    Overview(
        navController = rememberNavController(),
        viewModel = viewModel
    )
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

