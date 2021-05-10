package com.example.shoestoreproject.list

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment.findNavController
import com.example.shoestoreproject.MainViewModel
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentShoeDetailBinding


class ShoeDetail : Fragment() {
    //    private val model: SharedViewModel by activityViewModels()
    private lateinit var viewModel: MainViewModel//by MainViewModel
    private lateinit var binding : FragmentShoeDetailBinding
    //private lateinit var bindingList : FragmentShoeListBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
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
        binding.saveButton.setOnClickListener { view: View? ->
            //viewModel.addCustomView()
            viewModel.setBooleanTrue()
            viewModel.setNum()
            val saved = viewModel.saved.value
            //TODO: Unsure why "ShoeDetailDirections" became invalid..
            val action = ShoeDetailDirections.actionShoeDetailToShoeList(saved)
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