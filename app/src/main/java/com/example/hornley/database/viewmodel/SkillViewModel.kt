package com.example.hornley.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hornley.database.database.GameDatabase
import com.example.hornley.database.model.Skill
import com.example.hornley.database.repository.SkillRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class SkillViewModel(application: Application): AndroidViewModel(application) {

    private val skillReadAllData: LiveData<List<Skill>>
    private val skillRepository: SkillRepository

    init {
        val skillDao = GameDatabase.getDatabase(application).skillDao()
        skillRepository = SkillRepository(skillDao)
        skillReadAllData = skillRepository.readAllData
    }

    fun addSkill(skill: Skill) {
        viewModelScope.launch(Dispatchers.IO) {
            skillRepository.addSkill(skill)
        }
    }

    fun updateSkill(skill: Skill) {
        viewModelScope.launch(Dispatchers.IO) {
            skillRepository.updateSkill(skill)
        }
    }

    fun deleteSkill(skill: Skill) {
        viewModelScope.launch(Dispatchers.IO) {
            skillRepository.deleteSkill(skill)
        }
    }

}