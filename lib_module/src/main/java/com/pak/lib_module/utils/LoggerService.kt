package com.pak.mvvm.utils

import android.util.Log
import javax.inject.Inject

class LoggerService @Inject constructor(){
    fun log(TAG : String, msg : String){
        Log.d(TAG, msg)
    }
}