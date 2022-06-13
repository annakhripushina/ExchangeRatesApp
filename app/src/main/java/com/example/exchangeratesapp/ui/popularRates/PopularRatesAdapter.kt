package com.example.exchangeratesapp.ui.popularRates

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.databinding.RateItemBinding
import com.example.exchangeratesapp.domain.entity.PopularRate

class PopularRatesAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var ratesList: List<PopularRate> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding = RateItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularRatesViewHolder(binding, onClick)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PopularRatesViewHolder -> {
                holder.bind(ratesList[position])
            }
        }
    }

    override fun getItemCount(): Int =
        ratesList.size
}