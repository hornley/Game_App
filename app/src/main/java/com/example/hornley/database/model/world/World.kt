package com.example.hornley.database.model.world

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hornley.database.model.IntConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "worlds_table")
data class World (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: Int,
    val name: String,
    @TypeConverters(IntConverter::class)
    val country: ArrayList<Int>
): Parcelable