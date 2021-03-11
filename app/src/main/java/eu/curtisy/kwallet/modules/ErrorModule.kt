package eu.curtisy.kwallet.modules

import eu.curtisy.kwallet.data.helper.ErrorHandlerImpl
import eu.curtisy.kwallet.interfaces.ErrorHandler
import org.koin.dsl.module

val errorModule = module {
    factory<ErrorHandler> { ErrorHandlerImpl() }
}