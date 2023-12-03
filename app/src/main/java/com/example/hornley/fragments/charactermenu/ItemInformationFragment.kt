package com.example.hornley.fragments.charactermenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.navigation.fragment.navArgs
import com.example.hornley.R
import com.example.hornley.database.model.Item
import com.example.hornley.databinding.FragmentInventoryBinding
import com.example.hornley.databinding.FragmentItemInformationBinding

class ItemInformationFragment : Fragment() {

    private val args by navArgs<ItemInformationFragmentArgs>()
    private var _binding: FragmentItemInformationBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentItemInformationBinding.inflate(inflater, container, false)
        val item: Item = args.item
        (activity as? AppCompatActivity)?.supportActionBar?.title = "Item Information"

        setDisplay(item)

        return binding.root
    }

    fun setDisplay(item: Item) {
        binding.txvIFName.text = item.name
    }

}