package com.example.hornley.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters_table")
data class Character (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    var name: String,
    var characterClass: String,
    var subClass: String,
    var level: Int,
    var statPoint: Int,
    var tax: Int,
    var weapon: Int,
    var helm: Int,
    var chestPlate: Int,
    var leggings: Int,
    var boots: Int,
    @TypeConverters(IntConverter::class)
    val skills: ArrayList<Int>,
    @TypeConverters(DoubleConverter::class)
    val stats: ArrayList<Double>,
    val eResistance: ArrayList<Double>,
    @TypeConverters(InventoryConverter::class)
    val inventory: MutableMap<Int, Int>,
    @TypeConverters(ReputationConverter::class)
    val reputation: MutableMap<Int, Double>
): Parcelable
