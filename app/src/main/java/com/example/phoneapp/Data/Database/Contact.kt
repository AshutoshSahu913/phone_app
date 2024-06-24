package com.example.phoneapp.Data.Database

import android.net.Uri
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey


@Entity(tableName = "Contacts")
data class Contact(
    @PrimaryKey(autoGenerate = true) var id: Int = 0,
    @ColumnInfo(name = "UserName") var userName: String,
    @ColumnInfo(name = "PhoneNumber") var phoneNumber: String,
    @ColumnInfo(name = "Email") var emailAddress: String,
    @ColumnInfo(name = "DateOfCreation") var dataOfCreation: Long,
    @ColumnInfo(name = "IsActive") var isActive: Boolean,
    @ColumnInfo(name = "Image") var image: String

)