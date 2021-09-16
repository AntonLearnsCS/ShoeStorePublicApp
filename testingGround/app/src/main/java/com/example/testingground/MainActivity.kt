package com.example.testingground

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers.IO
import kotlinx.coroutines.Dispatchers.Main
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
private const val result_1 = "result_1"
private const val result_2 = "result_2"
/*
This activity displays the ability of coroutines to be suspended (stop -> resume) and therefore allows a new coroutine scope to run as
evident in the text displayed on the emulator
 */

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

    button.setOnClickListener {
        CoroutineScope(Main).launch {
            setTextInIO()
        }
        CoroutineScope(IO).launch {
            setTextInIOSecondScope()
        }
    }
    }
suspend fun setTextInIOSecondScope()
{
    val result2 = result2()
    setTextSecondScope(result2)
}

suspend fun setTextSecondScope(string: String)
{
    withContext(Main)
    {

        val mTextView = textView.text.toString() + string
        textView.text = mTextView
    }
}

suspend fun setText(string : String)
{
    withContext(Main)
    {
        val newText = textView.text.toString() + "\n$string"
        textView.text = newText
    }
}
suspend fun setTextInIO()
{
    val result1 = result1()
    setText(result1)

    //addResult2()

}
suspend fun addResult2()
{
    val result2 = result2()
    setText(result2)
}

    private suspend fun result1() : String
    {
        delay(3000)
        println("result1")
        return result_1
    }

    private suspend fun result2() : String
    {
        //delay(1000)
        println("\nresult2")
        return result_2
    }
}