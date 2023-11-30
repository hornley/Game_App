package com.example.hornley.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "enemies_table")
data class Enemy (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val vitality: Double,
    val strength: Double,
    val intelligence: Double,
    val agility: Double,
    val dexterity: Double,
    val wisdom: Double,
    val pDef: Double,
    val mDef: Double,
    val phyPenetration: Double,
    val magPenetration: Double,
    val criticalChance: Double,
    val criticalDamage: Double,
    val expGain: Double,
    val rewards: Double,
    val type: Double,
    @TypeConverters(DoubleConverter::class)
    val skills: ArrayList<Double>,
    val weaknesses: ArrayList<Double>
): Parcelable
