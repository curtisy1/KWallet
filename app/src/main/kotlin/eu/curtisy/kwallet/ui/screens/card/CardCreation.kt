package eu.curtisy.kwallet.ui.screens.card

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import eu.curtisy.kwallet.data.repositories.PreviewCardRepositoryImpl
import eu.curtisy.kwallet.ui.components.appbars.TopNavBar
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.navigation.AppRoutes
import eu.curtisy.kwallet.ui.screens.overview.CardViewModel

@Composable
fun CardCreation(navController: NavHostController, viewModel: CardViewModel) {
    val (cardState, saveCardState) = remember { mutableStateOf(viewModel.selectedCard) }

    Scaffold(
        topBar = {
            TopNavBar(
                text = "Create new card",
                onBackPressed = { navController.navigate(AppRoutes.CARD_OVERVIEW) })
        },
        content = {
            Column {
                CardContent(
                    card = cardState!!,
                    isEdit = true,
                    updateCardFun = saveCardState
                )
                Spacer(Modifier.height(20.dp))
                TextButton(onClick = {
                    viewModel.saveCard(cardState)
                    navController.navigate(AppRoutes.CARD_OVERVIEW)
                }) {
                    Text("Save card")
                }
            }
        }
    )
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