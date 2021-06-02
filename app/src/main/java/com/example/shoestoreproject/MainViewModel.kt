package com.example.shoestoreproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _back = MutableLiveData<Boolean>()
    val back : LiveData<Boolean>
        get() = _back
    init {
        _back.value = false
    }

    fun setBackTrue()
    {
        _back.value = true
    }
    fun setBackFalse()
    {
        _back.value = false
    }

}