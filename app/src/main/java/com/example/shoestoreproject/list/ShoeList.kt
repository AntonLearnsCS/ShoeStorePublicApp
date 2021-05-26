package com.example.shoestoreproject.list

//import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.LinearLayout
import android.widget.TextView
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.navArgs
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.customview.view.*
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

        //Q: LiveData is reverting to its default value..
        //A: Use requireActivity instead of "this" to ensure that the viewModel is tied to the activity.
        viewModel = ViewModelProvider(requireActivity(),factory).get(ShoeListViewModel::class.java)
        if (viewModel.array.value?.get(0) ?: null != null) {
            (0 until viewModel.counter.value!!).forEach(
                addCustomView()
            )
        }

        return binding.root//inflater.inflate(R.layout.fragment_shoe_list, container, false)

    }

    override fun onResume() {
        super.onResume()
        addCustomView()
        if(viewModel.saved.value == true)
        Log.i("arrayResume", viewModel?.array?.value?.get(0)?._companyName?.value.toString())
    }



    fun addCustomView(): (Any) -> Unit {
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
        val textView = v.findViewById<View>(R.id.companyName_text) as TextView//findViewById<View>(R.id.a_text_view) as TextView
        textView.companyName_text.setText( viewModel.array.value?.get(0)?._companyName?.value.toString())
        v.companyName_text.text
        i++
        myLayout.addView(v)

        printHello("dd")
        //(Any) -> Unit; requires an expression
        return { print("")}
    }
    fun printHello(name : String?) : Unit {
        if (name != null)
            print("Hello, $name!")
        else
            print("Hi there!")
        // We don't need to write 'return Unit.VALUE' or 'return', although we could
    }

    //issue of views not saving
    /*
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

     */

    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView called")
    }


}