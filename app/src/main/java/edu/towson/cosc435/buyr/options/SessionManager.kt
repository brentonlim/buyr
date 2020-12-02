package edu.towson.cosc435.buyr.options

import android.content.Context
import android.content.SharedPreferences

class SessionManager {
    private lateinit var sharedPreferences:SharedPreferences
    private lateinit var editor:SharedPreferences.Editor

    fun sessionManager(ctx: Context){
        sharedPreferences = ctx.getSharedPreferences(SHARED_PREF_NAME, Context.MODE_PRIVATE)

        editor = sharedPreferences.edit()
    }

    companion object{
        val SHARED_PREF_NAME = "session"
        val SESSION_KEY = "session_user"
    }

    fun saveSession(user:User){
        //user is logged in, save the session
        val id = user.id

        editor.putInt(SESSION_KEY, id).commit()

    }

    fun getSession() : Int{
        //return saved session of user
        return sharedPreferences.getInt(SESSION_KEY, -1)
    }

    fun removeSession(){
        //set user's ID to -1 so that they are  considered NOT LOGGED IN
        //we want to access this in MainActivity, taking from the fragment_options.xml
        editor.putInt(SESSION_KEY, -1).commit()
    }
}
