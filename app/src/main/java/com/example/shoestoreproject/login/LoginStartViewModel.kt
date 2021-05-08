package com.example.shoestoreproject.login

import android.widget.Toast
import androidx.lifecycle.ViewModel
import com.example.shoestoreproject.R
import com.example.shoestoreproject.databinding.FragmentLoginBinding
import kotlinx.android.synthetic.main.fragment_login.view.*

class LoginStartViewModel : ViewModel() {
    //val binding : FragmentLoginBinding()
    private lateinit var email : String
    private lateinit var password : String
    var hashMap: HashMap<String, String> = HashMap<String, String>(10)
    fun checkOldEmail() : Boolean
    {
        if (hashMap.size == 0)
        {
            return false
        }
        else
        {
            //https://stackoverflow.com/questions/50824330/how-to-pass-edittext-value-to-viewmodel-and-livedata-kotlin
            //compares string returned from hashmap to string found in editText : password
            if (hashMap.get(email) == password)
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
            hashMap.put(email, password)
            return true
        }
        else
        {
            //only checks email address
            if (hashMap.containsKey(email))
            {
                return false
            }
            return true
        }
    }
    fun setEmail(email : String)
    {
        this.email = email
    }
    fun setPassword(password : String)
    {
        this.password = password
    }
}