package com.example.hornley.fragments.battlemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hornley.database.model.Character
import com.example.hornley.databinding.FragmentBattleMenuBinding

class BattleMenuFragment : Fragment() {

    private val args by navArgs<BattleMenuFragmentArgs>()
    private var _binding: FragmentBattleMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentBattleMenuBinding.inflate(inflater, container, false)
        val character: Character = args.character
        activity?.title = "Battle Menu"

        binding.battleMenuBackButton.setOnClickListener {
            val action = BattleMenuFragmentDirections.actionBattleMenuFragmentToGameFragment2(character.id)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}