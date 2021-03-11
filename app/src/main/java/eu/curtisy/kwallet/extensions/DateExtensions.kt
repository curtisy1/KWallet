package eu.curtisy.kwallet.extensions

import java.util.*

fun Date?.isToday(): Boolean {
    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.resetHour()

    val calDiff = Calendar.getInstance()
    calDiff.timeInMillis = this?.time?:0L
    calDiff.resetHour()

    return cal.get(Calendar.DAY_OF_MONTH) == calDiff.get(Calendar.DAY_OF_MONTH) &&
            cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH) &&
            cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
}

fun Date?.isYesterday(): Boolean {
    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.add(Calendar.DAY_OF_MONTH, -1)
    cal.resetHour()

    val calDiff = Calendar.getInstance()
    calDiff.timeInMillis = this?.time?:0L
    calDiff.resetHour()

    return cal.get(Calendar.DAY_OF_MONTH) == calDiff.get(Calendar.DAY_OF_MONTH) &&
            cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH) &&
            cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
}

fun Date?.isTomorrow(): Boolean {
    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.add(Calendar.DAY_OF_MONTH, +1)
    cal.resetHour()

    val calDiff = Calendar.getInstance()
    calDiff.timeInMillis = this?.time?:0L
    calDiff.resetHour()

    return cal.get(Calendar.DAY_OF_MONTH) == calDiff.get(Calendar.DAY_OF_MONTH) &&
            cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH) &&
            cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
}

fun Date?.isOfWeek(): Boolean {
    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.resetHour()

    val calDiff = Calendar.getInstance()
    calDiff.timeInMillis = this?.time?:0L
    calDiff.resetHour()
    return cal.get(Calendar.WEEK_OF_MONTH) == calDiff.get(Calendar.WEEK_OF_MONTH)
}

fun Date?.isOfMonth(): Boolean {
    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.resetHour()

    val calDiff = Calendar.getInstance()
    calDiff.timeInMillis = this?.time?:0L
    calDiff.resetHour()
    return cal.get(Calendar.MONTH) == calDiff.get(Calendar.MONTH)
}

fun Date?.isOfYear(): Boolean {
    val cal = Calendar.getInstance()
    cal.timeInMillis = System.currentTimeMillis()
    cal.resetHour()

    val calDiff = Calendar.getInstance()
    calDiff.timeInMillis = this?.time?:0L
    calDiff.resetHour()
    return cal.get(Calendar.YEAR) == calDiff.get(Calendar.YEAR)
}