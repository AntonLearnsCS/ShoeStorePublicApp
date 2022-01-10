package com.example.pilothousepractice

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import com.example.pilothousepractice.database.Database
import com.example.pilothousepractice.database.EntityDataClass
import com.example.pilothousepractice.database.EntityDataClassDTO
import com.example.pilothousepractice.database.mDao
import junit.framework.Assert.assertEquals
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(JUnit4::class)
class DatabaseTest {

    private lateinit var database : Database
    @Before
    fun init()
    {
        database = Room.inMemoryDatabaseBuilder(ApplicationProvider.
        getApplicationContext(), Database::class.java).allowMainThreadQueries().build()
    }
    @After
    fun closeDb() = database.close()

    @Test
    fun DatabaseDaoTest()
    {
    //given an entity
        val item = EntityDataClass(123456,"mName",4)
        //when an item is inserted
        database.mDao.insertData(item)
        //then that same item is retrieved
        val returnedItem = database.mDao.getDataItem(123456)
        println("Name: " + returnedItem.name)
        assertEquals(item.name,(returnedItem.name))
    }
}