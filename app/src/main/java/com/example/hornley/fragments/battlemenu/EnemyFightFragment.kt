package com.example.hornley.fragments.battlemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hornley.database.model.Character
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.EnemyViewModel
import com.example.hornley.databinding.FragmentEnemyFightBinding

class EnemyFightFragment : Fragment() {

    private val args by navArgs<EnemyFightFragmentArgs>()
    private var _binding: FragmentEnemyFightBinding? = null
    private val binding get() = _binding!!
    private lateinit var pUserViewModel: CharacterViewModel
    private lateinit var eUserViewModel: EnemyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEnemyFightBinding.inflate(inflater, container, false)
        pUserViewModel = ViewModelProvider(this)[CharacterViewModel::class.java]
        eUserViewModel = ViewModelProvider(this)[EnemyViewModel::class.java]
        val character = args.character
        val enemy = args.enemy

        views(character, enemy)

        battle(character, enemy)

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    private fun views(character: Character, enemy: Enemy) {
        // Enemy Set Views
        binding.txvFightEnemyName.text = enemy.name
        binding.enemyHP.max = enemy.vitality.toInt()
        binding.enemyHP.progress = enemy.vitality.toInt()

        // Player Set Views
        binding.txvFightPlayerName.text = character.name
        binding.playerHP.max = character.stats[0].toInt()
        binding.playerHP.progress = character.stats[0].toInt()
        binding.txvPlayerBalance.text = "Balance: ${character.stats[17]}"
    }

    private fun update(character: Character, enemy: Enemy) {
        // Enemy Update Views
        binding.enemyHP.progress = enemy.vitality.toInt()

        // Player Update Views
        binding.playerHP.progress = character.stats[0].toInt()
        binding.txvPlayerBalance.text = "Balance: ${character.stats[17]}"
    }

    private fun battle(character: Character, enemy: Enemy) {
        var playerTurn: Boolean = false
        var enemyTurn: Boolean = false
        // vit, str, int, agi, dex, wis, luck, charisma, pDef, mDef, pPen, mPen, crtC, crtD, expMulti, moneyMulti, cons, bal, exp, expr, hunger, thirst
        var currentPlayerHP = character.stats[0]
        var currentEnemyHP = enemy.vitality
        if (character.stats[3] > enemy.agility) {
            playerTurn = true
        } else {
            enemyTurn = true
        }

    }

}