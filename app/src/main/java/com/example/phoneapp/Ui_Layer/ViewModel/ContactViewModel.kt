package com.example.phoneapp.Ui_Layer.ViewModel

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.phoneapp.Data.Database.Contact
import com.example.phoneapp.Data.Database.ContactDatabase
import com.example.phoneapp.Ui_Layer.ContactState
import com.example.phoneapp.Ui_Layer.Pref.UserPrefImp
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.combine
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class ContactViewModel @Inject constructor(
    private val database: ContactDatabase,
    private val dataStore: DataStore<Preferences>
) : ViewModel() {
    private val userPref = UserPrefImp(dataStore)
    val userIdFlow = userPref.getUserId().stateIn(
        scope = viewModelScope,
        initialValue = 0,
        started = SharingStarted.WhileSubscribed()
    )
    val userNameFlow = userPref.getUserName().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )
    val userPhoneFlow = userPref.getUserPhoneNumber().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )
    val userEmailFlow = userPref.getUserEmail().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )

    val userImgFlow = userPref.getUserImg().stateIn(
        scope = viewModelScope,
        initialValue = "",
        started = SharingStarted.WhileSubscribed()
    )

    private val isSortedByName = MutableStateFlow(true)

    private val contacts = isSortedByName.flatMapLatest {
        if (it) {
            database.contactDao().getContactsSortName()
        } else {
            database.contactDao().getContactsSortDate()
        }
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(0),
        initialValue = emptyList()
    )

    private val _state = MutableStateFlow(ContactState())

    val state = combine(_state, contacts, isSortedByName) { state, contacts, isSortedByName ->
        state.copy(contact = contacts)
    }.stateIn(
        scope = viewModelScope,
        started = SharingStarted.WhileSubscribed(500),
        initialValue = ContactState()
    )

    // Fun to save contacts
    fun saveContact() {
        val contact = Contact(
            id = state.value.id.value,
            userName = state.value.name.value,
            phoneNumber = state.value.number.value,
            emailAddress = state.value.email.value ?: "",
            dataOfCreation = System.currentTimeMillis(),
            isActive = true,
            image = state.value.img.value.toString()
        )
        viewModelScope.launch {
            database.contactDao().addAndUpdateContact(contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.number.value = ""
        state.value.email.value = ""
        state.value.dataOfCreation.value = 0
        state.value.img.value = ""
    }

    fun changeSorting() {
        isSortedByName.value = !isSortedByName.value
    }

    fun deleteContacts() {
        val contact = Contact(
            id = state.value.id.value,
            userName = state.value.name.value,
            phoneNumber = state.value.number.value,
            emailAddress = state.value.email.value!!,
            dataOfCreation = state.value.dataOfCreation.value,
            isActive = true,
            image = state.value.img.value.toString()
        )
        viewModelScope.launch {
            database.contactDao().deleteContact(contact = contact)
        }
        state.value.id.value = 0
        state.value.name.value = ""
        state.value.number.value = ""
        state.value.email.value = ""
        state.value.dataOfCreation.value = 0
        state.value.img.value = ""
    }

    fun saveId(id: Int) {
        viewModelScope.launch {
            userPref.saveUserId(id)
        }
    }

    fun saveUserName(name: String) {
        viewModelScope.launch {
            userPref.saveUserName(name)
        }
    }

    fun savePhoneNumber(phone: String) {
        viewModelScope.launch {
            userPref.saveUserPhoneNumber(phone)
        }
    }

    fun saveEmail(email: String) {
        viewModelScope.launch {
            userPref.saveUserEmail(email)
        }
    }

    fun saveImg(img: String) {
        viewModelScope.launch {
            userPref.saveUserImg(img)
        }
    }
}