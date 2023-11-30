package com.example.hornley.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hornley.database.dao.CharacterDao
import com.example.hornley.database.model.Character
import com.example.hornley.database.model.DoubleConverter
import com.example.hornley.database.model.IntConverter
import com.example.hornley.database.model.InventoryConverter
import com.example.hornley.database.model.ReputationConverter

@Database(entities = [Character::class], version = 1, exportSchema = false)
@TypeConverters(value=[DoubleConverter::class, InventoryConverter::class, ReputationConverter::class, IntConverter::class])
abstract class CharacterDatabase: RoomDatabase() {

    abstract fun characterDao(): CharacterDao

    companion object {
        @Volatile
        private var INSTANCE: CharacterDatabase? = null

        fun getDatabase(context: Context): CharacterDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CharacterDatabase::class.java,
                    "character_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}