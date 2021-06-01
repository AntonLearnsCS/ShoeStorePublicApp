package com.example.shoestoreproject.list

//import androidx.test.core.app.ApplicationProvider.getApplicationContext

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import androidx.core.view.get
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.navigation.ui.NavigationUI
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.CustomDetailBinding
//import com.example.shoestoreproject.databinding.CustomviewBinding
import com.example.shoestoreproject.databinding.FragmentShoeListBinding
import kotlinx.android.synthetic.main.custom_detail.view.*
import timber.log.Timber


class ShoeList : Fragment() {
    private lateinit var viewModel : ShoeListViewModel
    private lateinit var binding : FragmentShoeListBinding

    //Q: bindingCustomm is not triggering the returnDetail function
    private lateinit var bindingCustom : CustomDetailBinding

    private lateinit var factory : ShoeFactory
    var i = 0
    private lateinit var v: View
    private lateinit var myLayout: LinearLayout
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

        bindingCustom = DataBindingUtil.inflate(
            inflater,
            R.layout.custom_detail,
            container,
            false
        )
        // Set the viewmodel for databinding - this allows the bound layout access to all of the
        // data in the VieWModel
        bindingCustom.shoeListCustom = this
        //bindingCustom.button.setOnClickListener { returnToDetail() }
        Timber.plant(Timber.DebugTree())
        //TODO: Figure out why onCreate and onDestroyView are called twice after navigating from Shoedetail
        Timber.i("onCreateCalled")
        binding.FABButton.setOnClickListener {view: View? ->  view?.findNavController()?.navigate(R.id.action_shoeList_to_shoeDetail) }

        //binding.testButton.setOnClickListener { addCustomView(0) }
        //TODO: Q: Why is the ShoeListArgs object not being generated?
        //A: Build -> Clean Project followed by Build -> Rebuild project seems to work
        val scoreFragmentArgs by navArgs<ShoeListArgs>()
        factory = ShoeFactory(scoreFragmentArgs.saved)

        //Q: LiveData is reverting to its default value..
        //A: Use requireActivity instead of "this" to ensure that the viewModel is tied to the activity.
        viewModel = ViewModelProvider(requireActivity(),factory).get(ShoeListViewModel::class.java)

        viewModel.setReturnFalse()

        viewModel.array.observe(viewLifecycleOwner, Observer { array ->
            if (!array.isEmpty()){
                viewModel.array.value!!.forEachIndexed{ index, element ->
                    Log.i("arrayID1:",index.toString())
                    addCustomView(index)
                }
            }
        })

        //Q: Will this refer to the button on the customView? A: Returns null object so far
        /*
        if (viewModel.saved.value == true){
        this.constraintLayout.getViewById(R.id.constraintLayout).button.setOnClickListener {
                view: View? ->  view?.findNavController()?.navigate(R.id.action_shoeList_to_shoeDetail)
        }}

         */

        Log.i("arrayNavigationHeight", this.binding.linearLayoutId.get(0).id.toString())
        //this.binding.linearLayoutId.get(0)//.findViewById<View>(binding.linearLayoutId[0])
        //return bindingCustom.root

        //lets compiler know that the fragment has an options menu
        setHasOptionsMenu(true)

        return binding.root//inflater.inflate(R.layout.fragment_shoe_list, container, false)
    }

    fun addCustomView(index : Int): (Any) -> Unit {
        //https://stackoverflow.com/questions/6216547/android-dynamically-add-views-into-view
        val vi : LayoutInflater = this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = vi.inflate(R.layout.custom_detail, null)
        //adding a view to the LinearLayout
        //https://stackoverflow.com/questions/2395769/how-to-programmatically-add-views-to-views
        myLayout = binding.linearLayoutId
        //val addedItem  = view
        v.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )
        //Q: How to add a viewGroup so that I wont have to create a variable for each textView in the customView layout
        val textViewCompanyName = v.findViewById<View>(R.id.companyName_text) as EditText//findViewById<View>(R.id.a_text_view) as TextView
        //note: "companyName_text" is synthetic
        textViewCompanyName.companyName_text.setText( viewModel.array.value?.get(index)?._companyName?.value.toString())

        val textViewShoeName = v.findViewById<View>(R.id.shoeName_text) as EditText
        textViewShoeName.shoeName_text.setText(viewModel.array.value?.get(index)?._shoeName?.value.toString())

        val textViewShoeSize = v.findViewById<View>(R.id.shoeSize_text) as EditText
        textViewShoeSize.shoeSize_text.setText(viewModel.array.value?.get(index)?._shoeSize?.value.toString())

        val textViewShoeDescription = v.findViewById<View>(R.id.description_text) as EditText
        textViewShoeDescription.description_text.setText(viewModel.array.value?.get(index)?._shoeDescription?.value.toString())

        v.button.setOnClickListener()
        {
            viewModel.setReturnTrue()
            //Log.i("arrayID: ", v.id.toString())
            viewModel.setId(index)
            findNavController().navigate(R.id.action_shoeList_to_shoeDetail)
        }
        //v.visibility = View.GONE
        //v.setFocusable(false)
        v.companyName_text.isFocusable = false
        v.shoeName_text.isFocusable = false
        v.shoeSize_text.isFocusable = false
        v.description_text.isFocusable = false


        //v.clearFocus()

       // val imm = getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager
        //imm.hideSoftInputFromWindow(view?.windowToken, 0)
        //v.companyName_text.setOnFocusChangeListener(v,false) //focusable(false)
        i++
        myLayout.addView(v)

        //getChildAt() returns the view at the specified position in the group.
        Log.i("arrayNavigation", this.binding.linearLayoutId.getChildAt(0).id.toString())
        Log.i("arrayNavigation1", myLayout.get(0).id.toString())

        //(Any) -> Unit; requires an expression
        return { print("")}
    }
    //inflates options menu
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.logout_menu, menu)
    }

    //navigates to the id set on the item of the options menu
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return NavigationUI.onNavDestinationSelected(item, requireView().findNavController())
                || super.onOptionsItemSelected(item)
    }

    fun returnToDetail()
    {
        println("Testing")
        viewModel.setReturnTrue()
        findNavController().navigate(R.id.action_shoeList_to_shoeDetail)
    }
    override fun onDestroyView() {
        super.onDestroyView()
        Timber.i("onDestroyView called")
    }



}