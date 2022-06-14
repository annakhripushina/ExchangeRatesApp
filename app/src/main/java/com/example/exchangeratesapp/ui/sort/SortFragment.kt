package com.example.exchangeratesapp.ui.sort

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.example.exchangeratesapp.App
import com.example.exchangeratesapp.databinding.SortFragmentBinding
import com.example.exchangeratesapp.presentation.sort.SortViewModel
import javax.inject.Inject

class SortFragment : Fragment() {
    private lateinit var binding: SortFragmentBinding

    @Inject
    lateinit var viewModel: SortViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        (requireActivity().applicationContext as App).appComponent.inject(this)
        binding = SortFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        onSetCheckBox()

        binding.buttonOk.setOnClickListener {
            parentFragmentManager.popBackStack()
        }
        binding.buttonClose.setOnClickListener {
            parentFragmentManager.popBackStack()
        }

        onCheckedChange()
    }

    private fun onSetCheckBox() {
        val sortCode = viewModel.getCodeSort()
        val sortValue = viewModel.getValueSort()

        if (sortCode == 1) {
            binding.checkBoxCodeDesc.isChecked = false
            binding.checkBoxCodeDesc.isEnabled = false
            binding.checkBoxCodeAsc.isChecked = true
        } else if (sortCode == 2) {
            binding.checkBoxCodeDesc.isChecked = true
            binding.checkBoxCodeAsc.isChecked = false
            binding.checkBoxCodeAsc.isEnabled = false
        }
        if (sortValue == 1) {
            binding.checkBoxValueDesc.isChecked = false
            binding.checkBoxValueDesc.isEnabled = false
            binding.checkBoxValueAsc.isChecked = true
        } else if (sortValue == 2) {
            binding.checkBoxValueDesc.isChecked = true
            binding.checkBoxValueAsc.isChecked = false
            binding.checkBoxValueAsc.isEnabled = false
        }
    }

    private fun onCheckedChange() {
        binding.checkBoxCodeAsc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxCodeDesc.isEnabled = !isChecked
            if (isChecked)
                viewModel.setCodeSort(1)
            else
                viewModel.setCodeSort(null)

        }
        binding.checkBoxCodeDesc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxCodeAsc.isEnabled = !isChecked
            if (isChecked)
                viewModel.setCodeSort(2)
            else
                viewModel.setCodeSort(null)

        }
        binding.checkBoxValueAsc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxValueDesc.isEnabled = !isChecked
            if (isChecked)
                viewModel.setValueSort(1)
            else
                viewModel.setValueSort(null)
        }
        binding.checkBoxValueDesc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxValueAsc.isEnabled = !isChecked
            if (isChecked)
                viewModel.setValueSort(2)
            else
                viewModel.setValueSort(null)
        }

    }

}