package com.example.shoestoreproject

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.Navigation
import androidx.navigation.ui.NavigationUI
import com.example.shoestoreproject.login.Welcome
import kotlinx.android.synthetic.main.fragment_login.*
import kotlinx.android.synthetic.main.fragment_welcome.*

private lateinit var viewModel : MainViewModel
class MainActivity : AppCompatActivity() {
    private var backPressed : Boolean = false
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
     viewModel = ViewModelProvider(this).get(MainViewModel::class.java)

        val currentFragment =this.supportFragmentManager.findFragmentById(R.id.myNavHostFragment)
        if(getForegroundFragment() == welcome)
        {
            Log.i("arrayBack","correct Back")
            backPressed = true
        }
    }
    fun getForegroundFragment() : Fragment
    {
        var navHostFragment : Fragment? = getSupportFragmentManager().findFragmentById(R.id.myNavHostFragment)
        return  navHostFragment?.getChildFragmentManager()?.getFragments()?.get(0)!!
    }
//navHostFragment!! //== null ? null :

    override fun onBackPressed() {
        if(backPressed)
            return
        super.onBackPressed()
    }
}