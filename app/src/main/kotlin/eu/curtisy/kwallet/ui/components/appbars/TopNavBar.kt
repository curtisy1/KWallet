package eu.curtisy.kwallet.ui.components.appbars

import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview

@Composable
fun TopNavBar(
    text: String,
    onBackPressed: () -> Unit
) {
    TopAppBar(
        title = {
            Text(text = text)
        },
        navigationIcon = {
            IconButton(onClick = onBackPressed) {
                Icon(Icons.Filled.ArrowBack, "")
            }
        }
    )
}

@Composable
@Preview
private fun TopNavBarPreview() {
    TopNavBar(text = "Preview", onBackPressed = {  })
}