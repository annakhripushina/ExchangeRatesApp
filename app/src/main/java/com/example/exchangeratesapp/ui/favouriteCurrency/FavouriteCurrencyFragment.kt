package com.example.exchangeratesapp.ui.favouriteCurrency

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
import com.example.exchangeratesapp.databinding.FavouriteCurrencyFragmentBinding
import com.example.exchangeratesapp.domain.entity.FavouriteCurrency
import com.example.exchangeratesapp.presentation.favouriteCurrency.FavouriteCurrencyViewModel
import com.example.exchangeratesapp.ui.CurrencyListUiState
import com.example.exchangeratesapp.ui.sort.SortFragment
import com.google.android.material.snackbar.Snackbar
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class FavouriteCurrencyFragment : Fragment(), AdapterView.OnItemSelectedListener {
    private lateinit var binding: FavouriteCurrencyFragmentBinding

    @SuppressLint("NotifyDataSetChanged")
    private val listAdapter = FavouriteCurrencyAdapter(onClick = { code ->
        viewModel.deleteFavouriteCurrency(FavouriteCurrency(code))
    })

    private var spinnerArray: Array<String> = emptyArray()

    @Inject
    lateinit var viewModel: FavouriteCurrencyViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        viewModel.getCurrencySpinnerArray(requireContext())
        binding = FavouriteCurrencyFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.spinnerCurrency.onItemSelectedListener = this

        binding.recyclerView.apply {
            adapter = listAdapter
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
                            listAdapter.currencyList =
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