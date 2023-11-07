package com.rydot.iotRemote.utils

import android.annotation.SuppressLint
import android.content.Context

class SharedPrefs {
    companion object {
        @SuppressLint("SuspiciousIndentation")
        fun setValue(context: Context, key:String, value:Any?) {
            val sharedPref=context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            when(value) {
                is Int -> sharedPref.edit().putInt(key, value).apply()
                is String -> sharedPref.edit().putString(key, value).apply()
                is Boolean -> sharedPref.edit().putBoolean(key,value).apply()
            }
        }

        fun setValueBoolean(context: Context, key:String, value:Boolean) {
            val sharedPref = context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            sharedPref.edit().putBoolean(key, value).apply()
        }

        fun getValueBoolean(context: Context, key:String, defaultVal:Boolean):Any {
            val sharedPref=context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            return sharedPref.getBoolean(key, defaultVal)

        }
        fun getValue(context: Context, key:String, defaultVal:Any):Any {
            val sharedPref=context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            when(defaultVal) {
                is Int -> return sharedPref.getInt(key, defaultVal)
                is String -> return sharedPref.getString(key, defaultVal)?:""
                is Boolean -> return sharedPref.getBoolean(key,defaultVal)
                else-> return 0
            }
        }

        fun remove(context: Context, key: String){
            val sharedPref=context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            sharedPref.edit().remove(key).apply()
        }

        fun clear(context: Context){
            val sharedPref=context.getSharedPreferences(context.packageName, Context.MODE_PRIVATE)
            sharedPref.edit().clear().apply()
        }
    }
}