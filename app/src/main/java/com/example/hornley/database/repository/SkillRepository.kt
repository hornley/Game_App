package com.example.hornley.database.repository

import androidx.lifecycle.LiveData
import com.example.hornley.database.dao.SkillDao
import com.example.hornley.database.model.Skill

class SkillRepository(private val skillDao: SkillDao) {

    val readAllData: LiveData<List<Skill>> = skillDao.readAllData()

    suspend fun addSkill(skill: Skill) {
        skillDao.addSkill(skill)
    }

    suspend fun updateSkill(skill: Skill) {
        skillDao.updateSkill(skill)
    }

    suspend fun deleteSkill(skill: Skill) {
        skillDao.deleteSkill(skill)
    }

}