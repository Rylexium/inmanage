package com.example.inmanage.cabinet.list

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.inmanage.R
import com.example.inmanage.cabinet.CabinetActivity
import com.example.inmanage.utils.GlobalVariables

class MainListActivity : AppCompatActivity() {
    private lateinit var title : TextView
    private lateinit var iconBackArrow : View

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
    }

    private fun initComponents() {
        title = findViewById(R.id.title_main_list)
        title.text = intent.getStringExtra("title")

        iconBackArrow = findViewById(R.id.icon_back_arrow)

        replaceFragment(GlobalVariables.fragment)
    }

    private fun replaceFragment(fragment: Fragment?){
        if(fragment == null) return

        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.fragment_container_mainlist, fragment)
        transaction.commit()
    }
}