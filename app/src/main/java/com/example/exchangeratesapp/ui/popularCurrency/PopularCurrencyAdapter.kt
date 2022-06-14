package com.example.exchangeratesapp.ui.popularCurrency

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.databinding.CurrencyItemBinding
import com.example.exchangeratesapp.domain.entity.PopularCurrency

class PopularCurrencyAdapter(private val onClick: (PopularCurrency) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var listAdapter: List<PopularCurrency> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return PopularCurrencyViewHolder(binding, onClick)
    }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is PopularCurrencyViewHolder -> {
                holder.bind(listAdapter[position])
            }
        }
    }

    override fun getItemCount(): Int =
        listAdapter.size
}