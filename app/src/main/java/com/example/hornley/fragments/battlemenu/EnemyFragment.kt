package com.example.hornley.fragments.battlemenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hornley.R
import com.example.hornley.database.model.Character
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.EnemyViewModel
import com.example.hornley.databinding.FragmentEnemyBinding
import com.example.hornley.fragments.list.ListAdapter

class EnemyFragment : Fragment() {

    private val args by navArgs<EnemyFragmentArgs>()
    private var _binding: FragmentEnemyBinding? = null
    private val binding get() = _binding!!
    private lateinit var eUserViewModel: EnemyViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentEnemyBinding.inflate(inflater, container, false)
        eUserViewModel = ViewModelProvider(this).get(EnemyViewModel::class.java)
        val character: Character = args.character
        activity?.title = "Enemy Menu"

        val adapter = EnemyAdapter(eUserViewModel, character)
        val recyclerView = binding.enemylist
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        eUserViewModel.enemyReadAllData.observe(viewLifecycleOwner, Observer { enemy ->
            adapter.setData(enemy)
        })

        return binding.root
    }

}