package eu.curtisy.kwallet.extensions

import java.text.SimpleDateFormat
import java.util.*

fun Long?.getDateFormatted(): String {
    return SimpleDateFormat("dd/MM/yyyy", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getHourFormatted(): String{
    return SimpleDateFormat("HH:mm", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getDayOfWeek(): String{
    return SimpleDateFormat("EEEE", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getDayOfWeekShort(): String{
    return SimpleDateFormat("EE", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getDay(): String {
    return SimpleDateFormat("dd", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getMonth(): String {
    return SimpleDateFormat("MMMM", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getYear(): String {
    return SimpleDateFormat("yyyy", Locale.getDefault()).format(this.fromUnixDate())
}

fun Long?.getDayFormatted(): String {
    val date = this.fromUnixDate()
    return when(true){
        date.isYesterday() -> "Ontem, ${getHourFormatted()}"
        date.isToday() -> "Hoje, ${getHourFormatted()}"
        date.isTomorrow() -> "Amanhã, ${getHourFormatted()}"
        date.isOfWeek() -> "${getDayOfWeek()}, ${getHourFormatted()}"
        date.isOfYear() -> "${getDay()} de ${getMonth()}"
        else -> "${getDay()} de ${getMonth()} de ${getYear()}"
    }
}

fun Long?.fromUnixDate(): Date {
    return if(this!=null)Date(this*1000L) else Date()
}