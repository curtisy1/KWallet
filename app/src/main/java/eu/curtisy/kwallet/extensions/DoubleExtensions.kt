package eu.curtisy.kwallet.extensions

import java.text.NumberFormat
import java.util.*

fun Double?.currency(): String {
    val ptBR = Locale("pt", "BR")
    val formatter = NumberFormat.getCurrencyInstance(ptBR)
    return formatter.format(this ?: 0.0)
}

fun Double.format(digits: Int) = "%.${digits}f".format(this)