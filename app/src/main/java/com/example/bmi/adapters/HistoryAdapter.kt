package com.example.bmi.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import com.example.bmi.R
import com.example.bmi.logic.BmiAnalyser
import com.example.bmi.logic.BmiForKgCm
import com.example.bmi.logic.BmiForLbsIn

class HistoryAdapter(val context: Context, val entries: ArrayList<String>) : RecyclerView.Adapter<HistoryAdapter.Holder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): Holder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.history_entry_item, parent, false)
        return Holder(view)
    }

    override fun getItemCount(): Int {
        return entries.count()
    }

    override fun onBindViewHolder(holder: Holder, position: Int) {
        holder.bindEntry(entries[position], context)
    }

    inner class Holder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val entryImage = itemView.findViewById<ImageView>(R.id.history_img)
        val entryWeight = itemView.findViewById<TextView>(R.id.history_weight)
        val entryHeight = itemView.findViewById<TextView>(R.id.history_height)
        val entryBMI = itemView.findViewById<TextView>(R.id.history_bmi)

        fun Double.format(digits: Int) = java.lang.String.format("%.${digits}f", this)

        fun bindEntry(entry: String, context: Context){
            val data = entry.split(",")
            val weight = data[0].toInt()
            val height = data[1].toInt()
            val imperialSelected = data[2].toBoolean()
            entryImage.setImageResource(BmiAnalyser.getImageIdForGivenBMI(
                if(imperialSelected) BmiForLbsIn(weight, height).countBMI()
                else BmiForKgCm(weight, height).countBMI()))
            entryWeight.text = "Weight: " + weight.toString()
            entryHeight.text = "Height: " + height.toString()
            entryBMI.text = "BMI: " + if(imperialSelected) "${BmiForLbsIn(weight, height).countBMI().format(2)}"
            else "${BmiForKgCm(weight, height).countBMI().format(2)}"
        }
    }
}