package com.example.hornley.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hornley.database.dao.CharacterDao
import com.example.hornley.database.dao.EnemyDao
import com.example.hornley.database.dao.ItemDao
import com.example.hornley.database.dao.SkillDao
import com.example.hornley.database.model.Character
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.model.DoubleConverter
import com.example.hornley.database.model.IntConverter
import com.example.hornley.database.model.InventoryConverter
import com.example.hornley.database.model.Item
import com.example.hornley.database.model.ReputationConverter
import com.example.hornley.database.model.Skill

@Database(entities = [Enemy::class, Item::class, Skill::class, Character::class], version = 1, exportSchema = false)
@TypeConverters(value=[DoubleConverter::class, InventoryConverter::class, ReputationConverter::class, IntConverter::class])
abstract class GameDatabase: RoomDatabase() {

    abstract fun enemyDao(): EnemyDao
    abstract fun characterDao(): CharacterDao
    abstract fun skillDao(): SkillDao
    abstract fun itemDao(): ItemDao

    companion object {
        @Volatile
        private var INSTANCE: GameDatabase? = null

        fun getDatabase(context: Context): GameDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    GameDatabase::class.java,
                    "game_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}