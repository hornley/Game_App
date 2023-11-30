package com.example.hornley.database.dao

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.example.hornley.database.model.Skill

@Dao
interface SkillDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    suspend fun addSkill(skill: Skill)

    @Update
    suspend fun updateSkill(skill: Skill)

    @Delete
    suspend fun deleteSkill(skill: Skill)

    @Query("SELECT * FROM skills_table ORDER BY id ASC")
    fun readAllData(): LiveData<List<Skill>>

}