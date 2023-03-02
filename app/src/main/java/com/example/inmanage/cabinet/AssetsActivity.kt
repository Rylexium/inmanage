package com.example.inmanage.cabinet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MotionEvent
import androidx.fragment.app.Fragment
import com.example.inmanage.R
import com.example.inmanage.cabinet.fragments.AssetsFragment
import com.example.inmanage.utils.HideKeyboardClass
import com.google.android.material.bottomnavigation.BottomNavigationView

class AssetsActivity : AppCompatActivity() {
    private lateinit var bottomNavigationBar : BottomNavigationView
    private var fragments = listOf(AssetsFragment())

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assets)
        initComponents()
        applyEvents()
        replaceFragment(fragments[0])
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
        bottomNavigationBar.selectedItemId = R.id.button_balance
    }

    private fun applyEvents() {
        bottomNavigationBar.setOnItemReselectedListener {
            when(it.itemId) {
                R.id.button_reports -> {
                    bottomNavigationBar.menu.getItem(0).isChecked = true
                }
                R.id.button_balance -> {
                    bottomNavigationBar.menu.getItem(1).isChecked = true
                    replaceFragment(fragments[0])
                }
                R.id.button_scheduler ->{
                    bottomNavigationBar.menu.getItem(2).isChecked = true
                }
                R.id.button_profile -> {
                    bottomNavigationBar.menu.getItem(3).isChecked = true
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