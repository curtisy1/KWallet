package eu.curtisy.kwallet.ui.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.compose.*
import eu.curtisy.kwallet.ui.screens.card.CardCreation
import eu.curtisy.kwallet.ui.screens.overview.CardViewModel
import eu.curtisy.kwallet.ui.screens.overview.Overview
import org.koin.androidx.compose.getViewModel

object AppRoutes {
    const val CARD_OVERVIEW = "cardOverview"
    const val CARD_CREATION = "cardCreation"
}

@Composable
fun NavGraph(startDestination: String = AppRoutes.CARD_OVERVIEW) {
    val navController = rememberNavController()
    // This needs to reside here until koin has support for Compose state
    // TODO: Maybe move away from viewmodels and inject a state singleton instead?
    val cardViewModel = getViewModel<CardViewModel>()

    NavHost(
        navController = navController,
        startDestination = startDestination
    ) {
        composable(AppRoutes.CARD_OVERVIEW) {
            Overview(navController, cardViewModel)
        }
        composable(AppRoutes.CARD_CREATION) {
            CardCreation(navController, cardViewModel)
        }
    }
}