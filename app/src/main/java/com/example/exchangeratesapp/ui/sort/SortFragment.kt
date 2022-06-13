package com.example.exchangeratesapp.ui.sort

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.fragment.app.DialogFragment
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.databinding.PopularRatesFragmentBinding
import com.example.exchangeratesapp.databinding.SortFragmentBinding

class SortFragment:
    //DialogFragment() {
//    private lateinit var binding: SortFragmentBinding
//    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
//        binding = SortFragmentBinding.inflate(LayoutInflater.from(context))
//        return AlertDialog.Builder(requireContext())
//            .setView(binding.root)
//            .setNegativeButton("Отмена") { _, _ -> }
//            .setPositiveButton("Применить") { _, _ ->  }
//            .create()
//    }
//
//    override fun onResume() {
//        super.onResume()
//        onCheckedChange()
//    }
//
//    override fun onCreate(savedInstanceState: Bundle?) {
//        super.onCreate(savedInstanceState)
//        isCancelable = false
//    }
//
//    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
//        super.onViewCreated(view, savedInstanceState)
//        onCheckedChange()
//    }


Fragment(){
    private lateinit var binding: SortFragmentBinding

//    @Inject
//    lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //(requireActivity().applicationContext as App).appComponent.inject(this)
        binding = SortFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonOk.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }
        binding.buttonClose.setOnClickListener {
            parentFragmentManager.popBackStack(null, FragmentManager.POP_BACK_STACK_INCLUSIVE)
        }

        onCheckedChange()
    }

    private fun onCheckedChange(){
        binding.checkBoxCodeAsc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxCodeDesc.isEnabled = !isChecked
        }
        binding.checkBoxCodeDesc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxCodeAsc.isEnabled = !isChecked
        }
        binding.checkBoxValueAsc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxValueDesc.isEnabled = !isChecked
        }
        binding.checkBoxValueDesc.setOnCheckedChangeListener { _, isChecked ->
            binding.checkBoxValueAsc.isEnabled = !isChecked
        }
    }

}