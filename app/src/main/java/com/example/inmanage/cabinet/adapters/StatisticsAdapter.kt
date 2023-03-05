package com.example.inmanage.cabinet.adapters

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
import com.example.inmanage.utils.ShowToast
import org.w3c.dom.Text

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
            holder.shortInfoLinearLayout.visibility = View.GONE

            holder.moreInfoLinearLayout.visibility = View.VISIBLE
            holder.moreInfoBodyStatistics.visibility = View.VISIBLE
        }
        holder.moreInfoIcon.setOnClickListener {
            holder.shortInfoLinearLayout.visibility = View.VISIBLE

            holder.moreInfoLinearLayout.visibility = View.GONE
            holder.moreInfoBodyStatistics.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int {
        return statisticsList.size
    }
}