package com.pak.lib_module.language

import android.app.Activity
import com.pak.lib_module.utils.Utils

class LanguageHelper {
    private val selectedLanguage = ""

    private fun getSupportedLanguages() = listOf(
        Language("English", "en"),
        Language("Arabic", "ar")
    )


    fun getLanguageList(activity: Activity?): Pair<Int, List<String>> {
        val list = getSupportedLanguages()
        val lang = getSelectedLanguage(activity!!)
        val rList = mutableListOf<String>()
        var selectedIndex = 0
        for (i in list.indices) {
            if(list[i].code == lang) {
                selectedIndex = i
            }
            rList.add(list[i].name)
        }
        return Pair(selectedIndex, rList)
    }

    fun getSelectedLanguage(activity : Activity): String {
        val selectedLang = Utils.getSelectedLanguage(activity)!!
        return if(selectedLang.equals("Arabic")) {"ar"}
        else "en"
    }
}

data class Language(val name: String, val code: String, var isSelected: Boolean = false)