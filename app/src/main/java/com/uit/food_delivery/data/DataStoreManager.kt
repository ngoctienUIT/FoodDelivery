package com.uit.food_delivery.data

import android.content.Context
import androidx.datastore.preferences.core.booleanPreferencesKey
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import com.uit.food_delivery.dataStore
import kotlinx.coroutines.flow.map

class DataStoreManager(private val context: Context) {
    suspend fun setString(key: String, value: String) {
        context.dataStore.edit {
            it[stringPreferencesKey(key)] = value
        }
    }

    fun getString(key: String) = context.dataStore.data.map {
        it[stringPreferencesKey(key)] ?: ""
    }

    suspend fun setBoolean(key: String, value: Boolean) {
        context.dataStore.edit {
            it[booleanPreferencesKey(key)] = value
        }
    }

    fun getBoolean(key: String) = context.dataStore.data.map {
        it[booleanPreferencesKey(key)]
    }

    suspend fun clearDataStore() = context.dataStore.edit { it.clear() }
}