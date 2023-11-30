package com.example.hornley.database.model

import androidx.room.Entity
import android.os.Parcelable
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "items_table")
data class Item (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val rarity: String,
    val name: String,
    val category: String, // Armor/Weapon
    val subCategory: String,  // Armor Type and Weapon Type
    val cost: Double,
    val lvlReq: Int,
    val type: Int,  // Created, Normal (In-game)
    @TypeConverters(ReputationConverter::class)
    val effects: MutableMap<Int, Double>
): Parcelable