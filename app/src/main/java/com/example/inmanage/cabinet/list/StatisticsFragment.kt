package com.example.inmanage.cabinet.list

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.graphics.Paint
import android.os.Bundle
import android.text.SpannableString
import android.text.style.UnderlineSpan
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.inmanage.R
import com.example.inmanage.cabinet.adapters.StatisticsAdapter
import com.example.inmanage.cabinet.model.StatisticsData
import com.example.inmanage.databinding.FragmentStatisticsBinding
import kotlin.random.Random


class StatisticsFragment : Fragment() {
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
            it.visibility = View.GONE

            binding.customSpinnerInterval.visibility = View.VISIBLE


            if(binding.customSpinnerInterval.childCount == 0) {
                val list = mutableListOf("месяц", "квартал", "год")
                for (item in list) {
                    val inflater = activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
                    val rowView = inflater.inflate(R.layout.custom_line, null)
                    val text = rowView.findViewById<TextView>(R.id.text_custom_line)
                    text.text = item

                    text.setOnClickListener {
                        val mSpannableString = SpannableString(text.text)
                        mSpannableString.setSpan(UnderlineSpan(), 0, mSpannableString.length, 0)

                        binding.textOfInterval.text = mSpannableString
                        binding.textOfInterval.visibility = View.VISIBLE
                        binding.customSpinnerInterval.visibility = View.GONE
                    }

                    binding.customSpinnerInterval.addView(rowView)
                }
            }

        }
    }

    private fun initComponents() {
        val lisfOfStatistics = mutableListOf(
            StatisticsData("Доход:", "150 000 P"),
            StatisticsData("Расход:", "75 000 P"),
            StatisticsData("Сальдо:", "56 000 P"),
            StatisticsData("Стоимость рабочего часа:", "4 000 P"),
        )
        binding.recyclerViewStatistics.adapter = StatisticsAdapter(this, lisfOfStatistics)
        binding.recyclerViewStatistics.layoutManager = LinearLayoutManager(activity)
    }


    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}