package com.pak.lib_module.db

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.pak.lib_module.models.Media

class MediaConverter {

    @TypeConverter
    fun fromList(value: List<Media>): String {
        val gson = Gson()
        val type = object : TypeToken<List<Media>>() {}.type
        return gson.toJson(value, type)
    }

    @TypeConverter
    fun toList(value: String): List<Media> {
        val gson = Gson()
        val type = object : TypeToken<List<Media>>() {}.type
        return gson.fromJson(value, type)
    }
}