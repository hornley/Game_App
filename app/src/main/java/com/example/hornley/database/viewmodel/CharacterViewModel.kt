package com.example.hornley.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hornley.database.database.GameDatabase
import com.example.hornley.database.model.Character
import com.example.hornley.database.repository.CharacterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CharacterViewModel(application: Application): AndroidViewModel(application) {

    val charReadAllData: LiveData<List<Character>>
    private val characterRepository: CharacterRepository

    init {
        val characterDao = GameDatabase.getDatabase(application).characterDao()
        characterRepository = CharacterRepository(characterDao)
        charReadAllData = characterRepository.readAllData
    }

    fun addCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.addCharacter(character)
        }
    }

    fun updateCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.updateCharacter(character)
        }
    }

    fun deleteCharacter(character: Character) {
        viewModelScope.launch(Dispatchers.IO) {
            characterRepository.deleteCharacter(character)
        }
    }

}