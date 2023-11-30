package com.example.hornley.fragments.shopmenu

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.example.hornley.database.model.Character
import com.example.hornley.databinding.FragmentShopMenuBinding

class ShopMenuFragment : Fragment() {

    private val args by navArgs<ShopMenuFragmentArgs>()
    private var _binding: FragmentShopMenuBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentShopMenuBinding.inflate(inflater, container, false)
        val character: Character = args.character

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}