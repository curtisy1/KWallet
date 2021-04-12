package eu.curtisy.kwallet.ui.screens.card

import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
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
import eu.curtisy.kwallet.data.repositories.PreviewCardRepositoryImpl
import eu.curtisy.kwallet.ui.components.appbars.TopNavBar
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.navigation.AppRoutes
import eu.curtisy.kwallet.ui.screens.overview.CardViewModel

@Composable
fun CardCreation(navController: NavHostController, viewModel: CardViewModel) {
    val (cardState, saveCardState) = remember { mutableStateOf(viewModel.selectedCard) }

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
                Column(modifier = Modifier.align(Alignment.TopCenter).padding(top = 50.dp)) {
                    CardContent(
                        modifier = Modifier,
                        card = cardState!!,
                        isEdit = true,
                        updateCardFun = saveCardState
                    )
                }
                Spacer(Modifier.height(20.dp))
                Column(modifier = Modifier.align(Alignment.BottomEnd).padding(bottom = 5.dp, end = 5.dp)) {
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