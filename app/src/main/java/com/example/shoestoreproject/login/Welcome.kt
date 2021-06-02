package com.example.shoestoreproject.login

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.shoestoreproject.MainViewModel
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentWelcomeBinding
import com.example.shoestoreproject.viewModel //here we are importing the variable from the Main activity; this must be done because the view
//model in MainActivity is specific to that activity (Main is not declared a fragment)
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_welcome.*


class Welcome : Fragment() {

    /*
    Login screen: Email and password fields and labels plus create and login buttons
Welcome onboarding screen
Instructions onboarding screen
Shoe /ItemListing screen
Shoe/Item Detail screen for adding a new shoe/item
     */
    private lateinit var binding : FragmentWelcomeBinding


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_welcome,
            container,
            false
        )
        Log.i("onCreateWelcome","welcome")
        //viewModel = ViewModelProvider(requireActivity()).get(MainViewModel::class.java)

        //checks if the current fragment in the NavHostFragment is the Welcome fragment
        if (myNavHostFragment.childFragmentManager.findFragmentById(R.id.welcome) == welcome)
        {
            viewModel.setBackTrue()
        }
        else
        viewModel.setBackFalse()


        //doesn't make sense because the statement below assumes that the Welcome fragment has a child fragment
        //val currentFragment = this.childFragmentManager.getFragments()?.get(0)?.toString() // findFragmentById(R.id.myNavHostFragment).
        binding.skipButton.setOnClickListener { view: View? ->
            if (view != null) {
                viewModel.setBackFalse()
                view.findNavController().navigate(R.id.action_welcome_to_shoeList)
            }

        }
        binding.nextButton.setOnClickListener { view : View ->
            viewModel.setBackFalse()
            view.findNavController().navigate(R.id.action_welcome_to_instructions) }
        return binding.root
    }

}