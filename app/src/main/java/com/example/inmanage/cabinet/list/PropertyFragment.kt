package com.example.inmanage.cabinet.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.inmanage.databinding.FragmentPropertyBinding

class PropertyFragment: Fragment() {
    private var _binding: FragmentPropertyBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentPropertyBinding.inflate(inflater, container, false)

        initComponents()
        return binding.root
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initComponents() {

    }
}