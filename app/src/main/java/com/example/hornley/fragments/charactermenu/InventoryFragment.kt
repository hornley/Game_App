package com.example.hornley.fragments.charactermenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.databinding.FragmentInventoryBinding

class InventoryFragment : Fragment() {

    private val args by navArgs<InventoryFragmentArgs>()
    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        val character = args.character
        activity?.setTitle(character.name)

        binding.backtoMenu.setOnClickListener {
            val action = InventoryFragmentDirections.actionInventoryFragmentToCharacterMenuFragment(character)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}