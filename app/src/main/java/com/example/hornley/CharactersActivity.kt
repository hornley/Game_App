package com.example.hornley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController
import com.example.hornley.database.model.Character
import com.example.hornley.databinding.ActivityCharactersBinding
import com.example.hornley.database.model.Item
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.model.Skill
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.EnemyViewModel
import com.example.hornley.database.viewmodel.ItemViewModel
import com.example.hornley.database.viewmodel.SkillViewModel
import org.json.JSONObject

class CharactersActivity : AppCompatActivity() {

    private lateinit var binding: ActivityCharactersBinding
    private lateinit var eUserViewModel: EnemyViewModel
    private lateinit var iUserViewModel: ItemViewModel
    private lateinit var sUserViewModel: SkillViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityCharactersBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setupActionBarWithNavController(findNavController(R.id.characterFragment))

        eUserViewModel = ViewModelProvider(this).get(EnemyViewModel::class.java)
        iUserViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        sUserViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.characterFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}