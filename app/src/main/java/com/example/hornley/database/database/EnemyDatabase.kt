package com.example.hornley.database.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import com.example.hornley.database.dao.EnemyDao
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.model.DoubleConverter

@Database(entities = [Enemy::class], version = 1, exportSchema = false)
@TypeConverters(DoubleConverter::class)
abstract class EnemyDatabase: RoomDatabase() {

    abstract fun enemyDao(): EnemyDao

    companion object {
        @Volatile
        private var INSTANCE: EnemyDatabase? = null

        fun getDatabase(context: Context): EnemyDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    EnemyDatabase::class.java,
                    "enemy_database"
                ).build()
                INSTANCE = instance
                return instance
            }
        }
    }

}