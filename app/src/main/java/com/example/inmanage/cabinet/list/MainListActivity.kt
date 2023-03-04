package com.example.inmanage.cabinet.list

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.inmanage.R
import com.example.inmanage.authorization.AuthorizationActivity
import com.example.inmanage.cabinet.CabinetActivity
import com.example.inmanage.utils.GlobalVariables
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainListActivity : AppCompatActivity() {
    private lateinit var title : TextView
    private lateinit var iconBackArrow : View
    private lateinit var bottomNavigationBar : BottomNavigationView
    private var selectedFragment = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_list)

        initComponents()
        applyEvents()
    }

    private fun applyEvents() {
        iconBackArrow.setOnClickListener {
            onBackPressed()
        }
        bottomNavigationBar.setOnNavigationItemSelectedListener  {
            it.isChecked = true
            when (it.itemId) {
                R.id.button_reports -> {
                    selectedFragment = 0
                }
                R.id.button_balance -> {
                    selectedFragment = 1
                }
                R.id.button_scheduler -> {
                    selectedFragment = 2
                }
                R.id.button_profile -> {
                    selectedFragment = 3
                }
            }
            if(selectedFragment != 1) onBackPressed()
            false
        }
    }

    private fun initComponents() {
        title = findViewById(R.id.title_main_list)
        title.text = intent.getStringExtra("title")

        iconBackArrow = findViewById(R.id.icon_back_arrow)
        bottomNavigationBar = findViewById(R.id.bottom_navigation)
        bottomNavigationBar.selectedItemId = R.id.button_balance

        replaceFragment(GlobalVariables.fragment)
    }

    private fun replaceFragment(fragment: Fragment?){
        if(fragment == null) return

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_mainlist, fragment)
        transaction.commit()
    }

    override fun onBackPressed() {
        GlobalVariables.selectedFragment = selectedFragment
        GlobalVariables.selectedItemId = bottomNavigationBar.selectedItemId

        super.onBackPressed()
        overridePendingTransition(android.R.anim.fade_in, android.R.anim.fade_out)
    }
}