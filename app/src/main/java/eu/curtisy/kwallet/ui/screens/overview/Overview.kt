package eu.curtisy.kwallet.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.material.FloatingActionButton
import androidx.compose.material.Icon
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import eu.curtisy.kwallet.ui.components.list.HorizontalList
import eu.curtisy.kwallet.ui.components.list.VerticalList
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.ui.components.CardView
import eu.curtisy.kwallet.ui.components.PaymentItem
import eu.curtisy.kwallet.ui.navigation.AppRoutes
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun Overview(navController: NavHostController) {
    val viewModel = getViewModel<OverviewViewModel>()
    val coins = viewModel.creditCards
    val payments = viewModel.payments

    Column(Modifier.padding(top = 80.dp)) {
        Row {
            FloatingActionButton(onClick = {
                Timber.i("Button was pressed")
                navController.navigate(AppRoutes.CARD_CREATION)
            }) {
                Icon(Icons.Filled.Add, Color.White.toString())
            }
            HorizontalList(
                items = coins,
                generatorFunc = {
                    CardView(accentColor = it.color.toColor())
                    Spacer(Modifier.width(5.dp))
                },
                Modifier
                    .fillMaxWidth()
            )
        }
    }
    Column(Modifier.padding(top = 240.dp)) {
        VerticalList(
            items = payments,
            generatorFunc = {
                PaymentItem(payment = it, modifier = Modifier.padding(bottom = 24.dp))
            },
            Modifier
                .fillMaxWidth()
                .fillMaxHeight()
        )
    }
}

@Composable
@Preview
private fun HomePreview() {
    Overview(rememberNavController())
}

