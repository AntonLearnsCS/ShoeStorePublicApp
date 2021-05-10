package com.example.shoestoreproject.list

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class ShoeFactory (private val finalScore: Boolean) : ViewModelProvider.Factory {
        override fun <T : ViewModel?> create(modelClass: Class<T>): T {
            if (modelClass.isAssignableFrom(ShoeListViewModel::class.java)) {
                return ShoeListViewModel(finalScore) as T
            }
            throw IllegalArgumentException("Unknown ViewModel class")
        }

}