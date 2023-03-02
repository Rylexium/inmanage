package com.example.inmanage.cabinet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import com.example.inmanage.R
import com.example.inmanage.utils.HideKeyboardClass
import com.google.android.material.bottomnavigation.BottomNavigationView

class AssetsActivity : AppCompatActivity() {
    private lateinit var bottomNavigationBar : BottomNavigationView
    //private var fragmnets = listOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assets)

        initComponents()
        applyEvents()
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(currentFocus != null){
            HideKeyboardClass.hideKeyboard(this)
            currentFocus!!.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun initComponents() {
        bottomNavigationBar = findViewById(R.id.bottom_navigation)
    }

    private fun applyEvents() {
        bottomNavigationBar.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.button_reports -> {

                }
                R.id.button_balance -> {

                }
                R.id.button_scheduler ->{

                }
                R.id.button_profile -> {

                }
            }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

}