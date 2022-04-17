package com.deltarfd.deltagames.utils

fun Number?.isNull(): Boolean {
    return this == null
}

fun Number?.orZero(): Number {
    return this ?: 0.0
}