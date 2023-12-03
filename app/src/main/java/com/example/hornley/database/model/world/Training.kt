package com.example.hornley.database.model.world

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.example.hornley.database.model.Enemy
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "training_table")
data class Training (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: Int,
    val name: String,

): Parcelable