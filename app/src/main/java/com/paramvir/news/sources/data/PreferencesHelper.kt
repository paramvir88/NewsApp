package com.paramvir.news.sources.data

import android.content.Context
import android.content.SharedPreferences

/**
 * Interface for SharedPreferences used in the app.
 */
object PreferencesHelper {
    private const val PREF_NAME = "NewsPreferences"
    private const val KEY_SELECTED_SOURCES = "selected_sources"

    fun saveSelectedSources(context: Context, selectedSources: List<String>) {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        val editor = sharedPreferences.edit()
        editor.putStringSet(KEY_SELECTED_SOURCES, selectedSources.toSet())
        editor.apply()
    }

    fun getSelectedSources(context: Context): List<String> {
        val sharedPreferences: SharedPreferences =
            context.getSharedPreferences(PREF_NAME, Context.MODE_PRIVATE)
        return sharedPreferences.getStringSet(KEY_SELECTED_SOURCES, emptySet())?.toList()
            ?: emptyList()
    }
}
