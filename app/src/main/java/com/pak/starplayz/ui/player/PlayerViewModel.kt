package com.pak.starplayz.ui.player

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.pak.lib_module.models.Media
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class PlayerViewModel @Inject constructor() : ViewModel() {


    private var _media: MutableLiveData<Media> = MutableLiveData()
    val media: LiveData<Media> = _media

    fun setMedia(media: Media) {
        _media.value = media
    }
}