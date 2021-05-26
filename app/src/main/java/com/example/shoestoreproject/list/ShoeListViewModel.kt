package com.example.shoestoreproject.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel(saved : Boolean) : ViewModel() {
    var tempVar = LiveDataClass()

    private var _array : MutableLiveData<MutableList<LiveDataClass>>//MutableList<LiveDataClass>
    val array : LiveData<MutableList<LiveDataClass>>
    get() = _array


    var testArray : MutableList<LiveDataClass> = mutableListOf()

    private val _counter : MutableLiveData<Int> = MutableLiveData()
    val counter : LiveData<Int>
    get() = _counter


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
        _array = MutableLiveData(mutableListOf<LiveDataClass>())//MutableLiveData() //Collection<LiveDataClass>
        _counter.value = 0
    }

    fun createObject()
    {
        //pass in the information from shoeDetail to here
        var myObject = LiveDataClass()
        tempVar = myObject


        _array.value?.add(myObject)

        counter.value?.plus(1)

    }

    public class LiveDataClass()
    {
        var _companyName = MutableLiveData<String>()
        val _shoeName = MutableLiveData<String>()
        val _shoeSize = MutableLiveData<String>()
        val _shoeDescription = MutableLiveData<String>()
    }

    fun assignCompanyName(name : String)
    {
        tempVar._companyName.value = name
    }
    fun assignShoeName(name : String)
    {
        tempVar._shoeName.value = name
    }
    fun assignShoeSize(size : String)
    {
        tempVar._shoeSize.value = size
    }
    fun assignShoeDescription(name : String)
    {
        tempVar._shoeDescription.value = name
    }

    val mutableList : MutableList<LiveDataClass> = ArrayList()


    val message = MutableLiveData<String>()

    fun sendMessage(text: String) {
        message.value = text
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

