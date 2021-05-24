package com.example.shoestoreproject.list

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
//import androidx.test.core.app.ApplicationProvider.getApplicationContext
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentShoeListBinding
import timber.log.Timber


class ShoeList : Fragment() {
    private lateinit var viewModel : ShoeListViewModel
    private lateinit var binding : FragmentShoeListBinding
    private lateinit var factory : ShoeFactory
    var i = 0
    private lateinit var v: View
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
        Timber.plant(Timber.DebugTree())
        //TODO: Figure out why onCreate and onDestroyView are called twice after navigating from Shoedetail
        Timber.i("onCreateCalled")
        binding.FABButton.setOnClickListener {view: View? ->  view?.findNavController()?.navigate(R.id.action_shoeList_to_shoeDetail) }

        binding.testButton.setOnClickListener { view: View? -> addCustomView() }
        //TODO: Q: Why is the ShoeListArgs object not being generated?
        //A: Build -> Clean Project followed by Build -> Rebuild project seems to work
        val scoreFragmentArgs by navArgs<ShoeListArgs>()
        factory = ShoeFactory(scoreFragmentArgs.saved)

        //val scoreFragmentArgs by navArgs<ShoeListArgs>()
        //Log.d("ShoeList", "isSaved: ${scoreFragmentArgs.saved}")
        viewModel = ViewModelProvider(requireActivity(),factory).get(ShoeListViewModel::class.java)

        //Q: LiveData is reverting to its default value..
        //https://blog.mindorks.com/shared-viewmodel-in-android-shared-between-fragments potential solution but requires fragments to be added the
        //xml file, which may disrupt the NavHostFragment
        //potentially creating a new viewModel each time we go back to ShoeList
        //Also, the values of editText are not being saved in the viewModel

        //observing
        //TODO: _array does not exists within ShoeList


        //Log.i("arrayReal", viewModel._array.value?.get(0)?._companyName?.value.toString())

        if (viewModel.saved.value == true)
        {
            viewModel.array.observe(viewLifecycleOwner, Observer { myArray ->
                //myArray[0]
                if (myArray[0] != null)
                    Log.i("arrayReal", myArray[0]._companyName?.value.toString())
            })

        if (viewModel.testArray[0]._companyName != null)
        {
            Log.i("arrayTest2",viewModel.testArray[0]._companyName.value.toString())
        }}

        return binding.root//inflater.inflate(R.layout.fragment_shoe_list, container, false)

    }
/*
    override fun onResume() {
        super.onResume()
        viewModel._array.observe(viewLifecycleOwner, Observer { myArray ->
            //myArray[0]
            if (myArray[0] != null){
                Log.i("arrayTest0","null")
            if(myArray[0]._companyName.value != null)
                Log.i("arrayTest1","companyName exists")
            }


        Log.i("arrayTest",viewModel.testArray[0]._companyName.value.toString())
        })


    }

 */

    fun addCustomView()
    {
        //https://stackoverflow.com/questions/6216547/android-dynamically-add-views-into-view
        val vi : LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = vi.inflate(R.layout.customview, null)
        //adding a view to the LinearLayout
        //https://stackoverflow.com/questions/2395769/how-to-programmatically-add-views-to-views
        val myLayout: LinearLayout = binding.layoutId
        //val addedItem  = view
        v.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        v.setTag(i)
        i++
        myLayout.addView(v)
    }

    //issue of views not saving
    override fun onStart() {
        super.onStart()
        Timber.i("OnStart called")
        viewModel.saved.observe(viewLifecycleOwner, Observer { saved ->
            if (saved)
            {
                addCustomView()
                viewModel.setBooleanFalse()
            }
        })
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView called")
    }


}