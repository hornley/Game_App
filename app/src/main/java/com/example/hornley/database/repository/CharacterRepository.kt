package com.example.hornley.database.repository

import androidx.lifecycle.LiveData
import com.example.hornley.database.model.Character
import com.example.hornley.database.dao.CharacterDao

class CharacterRepository(private val characterDao: CharacterDao) {

    val readAllData: LiveData<List<Character>> = characterDao.readAllData()

    suspend fun addCharacter(character: Character) {
        characterDao.addCharacter(character)
    }

    suspend fun updateCharacter(character: Character) {
        characterDao.updateCharacter(character)
    }

    suspend fun deleteCharacter(character: Character) {
        characterDao.deleteCharacter(character)
    }

}