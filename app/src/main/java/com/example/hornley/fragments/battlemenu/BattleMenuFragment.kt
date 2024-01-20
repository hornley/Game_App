package com.example.hornley.fragments.battlemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Lifecycle
import androidx.lifecycle.LifecycleObserver
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hornley.database.model.Character
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.EnemyViewModel
import com.example.hornley.databinding.FragmentBattleMenuBinding

class BattleMenuFragment : Fragment() {

    private val args by navArgs<BattleMenuFragmentArgs>()
    private var _binding: FragmentBattleMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var eUserViewModel: EnemyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBattleMenuBinding.inflate(inflater, container, false)
        eUserViewModel = ViewModelProvider(this)[EnemyViewModel::class.java]
        val character: Character = args.character
        if (args.enemy != null) {
            val enemy = args.enemy
            binding.txvBattleMenuEnemyName.text = args.enemy?.name
            binding.fightBattleButton.isEnabled = true
            binding.fightBattleButton.setOnClickListener {
                eUserViewModel.enemyReadAllData.observe(viewLifecycleOwner, Observer { enemies ->
                    for (tEnemy in enemies) {
                        if (enemy == tEnemy) {
                            val action = BattleMenuFragmentDirections.actionBattleMenuFragmentToEnemyFightFragment(character, tEnemy)
                            findNavController().navigate(action)
                        }
                    }
                })
            }

        } else {
            binding.fightBattleButton.isEnabled = false
        }

        binding.chooseEnemyFightButton.setOnClickListener {
            val action = BattleMenuFragmentDirections.actionBattleMenuFragmentToEnemyFragment(character)
            findNavController().navigate(action)
        }


        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}