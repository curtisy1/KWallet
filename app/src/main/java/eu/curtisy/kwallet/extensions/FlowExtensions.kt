package eu.curtisy.kwallet.extensions

import eu.curtisy.kwallet.data.entities.Result
import eu.curtisy.kwallet.interfaces.ErrorHandler
import kotlinx.coroutines.CoroutineExceptionHandler
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map
import kotlin.coroutines.CoroutineContext

fun <T> Flow<T>.handleResult(errorHandler: ErrorHandler, coroutineExceptionHandler: CoroutineExceptionHandler? = null, coroutineContext: CoroutineContext? = null): Flow<Result<T>> {
    return this
        .map {
            val result: Result<T> = Result.Success(it)
            result
        }
        .catch {
            if(coroutineExceptionHandler!=null && coroutineContext!=null){
                coroutineExceptionHandler.handleException(coroutineContext, it)
            } else{
                emit(Result.Error(errorHandler.getError(it)))
            }
        }
}