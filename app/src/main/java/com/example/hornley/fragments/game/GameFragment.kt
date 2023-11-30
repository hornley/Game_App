package com.example.hornley.fragments.game

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.databinding.FragmentGameBinding

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        val ID = arguments?.getInt("x")
        mUserViewModel.charReadAllData.observe(viewLifecycleOwner, Observer { characters ->
            for (character in characters) {
                if (character.id == ID) {
                    binding.characterMenuButton.setOnClickListener {
                        val action = GameFragmentDirections.actionGameFragment2ToCharacterMenuFragment(character)
                        findNavController().navigate(action)
                    }

                    binding.shopMenuButton.setOnClickListener {
                        val action = GameFragmentDirections.actionGameFragment2ToShopMenuFragment(character)
                        findNavController().navigate(action)
                    }

                    binding.battleMenuButton.setOnClickListener {
                        val action = GameFragmentDirections.actionGameFragment2ToBattleMenuFragment(character)
                        findNavController().navigate(action)
                    }
                }
            }
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}