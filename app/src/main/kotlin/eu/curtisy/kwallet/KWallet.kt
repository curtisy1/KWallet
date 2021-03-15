package eu.curtisy.kwallet

import android.app.Application
import eu.curtisy.kwallet.injection.*
import org.koin.android.BuildConfig
import org.koin.android.ext.koin.androidContext
import org.koin.android.ext.koin.androidLogger
import org.koin.core.context.startKoin
import timber.log.Timber

class KWallet : Application() {
    override fun onCreate() {
        super.onCreate()
        initKoin(appDeclaration =  {
            androidLogger()
            androidContext(this@KWallet)
        })

        if (BuildConfig.DEBUG) {
            Timber.plant(Timber.DebugTree())
        }
    }
}