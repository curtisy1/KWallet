package eu.curtisy.kwallet.modules

import eu.curtisy.kwallet.data.usecase.GetCoinUseCaseImpl
import eu.curtisy.kwallet.interfaces.GetCoinUseCase
import org.koin.dsl.module

val useCaseModule = module {
    single<GetCoinUseCase> { GetCoinUseCaseImpl(get(), get()) }
}