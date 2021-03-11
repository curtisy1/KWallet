package eu.curtisy.kwallet.ui.main

import android.os.Bundle
import androidx.activity.compose.setContent
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.WindowCompat
import eu.curtisy.kwallet.ui.home.Home
import eu.curtisy.kwallet.ui.home.HomeViewModel
import eu.curtisy.kwallet.ui.theme.KWalletAppTheme
import dev.chrisbanes.accompanist.insets.ProvideWindowInsets
import org.koin.android.viewmodel.ext.android.viewModel

class MainActivity : AppCompatActivity() {
    private val viewModel: HomeViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        WindowCompat.setDecorFitsSystemWindows(window, false)

        setContent {
            ProvideWindowInsets {
                KWalletAppTheme {
                    Home(viewModel = viewModel)
                }
            }
        }
    }
}