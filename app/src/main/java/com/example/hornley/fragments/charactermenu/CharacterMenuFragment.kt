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
import com.example.hornley.databinding.FragmentCharacterMenuBinding

class CharacterMenuFragment : Fragment() {

    private val args by navArgs<CharacterMenuFragmentArgs>()
    private var _binding: FragmentCharacterMenuBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentCharacterMenuBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        activity?.setTitle("Character Menu")

        val character = args.character

        setDisplay(character)

        binding.useStatsButton.setOnClickListener {
            character.statPoint = 500
            if (character.statPoint >= 1) {
                val action = CharacterMenuFragmentDirections.actionCharacterMenuFragmentToUseStatsFragment(character)
                findNavController().navigate(action)
            } else {
                val alert = AlertDialog.Builder(binding.useStatsButton.context)
                alert.setMessage("You have no stat points available to use!")
                alert.setTitle("Unavailable stat points")
                alert.setCancelable(true)
                alert.setPositiveButton("Okay",
                    DialogInterface.OnClickListener { dialog: DialogInterface?, which: Int ->
                        dialog?.cancel()
                    })
                val dialog = alert.create()
                dialog.show()

            }
        }

        binding.backbutton.setOnClickListener {
            val action = CharacterMenuFragmentDirections.actionCharacterMenuFragmentToGameFragment2(character.id)
            findNavController().navigate(action)
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

    fun setDisplay(character: Character) {
        val name = character.name
        var statsText = ""
        val statsDisplay = binding.txvStatsList
        val statsName = binding.txvStatsName
        var num = 0
        var curExp = 0.0
        var expReq = 0.0
        while (num < character.stats.size) {
            if (num >= 0 && num <= 9 || num >= 16 && num <= 17){
                statsText += "${character.stats.get(num)}\n"
            }
            if (num == 18) { curExp = character.stats.get(num) }
            if (num == 19) { expReq = character.stats.get(num) }
            num++
        }
        val title = "$name (${character.characterClass})\nLevel ${character.level} - xp: $curExp/$expReq"
        statsName.text = "Vitality (VIT):\nStrength (STR):\nIntelligence (INT):\nAgility (AGI):\nDexterity" +
                " (DEX):\nWisdom (WIS):\nLuck:\nCharisma:\nPhysical Defense:\nMagical Defense:\nConstitution:\nBalance:\n"
        statsDisplay.text = statsText
        binding.charDisplay.text = title
    }

}