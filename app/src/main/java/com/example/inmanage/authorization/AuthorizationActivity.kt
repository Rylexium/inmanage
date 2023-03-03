package com.example.inmanage.authorization

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.widget.Button
import android.widget.EditText
import android.widget.LinearLayout
import androidx.appcompat.app.AppCompatActivity
import com.example.inmanage.R
import com.example.inmanage.cabinet.AssetsActivity
import com.example.inmanage.utils.HideKeyboardClass


class AuthorizationActivity : AppCompatActivity() {
    private lateinit var fieldLogin : EditText
    private lateinit var fieldPassword : EditText
    private lateinit var buttonLogin : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.authorization_main)
        initComponents()
        applyEvents()
        //startActivity(Intent(this, AssetsActivity::class.java))
    }

    private fun applyEvents() {
        buttonLogin.setOnClickListener {
            if(fieldLogin.text.toString() == "admin" && fieldPassword.text.toString() == "123")
                startActivity(Intent(this, AssetsActivity::class.java))
        }
    }

    private fun initComponents() {
        fieldLogin = findViewById(R.id.field_login)
        fieldPassword = findViewById(R.id.field_password)
        buttonLogin = findViewById(R.id.button_login)
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if (currentFocus != null) {
            HideKeyboardClass.hideKeyboard(this)
            val ll = findViewById<LinearLayout>(R.id.authorization_layout)
            ll.requestFocus()
        }
        return super.dispatchTouchEvent(ev)
    }
}