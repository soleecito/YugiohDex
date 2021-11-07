package com.elvisoperator.yugiohdex

import android.content.Context

class Prefs (val context: Context) {

    val SHARED_NAME = "UserDataBase"
    val SHARED_USER_NAME = "username"
    val SHARED_DECK_NAME = "deck name"

    val storage = context.getSharedPreferences(SHARED_NAME, 0)

    fun saveName (name: String){
        storage.edit().putString(SHARED_USER_NAME, name).apply()
    }

    fun saveDeckName (deck: String){
        storage.edit().putString(SHARED_DECK_NAME, deck).apply()
    }

    fun getName() = storage.getString(SHARED_USER_NAME, "")!!

    fun getDeckName() = storage.getString(SHARED_DECK_NAME, "")!!

}