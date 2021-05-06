package com.example.shoestoreproject.login

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentWelcomeBinding

// TODO: Rename parameter arguments, choose names that match
// the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
private const val ARG_PARAM1 = "param1"
private const val ARG_PARAM2 = "param2"

/**
 * A simple [Fragment] subclass.
 * Use the [Welcome.newInstance] factory method to
 * create an instance of this fragment.
 */
class Welcome : Fragment() {

    /*
    Login screen: Email and password fields and labels plus create and login buttons
Welcome onboarding screen
Instructions onboarding screen
Shoe /ItemListing screen
Shoe/Item Detail screen for adding a new shoe/item
     */
    private lateinit var binding : FragmentWelcomeBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }

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
        binding.nextButton.setOnClickListener { view : View ->
            if (view != null)
            view.findNavController().navigate(R.id.action_welcome_to_instructions) }
        return binding.root
        //return inflater.inflate(R.layout.fragment_welcome, container, false)
    }



}