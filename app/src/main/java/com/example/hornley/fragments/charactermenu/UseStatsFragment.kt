package com.example.hornley.fragments.charactermenu

import android.app.AlertDialog
import android.content.DialogInterface
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.example.hornley.database.model.Character
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.databinding.FragmentUseStatsBinding

class UseStatsFragment : Fragment() {

    private val args by navArgs<UseStatsFragmentArgs>()
    private var _binding: FragmentUseStatsBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel
    private var points = 0
    private var maxpoints = 0

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        _binding = FragmentUseStatsBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        val character = args.character
        activity?.setTitle(character.name)
        points = character.statPoint
        maxpoints = character.statPoint

        buttonBindings(character)
        return binding.root
    }

    fun buttonBindings(character: Character) {
        binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
        binding.textVit.text = "VIT: ${character.stats[0]}"
        binding.textStr.text = "STR: ${character.stats[1]}"
        binding.textInt.text = "INT: ${character.stats[2]}"
        binding.textAgi.text = "AGI: ${character.stats[3]}"
        binding.textDex.text = "DEX: ${character.stats[4]}"
        binding.textWis.text = "WIS: ${character.stats[5]}"
        binding.textLuck.text = "LUCK: ${character.stats[6]}"
        binding.addVit.setOnClickListener {
            if (points >= 1) {
                character.stats[0]++
                binding.textVit.text = "VIT: ${character.stats[0]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.addStr.setOnClickListener {
            if (points >= 1) {
                character.stats[1]++
                binding.textStr.text = "STR: ${character.stats[1]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.addInt.setOnClickListener {
            if (points >= 1) {
                character.stats[2]++
                binding.textInt.text = "INT: ${character.stats[2]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.addAgi.setOnClickListener {
            if (points >= 1) {
                character.stats[3]++
                binding.textAgi.text = "AGI: ${character.stats[3]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.addDex.setOnClickListener {
            if (points >= 1) {
                character.stats[4]++
                binding.textDex.text = "DEX: ${character.stats[4]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.addWis.setOnClickListener {
            if (points >= 1) {
                character.stats[5]++
                binding.textWis.text = "WIS: ${character.stats[5]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.addLuck.setOnClickListener {
            if (points >= 1) {
                character.stats[6]++
                binding.textLuck.text = "LUCK: ${character.stats[6]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points--
            } else {
                pointsExceed()
            }
        }
        binding.minusVit.setOnClickListener {
            if (points < maxpoints) {
                character.stats[0]--
                binding.textVit.text = "VIT: ${character.stats[0]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.minusStr.setOnClickListener {
            if (points < maxpoints) {
                character.stats[1]--
                binding.textStr.text = "STR: ${character.stats[1]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.minusInt.setOnClickListener {
            if (points < maxpoints) {
                character.stats[2]--
                binding.textInt.text = "INT: ${character.stats[2]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.minusAgi.setOnClickListener {
            if (points < maxpoints) {
                character.stats[3]--
                binding.textAgi.text = "AGI: ${character.stats[3]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.minusDex.setOnClickListener {
            if (points < maxpoints) {
                character.stats[4]--
                binding.textDex.text = "DEX: ${character.stats[4]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.minusWis.setOnClickListener {
            if (points < maxpoints) {
                character.stats[5]--
                binding.textWis.text = "WIS: ${character.stats[5]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.minusLuck.setOnClickListener {
            if (points < maxpoints) {
                character.stats[6]--
                binding.textLuck.text = "LUCK: ${character.stats[6]}"
                binding.availableStatPoints.text = "Available Stat Points: ${points}/${maxpoints}"
                points++
            } else {
                maxpoints(maxpoints)
            }
        }
        binding.applyChangesButton.setOnClickListener {
            mUserViewModel.updateCharacter(character)
            val action = UseStatsFragmentDirections.actionUseStatsFragmentToCharacterMenuFragment(character)
            findNavController().navigate(action)
        }
    }

    fun pointsExceed() {
        val alert = AlertDialog.Builder(binding.root.context)
        alert.setMessage("You are out of stat points!")
        alert.setTitle("Invalid")
        alert.setCancelable(true)
        alert.setPositiveButton("Okay",
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                dialog?.cancel()
            })
        val dialog = alert.create()
        dialog.show()
    }

    fun maxpoints(max: Int) {

        val alert = AlertDialog.Builder(binding.root.context)
        alert.setMessage("You can only have $max amount of stat points!")
        alert.setTitle("Invalid")
        alert.setCancelable(true)
        alert.setPositiveButton("Okay",
            DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                dialog?.cancel()
            })
        val dialog = alert.create()
        dialog.show()
    }

}