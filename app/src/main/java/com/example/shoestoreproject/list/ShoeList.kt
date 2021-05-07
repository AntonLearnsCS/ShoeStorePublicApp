package com.example.shoestoreproject.list

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentShoeListBinding


class ShoeList : Fragment() {

    private lateinit var binding : FragmentShoeListBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

    }
    //Q: Need to select views from LinearLayout so I can reference them using the Floating Action Button
    //https://stackoverflow.com/questions/7552333/android-linearlayout-background-selector

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_shoe_list,
            container,
            false
        )
        binding.FABButton.setOnClickListener {addCustomView()  }


        //binding.secondRowText.text = "Hello"


        return binding.root//inflater.inflate(R.layout.fragment_shoe_list, container, false)

    }

    fun addCustomView()
    {

        //https://stackoverflow.com/questions/6216547/android-dynamically-add-views-into-view
        val vi : LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val v: View = vi.inflate(R.layout.customview, null)
        //adding a view to the LinearLayout
        //https://stackoverflow.com/questions/2395769/how-to-programmatically-add-views-to-views
        val myLayout: LinearLayout = binding.layoutId
        //val addedItem  = view
        v.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.MATCH_PARENT,
                LinearLayout.LayoutParams.MATCH_PARENT
            )
        )

        myLayout.addView(v)
    }

}