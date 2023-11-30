package com.example.hornley.database.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.example.hornley.database.database.GameDatabase
import com.example.hornley.database.model.Item
import com.example.hornley.database.repository.ItemRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ItemViewModel(application: Application): AndroidViewModel(application) {

    private val itemReadAllData: LiveData<List<Item>>
    private val itemRepository: ItemRepository

    init {
        val itemDao = GameDatabase.getDatabase(application).itemDao()
        itemRepository = ItemRepository(itemDao)
        itemReadAllData = itemRepository.readAllData
    }

    fun addItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.addItem(item)
        }
    }

    fun updateItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.updateItem(item)
        }
    }

    fun deleteItem(item: Item) {
        viewModelScope.launch(Dispatchers.IO) {
            itemRepository.deleteItem(item)
        }
    }

}