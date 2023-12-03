package com.example.hornley.fragments.battlemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import com.example.hornley.databinding.FragmentFightBinding
import com.example.hornley.battle
import com.example.hornley.database.viewmodel.CharacterViewModel

class FightFragment : Fragment() {

    private val args by navArgs<FightFragmentArgs>()
    private var _binding: FragmentFightBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentFightBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        val character = args.character

        battle(character, mUserViewModel)

        return binding.root
    }

}