package com.example.hornley.database.model

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.ForeignKey
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "characters_table")
data class Character (
    @PrimaryKey(autoGenerate = true)
    @ColumnInfo(name = "id") val id: Int,
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
    // vit, str, int, agi, dex, wis, luck, charisma, pDef, mDef, pPen, mPen, crtC, crtD, expMulti, moneyMulti, cons, bal, exp, expr, hunger, thirst
    val skills: ArrayList<Int>,
    @TypeConverters(DoubleConverter::class)
    val stats: ArrayList<Double>,
    // Fire, Water, Earth, Wind, Light, Dark
    val eResistance: ArrayList<Double>,
    @TypeConverters(InventoryConverter::class)
    val inventory: MutableMap<Int, Int>,
    @TypeConverters(ReputationConverter::class)
    val reputation: MutableMap<Int, Double>
): Parcelable
