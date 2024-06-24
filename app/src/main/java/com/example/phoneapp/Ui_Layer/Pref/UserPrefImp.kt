package com.example.phoneapp.Ui_Layer.Pref

import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.emptyPreferences
import com.example.phoneapp.R
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.map

class UserPrefImp(private val dataStore: DataStore<Preferences>) : UserPref {
    override fun getUserId(): Flow<Int> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_KEY] ?: 0
        }
    }

    override suspend fun saveUserId(userId: Int) {
        dataStore.edit {
            it[USER_KEY] = userId
        }
    }

    override suspend fun deleteUserId() {
        dataStore.edit { pref ->
            pref.remove(USER_KEY)
        }
    }

    override fun getUserName(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_NAME_KEY] ?: ""
        }
    }

    override suspend fun saveUserName(userName: String) {
        dataStore.edit {
            it[USER_NAME_KEY] = userName
        }
    }

    override suspend fun deleteUserName() {
        dataStore.edit { pref ->
            pref.remove(USER_NAME_KEY)
        }
    }

    override fun getUserPhoneNumber(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_PHONE_KEY] ?: ""
        }
    }

    override suspend fun saveUserPhoneNumber(userPhoneNumber: String) {
        dataStore.edit {
            it[USER_PHONE_KEY] = userPhoneNumber
        }
    }

    override suspend fun deleteUserPhoneNumber() {
        dataStore.edit { pref ->
            pref.remove(USER_PHONE_KEY)
        }
    }

    override fun getUserEmail(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_EMAIL_KEY] ?: ""
        }
    }

    override suspend fun saveUserEmail(userEmail: String) {
        dataStore.edit {
            it[USER_EMAIL_KEY] = userEmail
        }
    }

    override suspend fun deleteUserEmail() {
        dataStore.edit { pref ->
            pref.remove(USER_EMAIL_KEY)
        }
    }

    override fun getUserImg(): Flow<String> {
        return dataStore.data.catch {
            emit(emptyPreferences())
        }.map {
            it[USER_IMG_KEY] ?: R.drawable.image.toString()
        }
    }

    override suspend fun saveUserImg(uri: String) {
        dataStore.edit {
            it[USER_IMG_KEY] = uri
        }
    }

    override suspend fun deleteUserImg() {
        dataStore.edit { pref ->
            pref.remove(USER_IMG_KEY)
        }
    }
}
