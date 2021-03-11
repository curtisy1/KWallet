package eu.curtisy.kwallet.modules

import eu.curtisy.kwallet.data.repositories.CoinRepositoryImpl
import eu.curtisy.kwallet.interfaces.CoinRepository
import org.koin.dsl.module

val repositoryModule = module {
    single<CoinRepository> { CoinRepositoryImpl() }
}