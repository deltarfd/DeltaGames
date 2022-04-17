package com.deltarfd.deltagames.utils

fun Double?.isNull(): Boolean {
    return (this as? Number).isNull()
}

fun Double?.orZero(): Double {
    return (this as? Number).orZero().toDouble()
}