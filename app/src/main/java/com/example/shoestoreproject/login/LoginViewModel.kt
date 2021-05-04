package com.example.shoestoreproject.login

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel


class LoginViewModel : ViewModel() {
    private val _email = MutableLiveData<String>()
    val email : LiveData<String>
    get() = _email

    private val _name = MutableLiveData<String>()
    val name : LiveData<String>
    get() = _name

}