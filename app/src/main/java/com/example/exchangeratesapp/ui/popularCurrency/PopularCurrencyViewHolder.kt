package com.example.exchangeratesapp.ui.popularCurrency

import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.databinding.CurrencyItemBinding
import com.example.exchangeratesapp.domain.entity.PopularCurrency

class PopularCurrencyViewHolder(
    private val binding: CurrencyItemBinding,
    private val onClick: (PopularCurrency) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(currency: PopularCurrency) {
        binding.textCurrencyCode.text = currency.code
        binding.textCurrencyValue.text = currency.value.toString()

        if (currency.isFavourite)
            binding.buttonStar.setImageResource(R.drawable.ic_round_star_24_yellow)
        else
            binding.buttonStar.setImageResource(R.drawable.ic_round_star_outline_24)

        binding.buttonStar.setOnClickListener {
            onClick(currency)
        }
    }
}