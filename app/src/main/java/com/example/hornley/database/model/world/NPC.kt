package com.example.hornley.database.model.world

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "npcs_table")
data class NPC (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: Int,
    val name: String,

): Parcelable