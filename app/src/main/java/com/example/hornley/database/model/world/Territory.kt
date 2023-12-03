package com.example.hornley.database.model.world

import android.os.Parcelable
import androidx.room.Entity
import androidx.room.PrimaryKey
import kotlinx.parcelize.Parcelize

@Parcelize
@Entity(tableName = "territories_table")
data class Territory (
    @PrimaryKey(autoGenerate = true)
    val id: Int,
    val level: Int,
    val name: String,
    val city: ArrayList<City>,
    val town: ArrayList<Town>,
    val village: ArrayList<Village>
): Parcelable