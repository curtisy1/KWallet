package eu.curtisy.kwallet.ui.main

import androidx.compose.material.MaterialTheme
import androidx.compose.runtime.Composable
import com.google.accompanist.insets.ProvideWindowInsets
import eu.curtisy.kwallet.ui.navigation.NavGraph

@Composable
fun KWalletApp() {
    ProvideWindowInsets {
        MaterialTheme {
            NavGraph()
        }
    }
}