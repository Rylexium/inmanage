package com.example.inmanage.cabinet.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inmanage.R
import com.example.inmanage.cabinet.adapters.AssetsAdapter
import com.example.inmanage.cabinet.model.AssetData
import com.example.inmanage.databinding.FragmentAssetsBinding

class AssetsFragment : Fragment() {
    private var _binding: FragmentAssetsBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentAssetsBinding.inflate(inflater, container, false)

        initComponents()
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun initComponents() {
        val lisfOfAssets = mutableListOf(
            AssetData("Общее", R.drawable.asset_transport, {}),
            AssetData("Недвижимость", R.drawable.asset_transport, {}),
            AssetData("Транспорт", R.drawable.asset_transport, {}),
            AssetData("Бизнес", R.drawable.asset_transport, {}),
            AssetData("Акции", R.drawable.asset_transport, {}),
            AssetData("Облигации", R.drawable.asset_transport, {})
        )
        binding.recyclerViewAssets.adapter = AssetsAdapter(this, lisfOfAssets)
        binding.recyclerViewAssets.layoutManager = LinearLayoutManager(activity)
    }

}