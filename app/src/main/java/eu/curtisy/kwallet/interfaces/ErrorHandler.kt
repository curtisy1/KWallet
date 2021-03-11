package eu.curtisy.kwallet.interfaces

import eu.curtisy.kwallet.data.entities.ErrorEntity

interface ErrorHandler {
    suspend fun getError(throwable: Throwable): ErrorEntity
}