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
    private lateinit var factory : ShoeFactory
    //private lateinit var bindingList : FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //I think we need this factory class since ShoeListViewModel has a parameter and we want shoeList & shoeDetail to observe it
        val scoreFragmentArgs by navArgs<ShoeListArgs>()
        factory = ShoeFactory(scoreFragmentArgs.saved)
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(requireActivity(),factory).get(ShoeListViewModel::class.java)
        //shoeList //= ""


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

        binding.saveButton.setOnClickListener { view: View? ->
            //viewModel.addCustomView()
            viewModel.setBooleanTrue()
            viewModel.setNum()
            viewModel.createObject()
            //viewModel._array.value?.get(0)?._companyName?.value = "test"
            viewModel.testArray[0]._companyName.value = "otherCompany"

            viewModel.array.value?.get(0)?._companyName?.value = "sears"//binding.companyNameText.toString()

            if (viewModel.array.value?.get(0)?._companyName?.value == null) //= binding.companyNameText.toString()
                Log.i("array","null value")
            else
            {
                viewModel.array.value?.get(0)?._companyName?.value = "sears"//binding.companyNameText.toString()
            }
            //viewModel._array.value?.set(0, binding.companyNameText.toString())
            /*viewModel._array.observe(viewLifecycleOwner, Observer { myArray ->
                myArray[0]._companyName.value = binding.companyNameText.toString()
            })

             */
            //test to see if _array exists in ShoeDetail //probably something wrong in generating _array in ViewModel
            viewModel.array.observe(viewLifecycleOwner, Observer { myArray ->
                //myArray[0]
                Log.i("array","ShoeDetail" + myArray.get(0)?._companyName.value.toString())
            })

            //update values in array in viewModel object
            viewModel.num
            val saved = viewModel.saved.value


            //TODO: Unsure why "ShoeDetailDirections" became invalid..
            val action = ShoeDetailDirections.actionShoeDetailToShoeList(saved ?: false)
            //view.findNavController().navigate(R.id.action_gameWonFragment_to_gameFragment)
            findNavController(this).navigate(action)
            //viewModel.setBooleanFalse()
        }
        /*
          val currentScore = viewModel.score.value ?: 0
                val action = GameFragmentDirections.actionGameToScore(currentScore)
                findNavController(this).navigate(action)
                viewModel.onGameFinishComplete()
         */


        return binding.root
    }
    fun addView()
    {
        //not sure how to get context in viewModel to use this function there
        //Q: Is it possible to reference a viewGroup of another fragment in this current fragment?
        //A: It would appear not, as we R.layout only gives us access to the xml file name but not the viewGroups within
        //The StackOverflow forums are implying that you must reference the viewGroup within the fragment the viewGroup is attached to.
    }

}