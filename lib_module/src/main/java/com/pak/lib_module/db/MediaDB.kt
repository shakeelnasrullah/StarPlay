package com.pak.lib_module.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.pak.lib_module.dao.MediaDao
import com.pak.lib_module.models.Media
import com.pak.lib_module.models.SearchResponse


@Database(entities = [SearchResponse::class, Media::class], version = 1)
@TypeConverters(MediaConverter::class, KnownForConverter::class, GenreConverter::class)
abstract class MediaDB : RoomDatabase() {

    abstract fun mediaDao(): MediaDao

    companion object {
        @Volatile
        private var INSTANCE: MediaDB? = null

        fun getDatabase(context: Context): MediaDB {
            if (INSTANCE == null) {
                synchronized(this) {
                    INSTANCE = Room.databaseBuilder(
                        context.applicationContext,
                        MediaDB::class.java,
                        "mediaDB"
                    ).build()
                }
            }
            return INSTANCE!!
        }
    }

}