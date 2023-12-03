package com.example.hornley.database.model.world

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hornley.database.model.IntConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "countries_table")
data class Country (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: Int,
    val name: String,
    @TypeConverters(IntConverter::class)
    val areas: ArrayList<Int>,
//    val territory: ArrayList<Territory>,
//    val dungeon: ArrayList<Dungeon>
): Parcelable