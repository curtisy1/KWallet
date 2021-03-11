package eu.curtisy.kwallet

import android.app.Application
import eu.curtisy.kwallet.modules.errorModule
import eu.curtisy.kwallet.modules.repositoryModule
import eu.curtisy.kwallet.modules.viewModelModule
import eu.curtisy.kwallet.modules.useCaseModule
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class KWallet : Application() {
    override fun onCreate() {
        super.onCreate()
        startKoin {
            androidLogger()
            androidContext(this@KWallet)
            modules(
                listOf(
                    useCaseModule,
                    repositoryModule,
                    errorModule,
                    viewModelModule
                )
            )
        }

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}