package com.example.shoestoreproject.login
//package com.example.shoestoreproject.login.Login
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.shoestoreproject.R

//C:\Users\Anton\Downloads\ShoeStoreProject\app\src\main\java\com\example\shoestoreproject\MainActivity.kt

//C:\Users\Anton\Downloads\ShoeStoreProject\app\src\main\java\com\example\shoestoreproject\login\LoginStart.kt
import com.example.shoestoreproject.databinding.FragmentLoginBinding


class LoginStart : Fragment() {
    //initialize the binding object
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

        //        viewModel = ViewModelProvider(this).get(GameViewModel::class.java)
        //viewModelLogin = ViewModelProvider(this)
        //Q: Why can I not call "findViewbyId" on the login fragment but can do so in MainActivity.kt?
        //A: https://stackoverflow.com/questions/9228777/findviewbyid-not-working-in-a-not-mainactivity-class

        val name = binding.editTextTextEmailAddress.text
        Log.i("name","name: " + name)
        //returns the root of the xml file being inflated
        return binding.root
    }


}