package eu.curtisy.kwallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import eu.curtisy.kwallet.ui.screens.card.CardCreation
import eu.curtisy.kwallet.ui.screens.overview.Overview

object AppRoutes {
    const val CARD_OVERVIEW = "cardOverview"
    const val CARD_CREATION = "cardCreation"
}

@Composable
fun NavGraph(startDestination: String = AppRoutes.CARD_OVERVIEW) {
    val navController = rememberNavController()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppRoutes.CARD_OVERVIEW) {
            Overview(navController)
        }
        composable(AppRoutes.CARD_CREATION) {
            CardCreation(navController)
        }
    }
}