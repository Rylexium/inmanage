package com.example.inmanage.cabinet.adapters

import android.os.Handler
import android.os.Looper
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.cardview.widget.CardView
import androidx.core.content.ContextCompat
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentActivity
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DecodeFormat
import com.example.inmanage.R
import com.example.inmanage.cabinet.model.AssetData
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class AssetsAdapter(
    private val context: Fragment,
    private val assetsList: MutableList<AssetData>)
: RecyclerView.Adapter<AssetsAdapter.AssetsViewHolder>()  {

    inner class AssetsViewHolder(v: View):RecyclerView.ViewHolder(v) {
        val fieldOfAsset : CardView = v.findViewById(R.id.field_of_asset)
        val descriptionField: TextView = v.findViewById(R.id.description_field)
        val descriptionImage: ImageView = v.findViewById(R.id.description_image)
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AssetsAdapter.AssetsViewHolder {
        val inflater = LayoutInflater.from(parent.context)
        val v = inflater.inflate(R.layout.field_of_asset, parent, false)

        return AssetsViewHolder(v)
    }

    override fun onBindViewHolder(holder: AssetsAdapter.AssetsViewHolder, position: Int) {
        val newList = assetsList[position]
        holder.descriptionField.text = newList.descriptionField

        CoroutineScope(Dispatchers.IO).launch {
            val drawable = ContextCompat.getDrawable(context.requireContext(), newList.descriptionImage)

            Handler(Looper.getMainLooper()).post {
                Glide.with(context)
                    .load(drawable)
                    .format(DecodeFormat.PREFER_RGB_565)
                    .centerCrop()
                    .into(holder.descriptionImage)
            }
        }
        holder.fieldOfAsset.setOnClickListener {
            newList.eventAfterClick()
        }

    }

    override fun getItemCount(): Int {
        return assetsList.size
    }
}