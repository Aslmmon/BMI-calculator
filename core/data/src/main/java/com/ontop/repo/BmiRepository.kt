package com.ontop.repo

import android.content.Context
import com.google.gson.Gson
import com.ontop.data.model.BmiItem

class BmiRepository {

    fun getBmiResult(context: Context): List<BmiItem> {
        val assetManager = context.assets
        val inputStream = assetManager.open("bmi_Result.json")
        val json = inputStream.bufferedReader().use { it.readText() }
        val gson = Gson()
        val userList: List<BmiItem> = gson.fromJson(json, Array<BmiItem>::class.java).toList()
        return userList

    }
}


