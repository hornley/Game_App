package com.example.hornley.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hornley.database.model.Enemy


@Dao
interface  EnemyDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addEnemy(enemy: Enemy)

    @Update
    suspend fun updateEnemy(enemy: Enemy)

    @Delete
    suspend fun deleteEnemy(enemy: Enemy)

    @Query("SELECT * FROM enemies_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Enemy>>

}