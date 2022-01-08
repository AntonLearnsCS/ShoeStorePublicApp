package com.example.giltest

import androidx.lifecycle.MutableLiveData

data class MainState (
    val textLive : MutableLiveData<String> = MutableLiveData("Hello world!"))