package com.pak.lib_module.models

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.io.Serializable

@Entity(tableName = "media")
data class Media(
    val media_type: String,
    val name: String?,
    val overview: String?,
    val poster_path: String?,
    val profile_path: String?,
    val video: Boolean,
    @PrimaryKey(autoGenerate = true)
    val media_id: Long
) : Serializable