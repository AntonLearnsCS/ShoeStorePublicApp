package com.example.shoestoreproject.list

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel

class ShoeListViewModel(saved : Boolean) : ViewModel() {
    private var _array : MutableLiveData<MutableList<LiveDataClass>>//MutableList<LiveDataClass>
    val array : LiveData<MutableList<LiveDataClass>>
    get() = _array

    var testArray : MutableList<LiveDataClass> = mutableListOf()
    var i = 0


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
    }

    fun createObject()
    {
        //pass in the information from shoeDetail to here
        var myObject = LiveDataClass()
        //Log.i("array0",myObject.toString())
        //myObject._companyName.value
        //myObject._shoeName.value
        //array.value?.add(myObject)
        testArray.add(myObject)
        myObject._companyName.value = "f"
        //testArray[0]._companyName.value = "companyName"
        if (testArray[0] != null) {
            Log.i("array1", testArray.get(0)._companyName.value.toString())
            if (testArray.get(0)._companyName.value.toString() != null)
                Log.i("array2","passed")
        }



        _array.value?.add(myObject)
        //object is not being added to array
        if (_array.value?.get(0) == null)
            Log.i("array","viewModelNull")

        Log.i("array", _array.value?.get(0)?._companyName?.value.toString())
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

