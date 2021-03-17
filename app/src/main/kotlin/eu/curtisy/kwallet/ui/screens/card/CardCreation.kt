package eu.curtisy.kwallet.ui.screens.card

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import eu.curtisy.kwallet.ui.components.CardView
import eu.curtisy.kwallet.ui.components.appbars.TopNavBar
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.navigation.AppRoutes

@Composable
fun CardCreation(navController: NavHostController) {
    Scaffold(
        topBar = {
            TopNavBar(
                text = "Create new card",
                onBackPressed = { navController.navigate(AppRoutes.CARD_OVERVIEW) })
        },
        content = {
            CardView() {
                CardContent(
                    iban = "DE 1234567890",
                    isVisa = true,
                    bic = "BELADEBXXX",
                    cardHolder = "Some Cool Dude",
                    cardNumber = 1234567890,
                    cvc = 123,
                    validMonth = 12,
                    validYear = 21
                )
            }
        }
    )
}

@Preview
@Composable
private fun CardCreationPreview() {
    CardCreation(navController = rememberNavController())
}