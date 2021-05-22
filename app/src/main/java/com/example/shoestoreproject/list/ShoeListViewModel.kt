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
    private var array : MutableLiveData<MutableList<LiveDataClass>>//MutableList<LiveDataClass>
    val _array : LiveData<MutableList<LiveDataClass>>
    get() = array
    var i = 0
    private lateinit var _companyName : MutableLiveData<String>


    private val _saved = MutableLiveData<Boolean>()
    val saved : LiveData<Boolean>
    get() = _saved

    private val _num = MutableLiveData<Int>()
    val num : LiveData<Int>
    get() = _num
    //Q: Why can't you reference the mutableList object "array" outside of a method?

    init {
        _saved.value = saved
        _num.value = 0
        array = MutableLiveData() //Collection<LiveDataClass>
    }

    fun createObject()
    {
        //pass in the information from shoeDetail to here
        var myObject = LiveDataClass()
        myObject._companyName.value
        myObject._shoeName.value
        array.value?.add(myObject)

    }

    public class LiveDataClass()
    {
        var _companyName = MutableLiveData<String>()
        val _shoeName = MutableLiveData<String>()
        val _shoeSize = MutableLiveData<Int>()
        val _shoeDescription = MutableLiveData<String>()
     /*
        fun setCompanyName()
        {
            _companyName.value =
        }

      */
    }

    val mutableList : MutableList<LiveDataClass> = ArrayList()





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

