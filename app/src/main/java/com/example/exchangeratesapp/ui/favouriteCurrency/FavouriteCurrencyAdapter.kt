package com.example.exchangeratesapp.ui.favouriteCurrency

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.databinding.CurrencyItemBinding
import com.example.exchangeratesapp.domain.entity.PopularCurrency

class FavouriteCurrencyAdapter(private val onClick: (String) -> Unit) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    var currencyList: List<PopularCurrency> = emptyList()
        @SuppressLint("NotifyDataSetChanged")
        set(value) {
            field = value
            notifyDataSetChanged()
        }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val binding =
            CurrencyItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteCurrencyViewHolder(binding, onClick)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        when (holder) {
            is FavouriteCurrencyViewHolder -> {
                holder.bind(currencyList[position])
            }
        }
    }

    override fun getItemCount(): Int =
        currencyList.size

}