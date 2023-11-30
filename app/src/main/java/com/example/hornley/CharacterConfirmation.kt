package com.example.hornley

import android.annotation.SuppressLint
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import androidx.navigation.navArgs
import com.example.hornley.fragments.game.GameFragment

class CharacterConfirmation : AppCompatActivity() {

    private val args by navArgs<CharacterConfirmationArgs>()

    @SuppressLint("SetTextI18n")
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_character_confirmation)
        val name = args.currentCharacter.name
        setTitle("Character Confirmation")
        val stats: ArrayList<Double> = args.currentCharacter.stats
        var statsText = ""
        val statsDisplay = findViewById<TextView>(R.id.txvStatsList)
        val statsName = findViewById<TextView>(R.id.txvStatsName)
        val confirmButton = findViewById<Button>(R.id.btnConfirm)
        var num = 0
        var curExp = 0.0
        var expReq = 0.0
        while (num < stats.size) {
            if (num >= 0 && num <= 9 || num >= 16 && num <= 17){
                statsText += "${stats.get(num)}\n"
            }
            if (num == 18) { curExp = stats.get(num) }
            if (num == 19) { expReq = stats.get(num) }
            num++
        }
        val title = "$name (${args.currentCharacter.characterClass})\nLevel ${args.currentCharacter.level} - xp: $curExp/$expReq"
        statsName.text = "Vitality (VIT):\nStrength (STR):\nIntelligence (INT):\nAgility (AGI):\nDexterity" +
                " (DEX):\nWisdom (WIS):\nLuck:\nCharisma:\nPhysical Defense:\nMagical Defense:\nConstitution:\nBalance:\n"
        statsDisplay.text = statsText
        findViewById<TextView>(R.id.charDisplay).text = title
        confirmButton.setOnClickListener {
            val intent = Intent(this, Game::class.java)
            intent.putExtra("character", args.currentCharacter)
            startActivity(intent)
        }
    }
}