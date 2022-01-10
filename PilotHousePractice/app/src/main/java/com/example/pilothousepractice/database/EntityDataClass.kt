package com.example.pilothousepractice.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class EntityDataClass(
    @PrimaryKey(autoGenerate = true)
    val key : Long = 0L,
    val name : String, val age : Int) {
}

data class EntityDataClassDTO( val key : Long, val name : String, val age : Int)

data class EntityDataClassDomain( val key : Long, val name : String, val age : Int)