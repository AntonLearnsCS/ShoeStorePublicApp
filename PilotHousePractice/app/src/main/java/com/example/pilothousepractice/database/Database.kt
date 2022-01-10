package com.example.pilothousepractice.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

//Note: We declare the database and the DAO instance as abstract b/c Room persistance library will
//provide the implementation for us
@Database(entities = [EntityDataClass::class], version = 1, exportSchema = false)
abstract class Database : RoomDatabase()  {

    abstract val mDao : mDao
    companion object{
        val key = Any()
        @Volatile
        private var instance : com.example.pilothousepractice.database.Database? = null

        fun getDatabaseInstance(context : Context) : com.example.pilothousepractice.database.Database
        {
            synchronized(key){
                var mInstance = instance
            if (mInstance == null)
            {
                mInstance = Room.databaseBuilder(context.applicationContext,
                    com.example.pilothousepractice.database.Database::class.java,
                    "mDatabase").fallbackToDestructiveMigration().build()
                instance = mInstance
            }
                return mInstance
            }
        }
    }
}

