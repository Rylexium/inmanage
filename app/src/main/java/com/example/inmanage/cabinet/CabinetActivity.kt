package com.example.inmanage.cabinet

import android.content.DialogInterface
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
import com.example.inmanage.cabinet.title.AssetsActivity
import com.example.inmanage.cabinet.title.BalanceActivity
import com.example.inmanage.cabinet.title.LiabilitiesActivity
import com.example.inmanage.utils.HideKeyboardClass
import com.google.android.material.bottomnavigation.BottomNavigationView


class CabinetActivity : AppCompatActivity() {
    private lateinit var bottomNavigationBar : BottomNavigationView
    private lateinit var iconInTitleAssets : View
    private lateinit var textInTitleAssets : TextView

    private var fragments = listOf(ReportsFragment(), AssetsFragment(), SchedulerFragment(), ProfileFragment())
    private val listOfAssets = mutableListOf("Баланс", "Активы", "Пассивы")
    private var pastIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_cabinet)
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
    override fun onBackPressed() {
        val dialogClickListener =
            DialogInterface.OnClickListener { _, which ->
                when (which) {
                    DialogInterface.BUTTON_POSITIVE -> { super.onBackPressed() }
                    DialogInterface.BUTTON_NEGATIVE -> {}
                }
            }

        AlertDialog.Builder(this).setMessage("Вы действительно хотите выйти?")
            .setPositiveButton("Да", dialogClickListener)
            .setNegativeButton("Нет", dialogClickListener)
            .show();
    }

    private fun initComponents() {
        iconInTitleAssets = findViewById(R.id.icon_in_title_assets)
        textInTitleAssets = findViewById(R.id.text_in_title_assets)

        bottomNavigationBar = findViewById(R.id.bottom_navigation)
        bottomNavigationBar.selectedItemId = R.id.button_balance
        replaceFragment(fragments[pastIndex], pastIndex, 0)
    }

    private fun applyEvents() {
        bottomNavigationBar.setOnNavigationItemSelectedListener  {
            it.isChecked = true
            when (it.itemId) {
                R.id.button_reports -> {
                    replaceFragment(fragments[0], pastIndex, 0)
                    pastIndex = 0
                }
                R.id.button_balance -> {
                    replaceFragment(fragments[1], pastIndex, 1)
                    pastIndex = 1
                }
                R.id.button_scheduler -> {
                    replaceFragment(fragments[2], pastIndex, 2)
                    pastIndex = 2
                }
                R.id.button_profile -> {
                    replaceFragment(fragments[3], pastIndex, 3)
                    pastIndex = 3
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
                AdapterView.OnItemClickListener { _, _, position, _ ->
                    if(listOfAssets[position] == "Баланс")
                        startActivity(Intent(this, BalanceActivity::class.java))
                    else if(listOfAssets[position] == "Активы")
                        startActivity(Intent(this, AssetsActivity::class.java))
                    else if(listOfAssets[position] == "Пассивы")
                        startActivity(Intent(this, LiabilitiesActivity::class.java))
                    dialog.dismiss()
                }
        }
    }

    private fun replaceFragment(fragment: Fragment, pastIndex : Int, previousIndex : Int){
        val transaction = supportFragmentManager.beginTransaction()
        if(pastIndex < previousIndex)
            transaction.setCustomAnimations(R.anim.slide_in_right, R.anim.slide_out_left)
        else
            transaction.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right)
        transaction.replace(R.id.fragment_container_cabinet, fragment)
        transaction.commit()
    }

}