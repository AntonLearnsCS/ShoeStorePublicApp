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

//Q: Why couldn't I delete the directory/repository "UdacityProject0" when it was listed as a
//directory. Issue was solved using GUI of folder files and deleting from there
// https://stackoverflow.com/questions/56873278/how-to-fix-error-filename-does-not-have-a-commit-checked-out-fatal-adding

lateinit var viewModel : MainViewModel
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }

    override fun onBackPressed() {
//not sure how to implement this solution: https://stackoverflow.com/questions/50689206/how-i-can-retrieve-current-fragment-in-navhostfragment
        viewModel.back.value

        if (viewModel.back.value!!)
        {
            Toast.makeText(this.applicationContext,"Cannot go back, click logout on menu of List to return to login",Toast.LENGTH_SHORT).show()
            return
        }
        else
            super.onBackPressed()
        }
    }
