package com.example.shoestoreproject

import android.app.Application
import timber.log.Timber

//application class is the main object that the operating system uses to interact with your application
//it is normal to have the ApplicationClass be unused since it refers to global declarations; see dessert pusher for notes on this.
class ApplicationClass: Application() {
    override fun onCreate() {
        super.onCreate()
        Timber.plant(Timber.DebugTree())
    }

}