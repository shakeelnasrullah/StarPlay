package com.pak.lib_module.dao
import androidx.lifecycle.LiveData
import androidx.room.*
import com.pak.lib_module.models.Media

@Dao
interface MediaDao {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertMedia(media: List<Media>)

    @Query("select * from media")
    fun getMediaList() : LiveData<List<Media>>
}