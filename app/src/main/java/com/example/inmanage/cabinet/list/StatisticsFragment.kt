package com.example.inmanage.cabinet.list

import android.R
import android.content.Context
import android.os.Bundle
import android.util.TypedValue
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import android.widget.TextView
import androidx.fragment.app.Fragment
import com.example.inmanage.databinding.FragmentStatisticsBinding


class StatisticsFragment: Fragment() {
    private var _binding: FragmentStatisticsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentStatisticsBinding.inflate(inflater, container, false)

        initComponents()
        applyEvents()
        return binding.root
    }

    private fun applyEvents() {
        binding.textOfInterval.setOnClickListener {


        }
    }

    private fun initComponents() {

    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}