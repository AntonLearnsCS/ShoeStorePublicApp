package com.example.shoestoreproject

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {

    private val _saved = MutableLiveData<Boolean>()
    val saved : LiveData<Boolean>
        get() = _saved
    private val _num = MutableLiveData<Int>()
    val num : LiveData<Int>
        get() = _num
    init {
        _saved.value = false
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