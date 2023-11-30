package com.example.hornley.fragments.add

import android.os.Bundle
import android.text.TextUtils
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.AdapterView
import android.widget.AdapterView.OnItemSelectedListener
import android.widget.ArrayAdapter
import android.widget.Spinner
import androidx.lifecycle.ViewModelProvider
import com.example.hornley.R
import androidx.navigation.fragment.findNavController
import com.example.hornley.database.model.Character
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.databinding.FragmentAddBinding

class AddFragment : Fragment() {

    private var _binding: FragmentAddBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel
    var item: String = ""

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentAddBinding.inflate(inflater, container, false)

        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)

        ArrayAdapter.createFromResource(
            binding.classChoices.context,
            R.array.classes,
            android.R.layout.simple_spinner_item
        ).also {adapter ->
            adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
            binding.classChoices.adapter = adapter
        }

        val spinner: Spinner = binding.classChoices
        spinner.onItemSelectedListener = object : OnItemSelectedListener {
            override fun onItemSelected(
                arg0: AdapterView<*>?,
                arg1: View?,
                arg2: Int,
                arg3: Long
            ) {
                // Do what you want
                item = spinner.selectedItem.toString()
            }
            override fun onNothingSelected(arg0: AdapterView<*>?) {}
        }

        binding.add.setOnClickListener {
            val character: Character? = newCharacter()
            if (character != null) {
                mUserViewModel.addCharacter(character)
                findNavController().navigate(R.id.action_addFragment_to_listFragment)
            }
        }

        return binding.root
    }

    private fun newCharacter(): Character? {
        val name = binding.addName.text.toString()
        if (inputCheck(name)) {
            var stats: ArrayList<Double> = arrayListOf()
            var skills: ArrayList<Int> = arrayListOf()
            if (item == "Warrior") {
                stats = arrayListOf(125.0, 3.0, 0.0, 20.0, 1.0, 0.0, 0.0, 0.0, 3.0, 0.0, 0.0, 0.0, 0.0, 150.0, 0.0, 0.0, 1.0, 1000.0, 0.0, 15.0, 0.0, 0.0)
            } else if (item == "Archer") {
                stats = arrayListOf(75.0, 2.0, 0.0, 35.0, 2.0, 1.0, 0.0, 0.0, 1.0, 0.0, 0.0, 0.0, 5.0, 150.0, 0.0, 0.0, 1.0, 1000.0, 0.0, 15.0, 0.0, 0.0)
            } else if (item == "Assassin") {
                stats = arrayListOf(100.0, 1.0, 0.0, 25.0, 3.0, 1.0, 0.0, 0.0, 1.0, 1.0, 2.0, 0.0, 10.0, 150.0, 0.0, 0.0, 1.0, 1000.0, 0.0, 15.0, 0.0, 0.0)
            } else if (item == "Mage") {
                stats = arrayListOf(90.0, 1.0, 3.0, 25.0, 0.0, 2.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 0.0, 150.0, 0.0, 0.0, 1.0, 1000.0, 0.0, 15.0, 0.0, 0.0)
            }
            val eResistance: ArrayList<Double> = arrayListOf(0.0, 0.0, 0.0, 0.0, 0.0, 0.0)
            val inventory: MutableMap<Int, Int> = mutableMapOf()
            val reputation: MutableMap<Int, Double> = mutableMapOf()
            val characterClass: String = item
            return Character(0, name, characterClass, "", 1,
                0, 20, 0, 0, 0, 0, 0,
                skills, stats, eResistance, inventory, reputation)
        }
        return null
    }

    private fun inputCheck(name: String): Boolean {
        return !(TextUtils.isEmpty(name))
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}