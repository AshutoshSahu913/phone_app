package com.example.phoneapp.Ui_Layer

import android.net.Uri
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.example.phoneapp.Data.Database.Contact

data class ContactState(
    val contact: List<Contact> = emptyList(),

    val id: MutableState<Int> = mutableStateOf(0),
    val name: MutableState<String> = mutableStateOf(""),
    val number: MutableState<String> = mutableStateOf(""),
    val email: MutableState<String?> = mutableStateOf(""),
    val dataOfCreation: MutableState<Long> = mutableStateOf(0),
    val img: MutableState<String?> = mutableStateOf(null)
)