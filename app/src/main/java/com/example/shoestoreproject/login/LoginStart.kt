package com.example.shoestoreproject.login
//package com.example.shoestoreproject.login.Login
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.EditText
import android.widget.Toast
import androidx.databinding.DataBindingUtil
import androidx.navigation.Navigation
import androidx.navigation.findNavController
import com.example.shoestoreproject.R

//C:\Users\Anton\Downloads\ShoeStoreProject\app\src\main\java\com\example\shoestoreproject\MainActivity.kt

//C:\Users\Anton\Downloads\ShoeStoreProject\app\src\main\java\com\example\shoestoreproject\login\LoginStart.kt
import com.example.shoestoreproject.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*


class LoginStart : Fragment() {
    //initialize the binding object
    private val emailList : MutableList<String> = ArrayList<String>(10)

    private lateinit var binding: FragmentLoginBinding
    //private lateinit var viewModelLogin: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )
        binding.loginButton.setOnClickListener { view: View? ->
            if (view != null) {
                view.findNavController().navigate(R.id.action_login_to_welcome)
            }
        }
        //TODO: Create a mapping array to check for newUser and LogIn
        val emailAddressText = binding.emailAddressText.text
        fun checkEmail (emailAddress: String) : Boolean
        {
            if (emailList.size == 0)
            {
                emailList.add(emailAddress)
                return true
            }
            else
            {
                if (emailAddress in emailList)
                {
                    Toast.makeText(this.context,"Email already in use",Toast.LENGTH_SHORT).show()
                    return false
                }
                return true
            }
        }
        binding.newUserButton.setOnClickListener { view: View ->
            if (checkEmail(emailAddressText.toString()))
            {
                view.findNavController().navigate(R.id.action_login_to_welcome)
            }
        }


        //Q: Why can I not call "findViewbyId" on the login fragment but can do so in MainActivity.kt?
        //A: https://stackoverflow.com/questions/9228777/findviewbyid-not-working-in-a-not-mainactivity-class

        //R.id.email_address_text.

        //returns the root of the xml file being inflated
        return binding.root
    }
}