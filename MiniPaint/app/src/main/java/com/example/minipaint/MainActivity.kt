package com.example.minipaint

import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.WindowInsetsController
import androidx.annotation.RequiresApi
import androidx.core.view.WindowCompat
import androidx.core.view.WindowInsetsCompat
import androidx.core.view.WindowInsetsControllerCompat
private  lateinit var myGlobalCanvasView:  MyCanvasView
class MainActivity : AppCompatActivity() {
    @RequiresApi(Build.VERSION_CODES.R)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val myCanvasView = MyCanvasView(this)

        myGlobalCanvasView = myCanvasView

   /*     myCanvasView.windowInsetsController?.let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsController.BEHAVIOR_SHOW_BARS_BY_SWIPE
            controller.show(WindowInsetsCompat.Type.systemBars())
                    WindowCompat.setDecorFitsSystemWindows(window, true)

        }*/
        hideSystemUI()
        showSystemUI()

        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        setContentView(myCanvasView)

    }
    private fun hideSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, false)
        WindowInsetsControllerCompat(window, myGlobalCanvasView).let { controller ->
            controller.hide(WindowInsetsCompat.Type.systemBars())
            controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
        }
    }

    private fun showSystemUI() {
        WindowCompat.setDecorFitsSystemWindows(window, true)
        WindowInsetsControllerCompat(window, myGlobalCanvasView).show(WindowInsetsCompat.Type.systemBars())
    }
    //TODO: How to implement "myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN" given it is depracated, currently
    // following link below
    //TODO: reference: https://stackoverflow.com/questions/62577645/android-view-view-systemuivisibility-deprecated-what-is-the-replacement
   /* private fun hideSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(window, false)
    WindowInsetsControllerCompat(window, mainContainer).let { controller ->
        controller.hide(WindowInsetsCompat.Type.systemBars())
        controller.systemBarsBehavior = WindowInsetsControllerCompat.BEHAVIOR_SHOW_TRANSIENT_BARS_BY_SWIPE
    }
}

private fun showSystemUI() {
    WindowCompat.setDecorFitsSystemWindows(window, true)
    WindowInsetsControllerCompat(window, mainContainer).show(WindowInsetsCompat.Type.systemBars())
}
    }*/
}