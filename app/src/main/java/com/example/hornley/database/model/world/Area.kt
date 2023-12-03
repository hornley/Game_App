package com.example.hornley.database.model.world

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import androidx.room.TypeConverters
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.model.IntConverter
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "areas_table")
data class Area (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: Int,
    val name: String,
    @TypeConverters(IntConverter::class)
    val enemies: ArrayList<Int>,
//    val training: Map<String, Training>,
//    val npc: ArrayList<NPC>,
//    val villages: ArrayList<Village>
): Parcelable