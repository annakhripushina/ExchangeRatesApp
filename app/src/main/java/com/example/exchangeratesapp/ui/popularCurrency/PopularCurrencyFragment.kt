package com.example.exchangeratesapp.ui.popularCurrency

import android.annotation.SuppressLint
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.lifecycleScope
import androidx.lifecycle.repeatOnLifecycle
import com.example.exchangeratesapp.App
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.databinding.PopularCurencyFragmentBinding
import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.presentation.popularCurrency.PopularCurrencyViewModel
import com.example.exchangeratesapp.ui.CurrencyListUiState
import com.example.exchangeratesapp.ui.sort.SortFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularCurrencyFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: PopularCurencyFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    private val curencyAdapter = PopularCurrencyAdapter(onClick = {
        if (!it.isFavourite) {
            viewModel.insertFavouriteCurrency(FavouriteCurrency(it.code))
        } else
            viewModel.deleteFavouriteCurrency(FavouriteCurrency(it.code))

        viewModel.updateIsFavouriteCurrency(!it.isFavourite, it)

    })

    private var spinnerArray: Array<String> = emptyArray()

    @Inject
    lateinit var viewModel: PopularCurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel.getCurrencySpinnerArray(requireContext())
        binding = PopularCurencyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerCurrency.onItemSelectedListener = this

        binding.recyclerView.apply {
            adapter = curencyAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { it ->
                    when (it) {
                        is CurrencyListUiState.SuccessSpinner -> {
                            spinnerArray = viewModel.getSpinnerArray()
                            onCreateSpinner(spinnerArray)
                            viewModel.getCurrencyList(viewModel.getSpinnerCode(), requireContext())
                        }
                        is CurrencyListUiState.SuccessCurrency -> {
                            curencyAdapter.listAdapter =
                                it.currencyList
                        }

                        is CurrencyListUiState.Error -> {
                            val snackUpdateList = Snackbar.make(
                                view,
                                it.exception,
                                Snackbar.LENGTH_LONG
                            )
                            snackUpdateList.anchorView = activity?.findViewById(R.id.bottomNavigate)
                            snackUpdateList.show()
                        }
                    }
                }
            }
        }

        binding.buttonSort.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SortFragment())
                .addToBackStack("SortFragment")
                .commit()
        }

        binding.swipeContainer.setOnRefreshListener {
            viewModel.getCurrencyList(viewModel.getSpinnerCode(), requireContext())
            binding.swipeContainer.isRefreshing = false
        }

    }

    private fun onCreateSpinner(array: Array<String>) {
        val spinnerAdapter =
            ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, array)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerCurrency.adapter = spinnerAdapter
        binding.spinnerCurrency.setSelection(spinnerAdapter.getPosition(viewModel.getSpinnerCode()))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        viewModel.getCurrencyList(spinnerArray[position], requireContext())
        viewModel.setSpinnerCode(spinnerArray[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}
