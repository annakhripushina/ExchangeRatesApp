package com.example.exchangeratesapp.ui.popularRates

import android.os.Bundle
import android.util.Log
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
import com.example.exchangeratesapp.databinding.PopularRatesFragmentBinding
import com.example.exchangeratesapp.domain.entity.PopularRate
import com.example.exchangeratesapp.presentation.popularRates.LatestRatesUiState
import com.example.exchangeratesapp.presentation.popularRates.PopularRatesViewModel
import com.example.exchangeratesapp.ui.sort.SortFragment
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import javax.inject.Inject

class PopularRatesFragment : Fragment(), AdapterView.OnItemSelectedListener {

    private lateinit var binding: PopularRatesFragmentBinding

    private val ratesAdapter = PopularRatesAdapter(onClick = {

    })

    private var spinnerArray : Array<String> = emptyArray()

    @Inject
    lateinit var viewModel: PopularRatesViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        binding = PopularRatesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewModel.getSpinnerArray()

        binding.spinnerRates.onItemSelectedListener = this

        binding.recyclerView.apply {
            adapter = ratesAdapter
        }

        viewLifecycleOwner.lifecycleScope.launch {
            viewLifecycleOwner.repeatOnLifecycle(Lifecycle.State.STARTED) {
                viewModel.uiState.collect { it ->
                    when (it) {
                        is LatestRatesUiState.SpinnerSuccess -> {
                            spinnerArray = it.codeList.results.map{it.key}.toTypedArray()
                            onCreateSpinner(spinnerArray)
                        }
                        is LatestRatesUiState.Success -> {
                            //Log.d("RESULT1", it.ratesList.toString())
                            ratesAdapter.ratesList = it.ratesList.results.map { PopularRate(it.key, it.value) }
                        }
                        is LatestRatesUiState.Error -> Log.d("RESULT2", it.exception.toString())
                    }
                }
            }
        }

        binding.buttonSort.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .add(R.id.fragmentContainerView, SortFragment())
                .addToBackStack("SortFragment")
                .commit()
            //SortFragment().show(parentFragmentManager, "SortFragment")
        }

    }

    private fun onCreateSpinner(array: Array<String>){
        val spinnerAdapter = ArrayAdapter(requireContext(), android.R.layout.simple_spinner_item, array)
        spinnerAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        binding.spinnerRates.adapter = spinnerAdapter
        binding.spinnerRates.setSelection(spinnerAdapter.getPosition("USD"))
    }

    override fun onItemSelected(parent: AdapterView<*>?, view: View?, position: Int, id: Long) {
        Log.d("SPINNER", spinnerArray[position])
        viewModel.getLatest(spinnerArray[position])
    }

    override fun onNothingSelected(parent: AdapterView<*>?) {

    }

}
