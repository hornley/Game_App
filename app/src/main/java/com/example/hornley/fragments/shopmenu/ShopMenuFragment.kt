package com.example.hornley.fragments.shopmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.view.menu.MenuView.ItemView
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.hornley.database.model.Character
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.ItemViewModel
import com.example.hornley.databinding.FragmentShopMenuBinding
import com.example.hornley.fragments.charactermenu.InventoryAdapter

class ShopMenuFragment : Fragment() {

    private val args by navArgs<ShopMenuFragmentArgs>()
    private var _binding: FragmentShopMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var iUserViewModel: ItemViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShopMenuBinding.inflate(inflater, container, false)
        val character: Character = args.character
        iUserViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Balance: ${character.stats[17]}"

        val adapter = ShopMenuAdapter(character)
        val recyclerView = binding.shopItemList
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(requireContext(), LinearLayoutManager.VERTICAL, false)

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