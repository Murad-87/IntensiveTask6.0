package com.example.myintensivetask60.utils

import android.content.Context
import android.view.inputmethod.InputMethodManager
import android.widget.EditText

fun EditText.openKeyboard(context: Context) {
    val imn = context.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
    imn.showSoftInput(this, InputMethodManager.SHOW_IMPLICIT)
}