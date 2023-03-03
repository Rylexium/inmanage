package com.example.inmanage.cabinet

import android.content.Intent
import android.os.Bundle
import android.view.MotionEvent
import android.view.View
import android.widget.AdapterView
import android.widget.ArrayAdapter
import android.widget.ListView
import android.widget.TextView
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.example.inmanage.R
import com.example.inmanage.cabinet.fragments.AssetsFragment
import com.example.inmanage.cabinet.fragments.ProfileFragment
import com.example.inmanage.cabinet.fragments.ReportsFragment
import com.example.inmanage.cabinet.fragments.SchedulerFragment
import com.example.inmanage.utils.HideKeyboardClass
import com.google.android.material.bottomnavigation.BottomNavigationView


class AssetsActivity : AppCompatActivity() {
    private lateinit var bottomNavigationBar : BottomNavigationView
    private lateinit var iconInTitleAssets : View
    private lateinit var textInTitleAssets : TextView

    private var fragments = listOf(ReportsFragment(), AssetsFragment(), SchedulerFragment(), ProfileFragment())
    private val listOfAssets = mutableListOf("Баланс", "Активы", "Пассивы")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assets)
        initComponents()
        applyEvents()
        replaceFragment(fragments[1])
    }

    override fun dispatchTouchEvent(ev: MotionEvent?): Boolean {
        if(currentFocus != null){
            HideKeyboardClass.hideKeyboard(this)
            currentFocus!!.clearFocus()
        }
        return super.dispatchTouchEvent(ev)
    }

    private fun initComponents() {
        iconInTitleAssets = findViewById(R.id.icon_in_title_assets)
        textInTitleAssets = findViewById(R.id.text_in_title_assets)

        bottomNavigationBar = findViewById(R.id.bottom_navigation)
        bottomNavigationBar.selectedItemId = R.id.button_balance
    }

    private fun applyEvents() {
        bottomNavigationBar.setOnNavigationItemSelectedListener  {
            it.isChecked = true
            when (it.itemId) {
                R.id.button_reports -> {
                    replaceFragment(fragments[0])
                }
                R.id.button_balance -> {
                    replaceFragment(fragments[1])
                }
                R.id.button_scheduler -> {
                    replaceFragment(fragments[2])
                }
                R.id.button_profile -> {
                    replaceFragment(fragments[3])
                }
            }
            false
        }
        iconInTitleAssets.setOnClickListener {
            val spinner = ListView(this)

            val dialog = AlertDialog.Builder(this).create()
            dialog.setTitle("Выберите")
            dialog.setView(spinner)
            dialog.show()

            val adapter = ArrayAdapter(this, android.R.layout.simple_list_item_1, listOfAssets)
            spinner.adapter = adapter
            spinner.onItemClickListener =
                AdapterView.OnItemClickListener { parent, view, position, id ->
                    if(listOfAssets[position] == "Баланс")
                        startActivity(Intent(this, BalancActivity::class.java))
                    dialog.dismiss()
                }
        }
    }

    private fun replaceFragment(fragment: Fragment){
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container, fragment)
        transaction.commit()
    }

}