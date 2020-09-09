package com.albuquerque.core.extensions

import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*

/**
 * Transform a [Date] into a [String]
 * @param pattern pattern used in the transformation
 * */
fun Date.format(pattern: String? = "dd/MM/yyyy"): String = SimpleDateFormat(pattern, Locale("pt", "BR")).format(this)

/**
 * Transform a [String] into a [Date]
 * @param pattern pattern used in the transformation
 * */
fun String.parse(pattern: String? = "dd/MM/yyyy"): Date {
    val format = SimpleDateFormat(pattern, Locale("pt", "BR"))
    var result = Calendar.getInstance().time

    try {
        format.parse(this)?.let {
            result = it
        }

    } catch (e: ParseException) {
        e.printStackTrace()
    }

    return result
}

fun Long.format(pattern: String): String = Date(this).format(pattern)

fun Date.add(field: Int, amount: Int): Date {
    val calendar = Calendar.getInstance().apply { time = this@add }
    calendar.add(field, amount)
    return calendar.time
}

fun Date.minus(field: Int, amount: Int): Date {
    val calendar = Calendar.getInstance().apply { time = this@minus }
    calendar.add(field, -amount)
    return calendar.time
}

fun getCurrentBrazilianTime(): Date {
    return Calendar.getInstance().apply {
        clear(Calendar.ZONE_OFFSET)
        timeZone = TimeZone.getTimeZone("GMT-03:00")
    }.time
}

fun Date.getBeginningOfTheDay(): Date {
    val calendar = Calendar.getInstance()
    calendar.time = this

    calendar.set(Calendar.HOUR_OF_DAY, 0)
    calendar.set(Calendar.MINUTE, 0)
    calendar.set(Calendar.SECOND, 0)
    calendar.set(Calendar.MILLISECOND, 0)

    return calendar.time
}