package com.example.inmanage.cabinet

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.inmanage.R

class AssetsActivity : AppCompatActivity() {

    lateinit var binding: AssetsActivity

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_assets)
    }
}