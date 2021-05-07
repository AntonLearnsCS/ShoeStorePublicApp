package com.example.shoestoreproject.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginStartViewModel : ViewModel() {
    //val binding : FragmentLoginBinding()
    private lateinit var hashMap: MutableMap<String,String>
    fun checkOldEmail() : Boolean
    {
        if (hashMap.size == 0)
        {
            Toast.makeText(this.context,"Account does not exist", Toast.LENGTH_SHORT).show()
            return false
        }
        else
        {
            //https://stackoverflow.com/questions/50824330/how-to-pass-edittext-value-to-viewmodel-and-livedata-kotlin
            if (hashMap.get(R.layout.MainActivityLogin.email_address_text.toString()) == binding.editTextTextPassword.toString())
            {
                return true
            }
            return false
        }
    }
    fun checkNewEmail () : Boolean
    {
        if (hashMap.size == 0)
        {
            hashMap.put(binding.emailAddressText.toString(), binding.editTextTextPassword.toString())
            return true
        }
        else
        {
            //only checks email address
            if (hashMap.containsKey(binding.emailAddressText.toString()))
            {
                Toast.makeText(this.context,"Email already in use", Toast.LENGTH_SHORT).show()
                return false
            }
            return true
        }
    }
}