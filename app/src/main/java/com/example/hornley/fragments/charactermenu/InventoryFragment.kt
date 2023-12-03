package com.example.hornley.fragments.charactermenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hornley.database.model.Character
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.ItemViewModel
import com.example.hornley.databinding.FragmentInventoryBinding
import com.example.hornley.fragments.list.ListAdapter

class InventoryFragment : Fragment() {

    private val args by navArgs<InventoryFragmentArgs>()
    private var _binding: FragmentInventoryBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel
    private lateinit var iUserViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentInventoryBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        iUserViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        val character = args.character
        (activity as? AppCompatActivity)?.supportActionBar?.title = "${character.name}'s Inventory"

        val adapter = InventoryAdapter(character)
        val recyclerView = binding.inventoryList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext())

        iUserViewModel.itemReadAllData.observe(viewLifecycleOwner, Observer {item ->
            adapter.setData(item)
        })

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}