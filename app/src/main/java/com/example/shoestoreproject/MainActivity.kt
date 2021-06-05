package com.example.shoestoreproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.shoestoreproject.login.Welcome
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_welcome.*

lateinit var viewModel : MainViewModel
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
        viewModel.back.value

        if (viewModel.back.value!!)
        {
            //prevents going back to "onboarding screen"
            Toast.makeText(this.applicationContext,"Cannot go back, click logout on menu of List to return to login",Toast.LENGTH_SHORT).show()
            return
        }
        else
            super.onBackPressed()
        }
    }
