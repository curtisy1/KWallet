package eu.curtisy.kwallet.modules

import eu.curtisy.kwallet.ui.screens.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module

val viewModelModule = module {
    viewModel { OverviewViewModel(get()) }
}