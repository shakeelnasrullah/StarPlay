package com.pak.lib_module.respository

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.pak.lib_module.models.Media
import com.pak.lib_module.models.SearchResponse
import com.pak.lib_module.network.ApiService
import com.pak.lib_module.utils.Constants
import com.pak.lib_module.utils.Response
import javax.inject.Inject


interface MediaRepository {
    suspend fun searchMedia(queryString : String, language: String)
}


class MediaRepositoryImpl @Inject constructor(private val apiService: ApiService) : MediaRepository {

    private val searchResponse_ = MutableLiveData<Response<List<Media>>>()

    val searchResponse : LiveData<Response<List<Media>>>
        get() = searchResponse_

    override suspend fun searchMedia(queryString : String, language : String) {
        searchResponse_.postValue(Response.Loading())
        val result = apiService.searchMedia(Constants.API_KEY , queryString, language)
        if (result?.body() != null) {
            searchResponse_.postValue(Response.Success(result.body()?.results))
        } else
            searchResponse_.postValue(Response.Failure(result.message()))
    }
}