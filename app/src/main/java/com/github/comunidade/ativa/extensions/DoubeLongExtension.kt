package com.github.comunidade.ativa.extensions

import android.util.Log
import java.math.RoundingMode
import java.text.DecimalFormat
import java.text.NumberFormat
import java.util.*

private val locale = Locale("pt", "BR")

fun Double?.balanceFormatted(): String? {

    if (this == null) {
        return "R$ 0,00"
    }

    val nf = NumberFormat.getCurrencyInstance(Locale("pt", "BR")) as DecimalFormat?
    nf?.isGroupingUsed = true
    nf?.positivePrefix = "R$ "
    nf?.negativePrefix = "R$ -"
    nf?.maximumFractionDigits = 2
    nf?.minimumFractionDigits = 2
    nf?.minimumIntegerDigits = 1
    return try {
        nf?.roundingMode = RoundingMode.UNNECESSARY
        nf?.format(this)

    } catch (arException: ArithmeticException) {
        nf?.roundingMode = RoundingMode.HALF_EVEN
        nf?.format(this)
    } catch (e: Exception) {
        Log.e("balanceFormatted", e.message, e)
        null
    }
}

fun Double?.converterValor(): String? {
    if (this != null) {
        val df = DecimalFormat("#.00")
        df.isDecimalSeparatorAlwaysShown = false
        df.minimumFractionDigits = 2
        df.maximumFractionDigits = 2
        df.minimumIntegerDigits = 1
        return df.format(this)
    }
    return null
}
