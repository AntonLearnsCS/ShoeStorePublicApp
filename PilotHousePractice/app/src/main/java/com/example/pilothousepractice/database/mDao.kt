package com.example.pilothousepractice.database

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query

@Dao
interface mDao {

    @Insert
    fun insertData(data : EntityDataClass)

    @Query("select * from EntityDataClass where `key` = :id")
    fun getDataItem(id : Long) : EntityDataClassDTO

}