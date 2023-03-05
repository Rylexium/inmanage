package com.example.inmanage.cabinet.adapters

import android.animation.LayoutTransition
import android.content.Context
import android.graphics.Color
import android.transition.AutoTransition
import android.transition.TransitionManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.RecyclerView
import com.example.inmanage.R
import com.example.inmanage.cabinet.model.StatisticsData
import kotlin.random.Random


class StatisticsAdapter(
    private val context: Fragment,
    private val statisticsList: MutableList<StatisticsData>)
    : RecyclerView.Adapter<StatisticsAdapter.StatisticsViewHolder>()  {

    inner class StatisticsViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val shortInfoLinearLayout : LinearLayout = v.findViewById(R.id.short_info_ll_field_of_statistics)
        val shortInfoDescription : TextView = v.findViewById(R.id.short_info_description_field_of_statistics)
        val shortInfoIcon : CardView = v.findViewById(R.id.short_info_icon_field_of_statistics)
        val shortInfoPrice: TextView = v.findViewById(R.id.short_info_price_field_of_statistics)

        val moreInfoLinearLayout : CardView = v.findViewById(R.id.more_info_ll_field_of_statistics)
        val moreInfoBodyStatistics : LinearLayout = v.findViewById(R.id.more_info_body_statistics)
        val moreInfoDescription : TextView = v.findViewById(R.id.more_info_description_field_of_statistics)
        val moreInfoIcon : CardView = v.findViewById(R.id.more_info_icon_field_of_statistics)
        val moreInfoPrice : TextView = v.findViewById(R.id.more_info_price_field_of_statistics)

        val moreInfoInBodyFieldOfStatistics : LinearLayout = v.findViewById(R.id.more_info_in_body_field_of_statistics)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StatisticsAdapter.StatisticsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.field_of_statistics, parent, false)

        return StatisticsViewHolder(v)
    }

    override fun onBindViewHolder(holder: StatisticsViewHolder, position: Int) {
        val newList = statisticsList[position]
        holder.shortInfoDescription.text = newList.description
        holder.shortInfoPrice.text = newList.price

        holder.moreInfoDescription.text = newList.description
        holder.moreInfoPrice.text = newList.price


        holder.shortInfoIcon.setOnClickListener {
            TransitionManager.beginDelayedTransition(holder.moreInfoLinearLayout, AutoTransition())
            holder.moreInfoLinearLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)

            holder.shortInfoLinearLayout.visibility = View.GONE

            holder.moreInfoLinearLayout.visibility = View.VISIBLE
            holder.moreInfoBodyStatistics.visibility = View.VISIBLE

            if(holder.moreInfoInBodyFieldOfStatistics.childCount == 0)
                addViewToLinearLayout(holder.moreInfoInBodyFieldOfStatistics,
                    mutableListOf("Недвижимость", "Транспорт", "Бизнес", "Акции", "Облегации"))
        }
        holder.moreInfoIcon.setOnClickListener {
            holder.shortInfoLinearLayout.visibility = View.VISIBLE

            holder.moreInfoLinearLayout.visibility = View.GONE
            holder.moreInfoBodyStatistics.visibility = View.GONE
            TransitionManager.beginDelayedTransition(holder.shortInfoLinearLayout, AutoTransition())
            holder.shortInfoLinearLayout.layoutTransition.enableTransitionType(LayoutTransition.CHANGING)
        }

    }


    private fun addViewToLinearLayout(linearLayout: LinearLayout, listOfAssets : MutableList<String>) {
        for(item in listOfAssets) {
            val inflater = context.activity?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
            val rowView = inflater.inflate(R.layout.line_of_statistics, null)
            val text = rowView.findViewById<TextView>(R.id.description_line)
            text.text = item

            val coloredCardView = rowView.findViewById<CardView>(R.id.colored_cardview)
            coloredCardView.setCardBackgroundColor(Color.parseColor("#" + (Random.nextInt(100_000, 999_999)).toString()))
            linearLayout.addView(rowView)
        }
    }

    override fun getItemCount(): Int {
        return statisticsList.size
    }
}