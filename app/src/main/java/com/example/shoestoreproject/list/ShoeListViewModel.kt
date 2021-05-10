package com.example.shoestoreproject.list

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.widget.LinearLayout
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.navigation.Navigation
import com.example.shoestoreproject.MainActivity
import com.example.shoestoreproject.R

class ShoeListViewModel(saved : Boolean) : ViewModel() {
    private val _saved = MutableLiveData<Boolean>()
    val saved : LiveData<Boolean>
    get() = _saved
    private val _num = MutableLiveData<Int>()
    val num : LiveData<Int>
    get() = _num
    init {
        _saved.value = saved
        _num.value = 0
    }
    fun addCustomView()
    {

        /*
        LayoutInflater vi = (LayoutInflater) getApplicationContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
View v = vi.inflate(R.layout.your_layout, null);

// fill in any details dynamically here
TextView textView = (TextView) v.findViewById(R.id.a_text_view);
textView.setText("your text");

// insert into main view
ViewGroup insertPoint = (ViewGroup) findViewById(R.id.insert_point);
insertPoint.addView(v, 0, new ViewGroup.LayoutParams(ViewGroup.LayoutParams.FILL_PARENT, ViewGroup.LayoutParams.FILL_PARENT));
         */

    }
    fun setNum()
    {
        _num.value = 1
    }
    fun setBooleanTrue()
    {
        _saved.value = true
    }
    fun setBooleanFalse()
    {
        _saved.value = false
    }

}