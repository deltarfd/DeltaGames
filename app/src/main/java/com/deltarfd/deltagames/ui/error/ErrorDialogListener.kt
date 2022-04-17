package com.deltarfd.deltagames.ui.error

interface RetryableErrorDialogListener : ErrorDialogListener {
    fun retry()
}

interface ErrorDialogListener {
    fun cancel()
}