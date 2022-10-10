package com.pak.lib_module.models

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "response")
data class SearchResponse(
    @PrimaryKey(autoGenerate = false)
    val page: Int,
    val results: List<Media>,
    val total_pages: Int,
    val total_results: Int
)