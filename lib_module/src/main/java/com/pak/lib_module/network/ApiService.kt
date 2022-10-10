package com.pak.lib_module.network

import com.pak.lib_module.models.SearchResponse
import com.pak.lib_module.utils.Constants.API_KEY
import retrofit2.Response
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiService {

    @GET("3/search/multi")
    suspend fun searchMedia(
        @Query("api_key") apiKey: String = API_KEY,
        @Query("query") searchQuery: String,
        @Query("language") languageCode: String
    ): Response<SearchResponse>
}