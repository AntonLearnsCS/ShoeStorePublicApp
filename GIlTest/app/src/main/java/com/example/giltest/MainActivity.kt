package com.example.giltest

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.LayoutInflater
import androidx.databinding.DataBindingUtil
import com.example.giltest.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding
    val mainState = MainState()
    val mainEvent = MainEvent()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(LayoutInflater.from(this))

        binding.mainEvent = mainEvent
        binding.mainState = mainState

       binding.button.setOnClickListener {

       }


        setContentView(binding.root)
    }

    fun someFun( n : Int)
    {
        println(1)

        for (factor in 1..n)
        {
            if (n % factor == 0)
            {
                println(n)
            }
        }
    }

    fun mappingFun()
    {
        val list : List<String?>? = mutableListOf<String?>()

        val list2 = list?.
        filterNotNull()?.
        filter{ it -> !it.isEmpty()}?.
        forEach{


        }


    }
}