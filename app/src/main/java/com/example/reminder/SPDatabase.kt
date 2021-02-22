package com.example.reminder


import android.content.Context
import android.content.SharedPreferences
import androidx.core.content.edit


object SPDatabase {

    private lateinit var ContextByCyrus: Context
    private const val SHARED_PREFERENCES_TABLE = "SP1"


    @JvmStatic
    fun init(context: Context) {
        ContextByCyrus = context.applicationContext
    }


    private fun getSPInstance(): SharedPreferences {
//        val sp = ContextByCyrus.getSharedPreferences(SHARED_PREFERENCES_TABLE, Context.MODE_PRIVATE)
//        return sp
        val sp: SharedPreferences by lazy(mode = LazyThreadSafetyMode.SYNCHRONIZED) {
            ContextByCyrus.getSharedPreferences(SHARED_PREFERENCES_TABLE, Context.MODE_PRIVATE)
        }
        return sp

    }

    @JvmStatic
    fun putString(key: String, value: String, commit: Boolean = false) {
        val sp = getSPInstance()
        sp.edit(commit) {
            putString(key, value)
        }
    }

    @JvmStatic
    fun getString(key: String, default: String = ""): String {
        val sp = getSPInstance()
        return sp.getString(key, default) ?: ""
 //       return sp.getString(key, default)
    }

    @JvmStatic
    fun putInt(key: String, value: Int, commit: Boolean = false) {
        val sp = getSPInstance()
        sp.edit(commit) { putInt(key, value) }
    }

    @JvmStatic
    fun getInt(key: String, default: Int = 0): Int {
        val sp = getSPInstance()
        return sp.getInt(key, default)
    }

    @JvmStatic
    fun putLong(key: String, value: Long, commit: Boolean = false) {
        val sp = getSPInstance()
        sp.edit(commit) { putLong(key, value) }
    }

    @JvmStatic
    fun getLong(key: String, default: Long = 0L): Long {
        val sp = getSPInstance()
        return sp.getLong(key, default)
    }

    @JvmStatic
    fun putFloat(key: String, value: Float, commit: Boolean = false) {
        val sp = getSPInstance()
        sp.edit(commit) { putFloat(key, value) }
    }

    @JvmStatic
    fun getFloat(key: String, default: Float = 0F): Float {
        val sp = getSPInstance()
        return sp.getFloat(key, default)
    }

    @JvmStatic
    fun putBoolean(key: String, value: Boolean, commit: Boolean = false) {
        val sp = getSPInstance()
        sp.edit(commit) { putBoolean(key, value) }
    }

    @JvmStatic
    fun getBoolean(key: String, default: Boolean = false): Boolean {
        val sp = getSPInstance()
        return sp.getBoolean(key, default)
    }

    @JvmStatic
    fun putStringSet(
            key: String,
            value: MutableSet<String>?,
            commit: Boolean = false
    ) {
        val sp = getSPInstance()
        sp.edit(commit) { putStringSet(key, value) }
    }

    @JvmStatic
    fun getStringSet(key: String): MutableSet<String>? {
        val sp = getSPInstance()
        return sp.getStringSet(key, null)
    }

}