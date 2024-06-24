package com.example.phoneapp.Data.Database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import kotlinx.coroutines.flow.Flow

@Dao
interface Dao {

    @Upsert
    suspend fun addAndUpdateContact(contact: Contact)

    @Delete
    suspend fun deleteContact(contact: Contact)

    @Query("SELECT *  FROM Contacts ORDER BY UserName ASC")
    fun getContactsSortName(): Flow<List<Contact>>


    @Query("SELECT * FROM Contacts ORDER BY DateOfCreation DESC")
    fun getContactsSortDate(): Flow<List<Contact>>
}