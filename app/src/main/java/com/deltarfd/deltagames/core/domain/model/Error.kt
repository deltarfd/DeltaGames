package com.deltarfd.deltagames.core.domain.model

data class Error(
    val throwable: Throwable,
    val callback: () -> Unit = {},
)