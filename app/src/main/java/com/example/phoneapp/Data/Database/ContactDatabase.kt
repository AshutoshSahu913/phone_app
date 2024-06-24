package com.example.phoneapp.Data.Database

import androidx.room.Database
import androidx.room.RoomDatabase


@Database(entities = [Contact::class], version = 3, exportSchema = true)
abstract class ContactDatabase : RoomDatabase() {
    //Dao interface for the database
    abstract fun contactDao(): Dao
}