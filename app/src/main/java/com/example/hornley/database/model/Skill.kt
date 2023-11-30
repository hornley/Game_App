package com.example.hornley.database.model

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "skills_table")
data class Skill (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val name: String,
    val skillClass: String,
    val skillLevelReq: Int,
    val fixedManaConsumption: Double,
    val percentageManaConsumption: Double,
    val type: Int,
    @TypeConverters(ReputationConverter::class)
    val statRatio: MutableMap<Int, Double>
): Parcelable