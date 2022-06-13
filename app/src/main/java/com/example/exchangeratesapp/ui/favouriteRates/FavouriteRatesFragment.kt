package com.example.exchangeratesapp.ui.favouriteRates

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import com.example.exchangeratesapp.R
import com.example.exchangeratesapp.databinding.PopularRatesFragmentBinding
import com.example.exchangeratesapp.ui.sort.SortFragment

class FavouriteRatesFragment : Fragment(){
    private lateinit var binding: PopularRatesFragmentBinding

//    @Inject
//    lateinit var viewModel: AuthViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //(requireActivity().applicationContext as App).appComponent.inject(this)
        binding = PopularRatesFragmentBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.buttonSort.setOnClickListener {
            parentFragmentManager.beginTransaction()
                .replace(R.id.fragmentContainerView, SortFragment())
                .addToBackStack("SortFragment")
                .commit()
        }


    }
}