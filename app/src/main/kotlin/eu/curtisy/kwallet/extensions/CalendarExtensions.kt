package eu.curtisy.kwallet.extensions

import java.util.*

fun Calendar.resetHour() {
    this.set(Calendar.HOUR_OF_DAY, 0)
    this.set(Calendar.MINUTE, 0)
    this.set(Calendar.SECOND, 0)
}