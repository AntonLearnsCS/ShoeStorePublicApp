package com.example.shoestoreproject.list

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.NavHostFragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentShoeDetailBinding


class ShoeDetail  : Fragment() {
    private lateinit var viewModel: ShoeListViewModel
    private lateinit var binding : FragmentShoeDetailBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)

        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )
        binding.shoeDetail = viewModel

        binding.cancelButton.setOnClickListener { findNavController(this).navigate(R.id.action_shoeDetail_to_shoeList)
        }

        binding.saveButton.setOnClickListener {

            if (viewModel.returning.value == true)
            {
                viewModel.array.value?.get(viewModel.id.value!!)?._companyName?.value = binding.companyNameText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._shoeName?.value = binding.shoeNameText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._shoeSize?.value = binding.shoeSizeText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._shoeDescription?.value = binding.descriptionText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._numStock?.value = binding.numStock.text.toString()

                findNavController(this).navigate(R.id.action_shoeDetail_to_shoeList)
            }
            else
            {
                viewModel.createObject()
                viewModel.assignCompanyName(binding.companyNameText.text.toString())
                viewModel.assignShoeDescription(binding.descriptionText.text.toString())
                viewModel.assignShoeName(binding.shoeNameText.text.toString())
                viewModel.assignShoeSize(binding.shoeSizeText.text.toString())
                viewModel.assignNumStock(binding.numStock.text.toString())
            findNavController(this).navigate(R.id.action_shoeDetail_to_shoeList)
        }
        }

        return binding.root
    }
}