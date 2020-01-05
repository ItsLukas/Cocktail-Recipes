package com.example.cocktailRecipes.data.provider

import android.content.Context
import android.content.SharedPreferences
import androidx.preference.PreferenceManager

class FilterProviderImpl(context: Context) : FilterProvider {
    private val appContext = context.applicationContext

    private val preferences: SharedPreferences
        get() = PreferenceManager.getDefaultSharedPreferences(appContext)

    override fun getFilterLetter(): String {
        val filterLetter = preferences.getString("FILTER_LETTER", "A")
        return filterLetter?.first().toString()
    }
}