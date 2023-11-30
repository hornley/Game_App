package com.example.hornley.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hornley.database.database.GameDatabase
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.repository.EnemyRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EnemyViewModel(application: Application): AndroidViewModel(application) {

    private val enemyReadAllData: LiveData<List<Enemy>>
    private val enemyRepository: EnemyRepository

    init {
        val enemyDao = GameDatabase.getDatabase(application).enemyDao()
        enemyRepository = EnemyRepository(enemyDao)
        enemyReadAllData = enemyRepository.readAllData
    }

    fun addEnemy(enemy: Enemy) {
        viewModelScope.launch(Dispatchers.IO) {
            enemyRepository.addEnemy(enemy)
        }
    }

    fun updateEnemy(enemy: Enemy) {
        viewModelScope.launch(Dispatchers.IO) {
            enemyRepository.updateEnemy(enemy)
        }
    }

    fun deleteEnemy(enemy: Enemy) {
        viewModelScope.launch(Dispatchers.IO) {
            enemyRepository.deleteEnemy(enemy)
        }
    }

}