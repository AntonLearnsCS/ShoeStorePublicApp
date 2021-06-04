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
    //    private val model: SharedViewModel by activityViewModels()
    private lateinit var viewModel: ShoeListViewModel//by MainViewModel
    private lateinit var binding : FragmentShoeDetailBinding
    //private lateinit var factory : ShoeFactory
    //private lateinit var bindingList : FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        //We don't actually need a factor to create a custom viewModel since we discovered we can share a common viewModel using requireActivity()
        //I commented out the code having to do with using a factory to pass in variables from shoeList and shoeDetail; also see code at bottom
        //for the factory class previously created
        //ShoeListArgs is the argument passed into the ShoeList fragment
        //val scoreFragmentArgs by navArgs<ShoeListArgs>()
        //factory = ShoeFactory(scoreFragmentArgs.saved)

        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
/*
Create a new Shoe Detail destination that includes:

A new layout
A TextView label and EditView for the Shoe Name, Company, Shoe Size and Description
A Cancel button with an action to navigate back to the shoe list screen
A Save button with an action to navigate back to the shoe list screen and add a new Shoe to the Shoe View Model.
 */
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_detail,
            container,
            false
        )
        binding.cancelButton.setOnClickListener { findNavController(this).navigate(R.id.action_shoeDetail_to_shoeList)
        }
        //pass in the id of the selected view
        if (viewModel.returning.value == true)
        {
            //Sets the shoeDetail to the previously inputted strings
            //Use setText(String), since editText.text expects an Editable, not a String.
            binding.companyNameText.setText(viewModel.array.value?.get(viewModel.id.value!!)?._companyName?.value.toString())
            binding.shoeNameText.setText(viewModel.array.value?.get(viewModel.id.value!!)?._shoeName?.value.toString())
            binding.shoeSizeText.setText(viewModel.array.value?.get(viewModel.id.value!!)?._shoeSize?.value.toString())
            binding.descriptionText.setText(viewModel.array.value?.get(viewModel.id.value!!)?._shoeDescription?.value.toString())
            binding.numStock.setText(viewModel.array.value?.get(viewModel.id.value!!)?._numStock?.value.toString())

            //binding.companyNameText.text = (viewModel.array.value?.get(viewModel.id.value)?)._companyName.value.toString()
               //viewModel.id.value?.let { it1 -> viewModel.array.value?.get(it1) }
        }

        binding.saveButton.setOnClickListener {
            //val saved = viewModel.saved.value

            if (viewModel.returning.value == true)
            {
                viewModel.array.value?.get(viewModel.id.value!!)?._companyName?.value = binding.companyNameText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._shoeName?.value = binding.shoeNameText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._shoeSize?.value = binding.shoeSizeText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._shoeDescription?.value = binding.descriptionText.text.toString()
                viewModel.array.value?.get(viewModel.id.value!!)?._numStock?.value = binding.numStock.text.toString()

                //viewModel.setBooleanTrue()
                //val action = ShoeDetailDirections.actionShoeDetailToShoeList(saved ?: false)
                //view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
                findNavController(this).navigate(R.id.action_shoeDetail_to_shoeList)
            }
            else
            {
                //viewModel.setBooleanTrue()
                viewModel.createObject()
                viewModel.assignCompanyName(binding.companyNameText.text.toString())
                viewModel.assignShoeDescription(binding.descriptionText.text.toString())
                viewModel.assignShoeName(binding.shoeNameText.text.toString())
                viewModel.assignShoeSize(binding.shoeSizeText.text.toString())
                viewModel.assignNumStock(binding.numStock.text.toString())
            //val action = ShoeDetailDirections.actionShoeDetailToShoeList(saved ?: false)
            //view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
            findNavController(this).navigate(R.id.action_shoeDetail_to_shoeList)
        }
        }

        return binding.root
    }
    /*
    fun addView()
    {
        //not sure how to get context in viewModel to use this function there
        //Q: Is it possible to reference a viewGroup of another fragment in this current fragment?
        //A: It would appear not, as we R.layout only gives us access to the xml file name but not the viewGroups within
        //The StackOverflow forums are implying that you must reference the viewGroup within the fragment the viewGroup is attached to.
    }
     */
}

/* //factory class before using requireActivity()
package com.example.shoestoreproject.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoeFactory (private val finalScore: Boolean) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {

            if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
                return ShoeListViewModel(finalScore) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }



}


 */