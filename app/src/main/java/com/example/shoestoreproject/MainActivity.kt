package com.example.shoestoreproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider

private lateinit var viewModel : MainViewModel
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
    }
}