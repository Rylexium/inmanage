package com.example.inmanage.utils

import android.app.Activity
import android.view.inputmethod.InputMethodManager

import androidx.appcompat.app.AppCompatActivity




object HideKeyboardClass {
    fun hideKeyboard(activity: AppCompatActivity) { //метод убирает клавиатуру
        val inputMethodManager: InputMethodManager =
            activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
        if (inputMethodManager.isActive) {
            if (activity.currentFocus != null) {
                inputMethodManager.hideSoftInputFromWindow(activity.currentFocus!!.windowToken, 0)
            }
        }
    }
}