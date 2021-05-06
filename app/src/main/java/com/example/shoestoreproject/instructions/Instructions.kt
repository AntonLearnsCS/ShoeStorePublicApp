package com.example.shoestoreproject.instructions

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.navigation.findNavController
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentInstructionsBinding


class Instructions : Fragment() {

    private lateinit var binding : FragmentInstructionsBinding
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
            R.layout.fragment_instructions,
            container,
            false
        )
        binding.ListButton.setOnClickListener { view: View? ->
            if (view != null) {
                view.findNavController().navigate(R.id.action_instructions_to_shoeList)
            }
        }
        return binding.root//return inflater.inflate(R.layout.fragment_instructions, container, false)
    }

}