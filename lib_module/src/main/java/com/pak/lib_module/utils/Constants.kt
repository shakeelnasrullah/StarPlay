package com.pak.lib_module.utils

import com.pak.lib_module.models.Media


object Constants {

    const val BASE_URL = "https://api.themoviedb.org/"
    const val IMAGE_BASE_URL = "https://image.tmdb.org/t/p/original/"
    const val API_KEY = "8c17be34f66a11d9e8a5ff8aec9ea18a"

    const val mediaKey = "media.to.play"
    const val languageExtras = "lang.selected"
    const val VIDEO_URL = "http://commondatastorage.googleapis.com/gtv-videos-bucket/sample/ElephantsDream.mp4"

    // Filter the list according to media type
    private fun filterMediaTypes(mediaList: List<Media>): HashMap<String, ArrayList<Media>>{
        val mediaMap = HashMap<String, ArrayList<Media>>()
        for (media in mediaList){
            if(mediaMap.containsKey(media.media_type)){
                val list : ArrayList<Media>  = mediaMap.getValue(media.media_type)
                list.add(media)
            }else{
                mediaMap.put(media.media_type, arrayListOf(media))
            }
        }
        return mediaMap
    }

}