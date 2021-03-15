package eu.curtisy.kwallet.injection

import com.squareup.sqldelight.android.AndroidSqliteDriver
import com.squareup.sqldelight.db.SqlDriver
import eu.curtisy.kwallet.data.WalletDatabase
import eu.curtisy.kwallet.data.repositories.CardRepository
import eu.curtisy.kwallet.data.repositories.CardRepositoryImpl
import eu.curtisy.kwallet.ui.screens.overview.OverviewViewModel
import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.core.context.startKoin
import org.koin.dsl.KoinAppDeclaration
import org.koin.dsl.module

fun initKoin(appDeclaration: KoinAppDeclaration = {}) = startKoin {
    appDeclaration()
    modules(coreModule)
}

val coreModule = module {
    single<SqlDriver> {
        AndroidSqliteDriver(WalletDatabase.Schema, get(), "KWallet.db")
    }
    single { WalletDatabase(get()) }
    single { get<WalletDatabase>().cardDetailsQueries }

    single<CardRepository> { CardRepositoryImpl(get()) }

    viewModel { OverviewViewModel(get()) }
}