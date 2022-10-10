package com.pak.starplayz.ui.main

import android.app.Activity
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.pak.lib_module.language.LanguageHelper
import com.pak.lib_module.models.Media
import com.pak.lib_module.respository.MediaRepositoryImpl
import com.pak.lib_module.utils.Response
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val repository: MediaRepositoryImpl,
    private val languageHelper: LanguageHelper
) : ViewModel() {

    val mediaList: LiveData<Response<List<Media>>>
        get() = repository.searchResponse


    fun searchMedia(queryString: String, activity : Activity) {

        viewModelScope.launch {
            repository.searchMedia(queryString, languageHelper.getSelectedLanguage(activity))
        }

    }


}