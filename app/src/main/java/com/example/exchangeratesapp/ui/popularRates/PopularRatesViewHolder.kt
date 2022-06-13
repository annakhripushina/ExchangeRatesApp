package com.example.exchangeratesapp.ui.popularRates

import androidx.recyclerview.widget.RecyclerView
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.databinding.RateItemBinding
import com.example.exchangeratesapp.domain.entity.PopularRate

class PopularRatesViewHolder(
    private val binding: RateItemBinding,
    private val onClick: (String) -> Unit
) : RecyclerView.ViewHolder(
    binding.root
) {
    fun bind(rate: PopularRate) {
        binding.textRateCode.text = rate.code
        binding.textRateValue.text = rate.value.toString()

        binding.buttonStar.setOnClickListener {
            onClick(rate.code)
        }
    }
}