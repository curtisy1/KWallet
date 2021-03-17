package eu.curtisy.kwallet.ui.screens.overview

import androidx.compose.foundation.layout.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.navigate
import androidx.navigation.compose.rememberNavController
import eu.curtisy.kwallet.ui.components.list.HorizontalList
import eu.curtisy.kwallet.extensions.toColor
import eu.curtisy.kwallet.ui.components.CardView
import eu.curtisy.kwallet.ui.components.creditcard.CardContent
import eu.curtisy.kwallet.ui.components.creditcard.Placeholder
import eu.curtisy.kwallet.ui.navigation.AppRoutes
import org.koin.androidx.compose.getViewModel
import timber.log.Timber

@Composable
fun Overview(navController: NavHostController) {
    val viewModel = getViewModel<OverviewViewModel>()
    val cards = viewModel.creditCards
    val payments = viewModel.payments

    Column(Modifier.padding(top = 80.dp)) {
        Row {
            if (cards.isEmpty()) {
                Placeholder(onClick = {
                    Timber.i("Button was pressed")
                    navController.navigate(AppRoutes.CARD_CREATION)
                })
            } else {
                HorizontalList(
                    items = cards,
                    generatorFunc = {
                        CardView(backgroundColor = it.color.toColor()) {
                            CardContent(
                                cardNumber = it.cardNumber,
                                cardHolder = it.fullName,
                                cvc = it.cvc,
                                iban = it.iban,
                                bic = it.bic,
                                validMonth = it.validMonth,
                                validYear = it.validYear,
                                isVisa = it.isVisa
                            )
                        }
                        Spacer(Modifier.width(5.dp))

                        if (cards.indexOf(it) == cards.size) {
                            Placeholder(onClick = {
                                Timber.i("Button was pressed")
                                navController.navigate(AppRoutes.CARD_CREATION)
                            })
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
@Preview
private fun HomePreview() {
    Overview(rememberNavController())
}

