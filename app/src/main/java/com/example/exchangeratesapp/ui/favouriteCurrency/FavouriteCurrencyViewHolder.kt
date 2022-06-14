package com.example.exchangeratesapp.ui.favouriteCurrency

import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.databinding.CurrencyItemBinding
import com.example.exchangeratesapp.domain.entity.PopularCurrency

class FavouriteCurrencyViewHolder(
    private val binding: CurrencyItemBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(currency: PopularCurrency) {
        binding.textCurrencyCode.text = currency.code
        binding.textCurrencyValue.text = currency.value.toString()
        binding.buttonStar.setImageResource(R.drawable.ic_round_star_24_yellow)

        binding.buttonStar.setOnClickListener {
            onClick(currency.code)
        }
    }
}