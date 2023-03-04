package com.example.inmanage.cabinet.fragments

import android.content.Intent
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inmanage.R
import com.example.inmanage.R.drawable.asset_promotion
import com.example.inmanage.cabinet.CabinetActivity
import com.example.inmanage.cabinet.adapters.AssetsAdapter
import com.example.inmanage.cabinet.list.MainListActivity
import com.example.inmanage.cabinet.list.PropertyFragment
import com.example.inmanage.cabinet.model.AssetData
import com.example.inmanage.databinding.FragmentAssetsBinding
import com.example.inmanage.utils.GlobalVariables
import com.example.inmanage.utils.ShowToast

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
            AssetData("Общее", R.drawable.asset_statistics) {
                //GlobalVariables.fragment =
                activity?.startActivity(Intent(activity, MainListActivity::class.java)
                    .putExtra("title", "Общая статистика:"))
            },
            AssetData("Недвижимость", R.drawable.asset_immovables) {
                GlobalVariables.fragment = PropertyFragment()
                activity?.startActivity(Intent(activity, MainListActivity::class.java)
                    .putExtra("title", "Недвижимость:"))
            },
            AssetData("Транспорт", R.drawable.asset_transport) {
                //GlobalVariables.fragment =
                activity?.startActivity(Intent(activity, MainListActivity::class.java)
                    .putExtra("title", "Транспорт:"))
            },
            AssetData("Бизнес", R.drawable.asset_business) {
                //GlobalVariables.fragment =
                activity?.startActivity(Intent(activity, MainListActivity::class.java)
                    .putExtra("title", "Бизнес:"))
            },
            AssetData("Акции", asset_promotion) {
                //GlobalVariables.fragment =
                activity?.startActivity(Intent(activity, MainListActivity::class.java)
                    .putExtra("title", "Акции:"))
            },
            AssetData("Облигации", R.drawable.asset_bonds) {
                //GlobalVariables.fragment =
                activity?.startActivity(Intent(activity, MainListActivity::class.java)
                    .putExtra("title", "Облигации:"))
            }
        )
        binding.recyclerViewAssets.adapter = AssetsAdapter(this, lisfOfAssets)
        binding.recyclerViewAssets.layoutManager = LinearLayoutManager(activity)
    }

}