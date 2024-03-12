package com.mobile.proyectofinal.data

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map


val Context.dataStore: DataStore<Preferences> by preferencesDataStore(name = "settings")

class SettingsDataStore(context: Context) {
    private val dataStore = context.dataStore

    val countryFlow: Flow<String> = dataStore.data.map { preferences ->
        preferences[PreferencesKeys.COUNTRY_KEY] ?: "US"
    }

    suspend fun updateCountry(country: String) {
        dataStore.edit { preferences ->
            preferences[PreferencesKeys.COUNTRY_KEY] = country
        }
    }

    private object PreferencesKeys {
        val COUNTRY_KEY = stringPreferencesKey("country_key")
    }
}