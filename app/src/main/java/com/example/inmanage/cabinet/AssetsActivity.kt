package com.example.inmanage.cabinet

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
import com.example.inmanage.utils.HideKeyboardClass
import com.google.android.material.bottomnavigation.BottomNavigationView


class AssetsActivity : AppCompatActivity() {
    private lateinit var bottomNavigationBar : BottomNavigationView
    private lateinit var iconInTitleAssets : View
    private lateinit var textInTitleAssets : TextView

    private var fragments = listOf(AssetsFragment())
    private val listOfAssets = mutableListOf("Баланс", "Активы", "Пассивы", "Отчёты", "Планировщик")

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
        iconInTitleAssets = findViewById(R.id.icon_in_title_assets)
        textInTitleAssets = findViewById(R.id.text_in_title_assets)

        bottomNavigationBar = findViewById(R.id.bottom_navigation)
        bottomNavigationBar.selectedItemId = R.id.button_balance
    }

    private fun applyEvents() {
        bottomNavigationBar.setOnItemReselectedListener {
            when (it.itemId) {
                R.id.button_reports -> {
                    bottomNavigationBar.menu.getItem(0).isChecked = true
                }
                R.id.button_balance -> {
                    bottomNavigationBar.menu.getItem(1).isChecked = true
                    replaceFragment(fragments[0])
                }
                R.id.button_scheduler -> {
                    bottomNavigationBar.menu.getItem(2).isChecked = true
                }
                R.id.button_profile -> {
                    bottomNavigationBar.menu.getItem(3).isChecked = true
                }
            }
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
                    textInTitleAssets.text = listOfAssets[position]
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