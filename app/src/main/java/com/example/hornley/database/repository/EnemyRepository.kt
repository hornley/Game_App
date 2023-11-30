package com.example.hornley.database.repository

import androidx.lifecycle.LiveData
import com.example.hornley.database.dao.EnemyDao
import com.example.hornley.database.model.Enemy

class EnemyRepository(private val enemyDao: EnemyDao) {

    val readAllData: LiveData<List<Enemy>> = enemyDao.readAllData()

    suspend fun addEnemy(enemy: Enemy) {
        enemyDao.addEnemy(enemy)
    }

    suspend fun updateEnemy(enemy: Enemy) {
        enemyDao.updateEnemy(enemy)
    }

    suspend fun deleteEnemy(enemy: Enemy) {
        enemyDao.deleteEnemy(enemy)
    }

}