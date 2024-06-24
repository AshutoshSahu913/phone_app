package com.example.phoneapp.Ui_Layer.Pref

import androidx.datastore.preferences.core.intPreferencesKey
import androidx.datastore.preferences.core.stringPreferencesKey
import kotlinx.coroutines.flow.Flow

val USER_KEY = intPreferencesKey("user_key")
val USER_NAME_KEY = stringPreferencesKey("user_name_key")
val USER_PHONE_KEY = stringPreferencesKey("user_phone_key")
val USER_EMAIL_KEY = stringPreferencesKey("user_email_key")
val USER_IMG_KEY = stringPreferencesKey("user_img_key")

interface UserPref {
    fun getUserId(): Flow<Int>
    suspend fun saveUserId(userId: Int)
    suspend fun deleteUserId()

    fun getUserName(): Flow<String>
    suspend fun saveUserName(userName: String)
    suspend fun deleteUserName()

    fun getUserPhoneNumber(): Flow<String>
    suspend fun saveUserPhoneNumber(userPhoneNumber: String)
    suspend fun deleteUserPhoneNumber()

    fun getUserEmail(): Flow<String>
    suspend fun saveUserEmail(userEmail: String)
    suspend fun deleteUserEmail()

    fun getUserImg(): Flow<String>
    suspend fun saveUserImg(uri: String)
    suspend fun deleteUserImg()
}
