package com.example.shoestoreproject.list


import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.*
import android.widget.EditText
import android.widget.LinearLayout
import android.widget.TextView
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
import kotlinx.android.synthetic.main.custom_detail.*
import kotlinx.android.synthetic.main.custom_detail.view.*
import kotlinx.android.synthetic.main.custom_detail.view.companyName_text
import kotlinx.android.synthetic.main.custom_detail.view.constraintLayout
import kotlinx.android.synthetic.main.custom_detail.view.description_text
import kotlinx.android.synthetic.main.custom_detail.view.shoeName_text
import kotlinx.android.synthetic.main.custom_detail.view.shoeSize_text
import kotlinx.android.synthetic.main.fragment_shoe_detail.view.*
import org.w3c.dom.Text
import timber.log.Timber


class ShoeList : Fragment() {
    private lateinit var viewModel: ShoeListViewModel
    private lateinit var binding: FragmentShoeListBinding

    //view to be added dynamically
    private lateinit var v: View

    //layout the view will be added to
    private lateinit var myLayout: LinearLayout

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

        //adds shoes to the layout
        binding.FABButton.setOnClickListener { view: View? ->
            view?.findNavController()?.navigate(R.id.action_shoeList_to_shoeDetail)
        }


        viewModel = ViewModelProvider(requireActivity()).get(ShoeListViewModel::class.java)
        viewModel.setReturnFalse()
        //adds each object to the list layout
        viewModel.array.observe(viewLifecycleOwner, Observer { array ->
            if (!array.isEmpty()) {
                viewModel.array.value!!.forEachIndexed { index, element ->
                    viewModel.setId(index)
                    addCustomView(index)
                }
            }
        })

        //lets compiler know that the fragment has an options menu
        setHasOptionsMenu(true)

        return binding.root
    }


    fun addCustomView(index: Int): (Any) -> Unit {
        //https://stackoverflow.com/questions/6216547/android-dynamically-add-views-into-view
        val vi: LayoutInflater =
            this.context?.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        v = vi.inflate(R.layout.custom_detail, null)

        //adding a view to the LinearLayout
        //https://stackoverflow.com/questions/2395769/how-to-programmatically-add-views-to-views
        myLayout = binding.linearLayoutId
        v.setLayoutParams(
            LinearLayout.LayoutParams(
                LinearLayout.LayoutParams.WRAP_CONTENT,
                LinearLayout.LayoutParams.WRAP_CONTENT
            )
        )

        val textViewCompanyName = v.findViewById<View>(R.id.companyName_text) as TextView
        textViewCompanyName.companyName_text.setText(viewModel.array.value?.get(index)?._companyName?.value.toString())

        val textViewShoeName = v.findViewById<View>(R.id.shoeName_text) as TextView
        textViewShoeName.shoeName_text.setText(viewModel.array.value?.get(index)?._shoeName?.value.toString())

        val textViewShoeSize = v.findViewById<View>(R.id.shoeSize_text) as TextView
        textViewShoeSize.shoeSize_text.setText(viewModel.array.value?.get(index)?._shoeSize?.value.toString())

        val textViewShoeDescription = v.findViewById<View>(R.id.description_text) as TextView
        textViewShoeDescription.description_text.setText(viewModel.array.value?.get(index)?._shoeDescription?.value.toString())

        val textViewNumStock = v.findViewById<View>(R.id.num_Inventory) as TextView
        textViewNumStock.num_Inventory.append(viewModel.array.value?.get(index)?._numStock?.value.toString())

        v.button.setOnClickListener()
        {
            viewModel.setReturnTrue()
            viewModel.setId(index)
            findNavController().navigate(R.id.action_shoeList_to_shoeDetail)
        }

        myLayout.addView(v)

        return { print("") }
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
}

