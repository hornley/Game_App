package com.example.hornley.fragments.shopmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hornley.R
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.databinding.FragmentInventoryBinding
import com.example.hornley.databinding.FragmentItemBuyBinding
import com.example.hornley.fragments.charactermenu.InventoryFragmentArgs

class ItemBuyFragment : Fragment() {

    private val args by navArgs<ItemBuyFragmentArgs>()
    private var _binding: FragmentItemBuyBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentItemBuyBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        val item = args.item
        val character = args.character
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Buy Menu"

        binding.txvItemBuyName.text = item.name
        binding.txvItemBuyCost.text = item.cost.toString()
        binding.ItemBackButton.setOnClickListener {
            val action = ItemBuyFragmentDirections.actionItemBuyFragmentToShopMenuFragment(character)
            findNavController().navigate(action)
        }

        binding.ShopBuyButton.setOnClickListener {
            if (character.stats[17] >= item.cost) {
                var m = 1
                try {
                    if (character.inventory.getValue(item.id) >= 1) { m = character.inventory.getValue(item.id) + 1 }
                } catch (_: Exception) { }
                character.inventory[item.id] = m
                character.stats[17] -= item.cost
                mUserViewModel.updateCharacter(character)
                Toast.makeText(requireContext(), "${item.name} successfully bought", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(requireContext(), "Insufficient Balance", Toast.LENGTH_SHORT).show()
            }
        }

        return binding.root
    }

}