package com.example.shoestoreproject.login
//package com.example.shoestoreproject.login.Login
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast

import androidx.databinding.DataBindingUtil
//import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.findNavController
import com.example.shoestoreproject.R

//C:\Users\Anton\Downloads\ShoeStoreProject\app\src\main\java\com\example\shoestoreproject\MainActivity.kt

//C:\Users\Anton\Downloads\ShoeStoreProject\app\src\main\java\com\example\shoestoreproject\login\LoginStart.kt
import com.example.shoestoreproject.databinding.FragmentLoginBinding


class LoginStart : Fragment() {
    //initialize the binding object
    private lateinit var viewModel: LoginStartViewModel
    private lateinit var binding: FragmentLoginBinding
    //private lateinit var viewModelLogin: LoginViewModel
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        //note need to implement Gradle dependency for lifecycle before using "this"
        viewModel = ViewModelProvider(this).get(LoginStartViewModel::class.java)
//ViewModelProvider(this.context).get(LoginStartViewModel::class.java)
        //updated in real time?
//        val emailText = binding.emailAddressText.toString()
        // Inflate the layout for this fragment
        binding = DataBindingUtil.inflate(
            inflater,
            R.layout.fragment_login,
            container,
            false
        )

        binding.loginButton.setOnClickListener { view: View? ->
            //pass in email text from editText to viewModel
            viewModel.setEmail(binding.emailAddressText.text.toString())
            viewModel.setPassword(binding.passwordText.text.toString())
            if (binding.emailAddressText.text.isEmpty() || binding.passwordText.text.isEmpty()){
                Toast.makeText(this.context,"Missing log-in information", Toast.LENGTH_SHORT).show()
            }
            else if (view != null && viewModel.checkOldEmail()) {
                view.findNavController().navigate(R.id.action_login_to_welcome)
            }
            else
                Toast.makeText(this.context,"Email does not exist", Toast.LENGTH_SHORT).show()

        }

        binding.newUserButton.setOnClickListener { view: View ->
            viewModel.setEmail(binding.emailAddressText.text.toString())
            viewModel.setPassword(binding.passwordText.text.toString())
            if (binding.emailAddressText.text.isEmpty() || binding.passwordText.text.isEmpty()){
                Toast.makeText(this.context,"Missing log-in information", Toast.LENGTH_SHORT).show()
            }
            else if (viewModel.checkNewEmail())
            {
                view.findNavController().navigate(R.id.action_login_to_welcome)
            }
            else
                Toast.makeText(this.context,"Email already in use", Toast.LENGTH_SHORT).show()
        }


        //Q: Why can I not call "findViewbyId" on the login fragment but can do so in MainActivity.kt?
        //A: https://stackoverflow.com/questions/9228777/findviewbyid-not-working-in-a-not-mainactivity-class

        //R.id.email_address_text.

        //returns the root of the xml file being inflated
        return binding.root
    }

}