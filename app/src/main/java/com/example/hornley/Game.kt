package com.example.hornley

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import com.example.hornley.database.model.Character
import com.example.hornley.databinding.ActivityGameBinding

class Game : AppCompatActivity() {

    private lateinit var binding: ActivityGameBinding
    private lateinit var character: Character

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        character = intent.getParcelableExtra<Character>("character") as Character
        this.title = character.name

        binding = ActivityGameBinding.inflate(layoutInflater)
        setContentView(binding.root)
        val navHostFragment = supportFragmentManager.findFragmentById(R.id.gameFragment) as NavHostFragment
        val navController = navHostFragment.navController
        val bundle = Bundle()
        bundle.putInt("x", character.id)
        navController.setGraph(R.navigation.game_menu_nav, bundle)
//        setupActionBarWithNavController(navController)

//        val charID: Int = character.id
//        val fragment = GameFragment()
//        fragment.arguments = bundle
//        supportFragmentManager.beginTransaction().add(R.id.gameFragment, fragment).commit()

        /*
        LAYOUT
        > Change the layout design to a menu
        > Character
        > Shop
        > Battle
        */

    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.gameFragment)
        return navController.navigateUp() || super.onSupportNavigateUp()
    }

}