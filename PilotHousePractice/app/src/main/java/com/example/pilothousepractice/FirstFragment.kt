package com.example.pilothousepractice

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.pilothousepractice.database.EntityDataClassDomain
import com.example.pilothousepractice.databinding.FragmentFirstBinding
import com.example.pilothousepractice.recyclerview.Adapter

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class FirstFragment : Fragment() {

    private var _binding: FragmentFirstBinding? = null

    // This property is only valid between onCreateView and
    // onDestroyView.

    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = FragmentFirstBinding.inflate(inflater, container, false)
        binding.lifecycleOwner = this
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val List = mutableListOf<EntityDataClassDomain>(EntityDataClassDomain(123456L,"Bob",12),
            EntityDataClassDomain(67894L, "Jack", 12), EntityDataClassDomain(735542L,"Jill",14),
            EntityDataClassDomain(87645L, "Bread",98))
        val adapter = Adapter()

        val recyclerView = binding.recyclerView
        recyclerView.adapter = adapter
        adapter.submitList(List)

        binding.buttonFirst.setOnClickListener {
            findNavController().navigate(FirstFragmentDirections.actionFirstFragmentToSecondFragment("mString"))
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}