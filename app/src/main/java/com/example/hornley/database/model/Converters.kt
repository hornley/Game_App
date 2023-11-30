package com.example.hornley.database.model

import androidx.room.TypeConverter
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken

class DoubleConverter {

    @TypeConverter
    fun fromString(value: String): ArrayList<Double> {
        val listType = object: TypeToken<ArrayList<Double>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Double>): String {
        return Gson().toJson(list)
    }
}

class InventoryConverter {

    @TypeConverter
    fun fromString(value: String): MutableMap<Int, Int> {
        val mapType = object: TypeToken<MutableMap<Int, Int>>(){}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMutableMap(map: MutableMap<Int, Int>): String {
        return Gson().toJson(map)
    }
}

class ReputationConverter {

    @TypeConverter
    fun fromString(value: String): MutableMap<Int, Double> {
        val mapType = object: TypeToken<MutableMap<Int, Double>>(){}.type
        return Gson().fromJson(value, mapType)
    }

    @TypeConverter
    fun fromMutableMap(map: MutableMap<Int, Double>): String {
        return Gson().toJson(map)
    }
}

class IntConverter {

    @TypeConverter
    fun fromString(value: String): ArrayList<Int> {
        val listType = object: TypeToken<ArrayList<Int>>(){}.type
        return Gson().fromJson(value, listType)
    }

    @TypeConverter
    fun fromArrayList(list: ArrayList<Int>): String {
        return Gson().toJson(list)
    }
}