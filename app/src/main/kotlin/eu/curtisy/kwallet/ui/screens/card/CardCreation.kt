package eu.curtisy.kwallet.ui.screens.card

import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
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
            CardView(accentColor = Color.LightGray) {
                CardContent()
            }
        }
    )
}

@Preview
@Composable
private fun CardCreationPreview() {
    CardCreation(navController = rememberNavController())
}