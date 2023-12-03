package com.example.hornley.fragments.game

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.fragment.findNavController
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.model.Item
import com.example.hornley.database.model.Skill
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.example.hornley.database.viewmodel.EnemyViewModel
import com.example.hornley.database.viewmodel.ItemViewModel
import com.example.hornley.database.viewmodel.SkillViewModel
import com.example.hornley.databinding.FragmentGameBinding
import org.json.JSONObject

class GameFragment : Fragment() {

    private var _binding: FragmentGameBinding? = null
    private val binding get() = _binding!!
    private lateinit var mUserViewModel: CharacterViewModel
    private lateinit var eUserViewModel: EnemyViewModel
    private lateinit var iUserViewModel: ItemViewModel
    private lateinit var sUserViewModel: SkillViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        // Inflate the layout for this fragment
        _binding = FragmentGameBinding.inflate(inflater, container, false)
        mUserViewModel = ViewModelProvider(this).get(CharacterViewModel::class.java)
        eUserViewModel = ViewModelProvider(this).get(EnemyViewModel::class.java)
        iUserViewModel = ViewModelProvider(this).get(ItemViewModel::class.java)
        sUserViewModel = ViewModelProvider(this).get(SkillViewModel::class.java)

        val ID = arguments?.getInt("x")
        mUserViewModel.charReadAllData.observe(viewLifecycleOwner, Observer { characters ->
            for (character in characters) {
                if (character.id == ID) {
                    binding.characterMenuButton.setOnClickListener {
                        val action = GameFragmentDirections.actionGameFragment2ToCharacterMenuFragment(character)
                        findNavController().navigate(action)
                    }

                    binding.shopMenuButton.setOnClickListener {
                        val action = GameFragmentDirections.actionGameFragment2ToShopMenuFragment(character)
                        findNavController().navigate(action)
                    }

                    binding.battleMenuButton.setOnClickListener {
                        val action = GameFragmentDirections.actionGameFragment2ToBattleMenuFragment(character)
                        findNavController().navigate(action)
                    }
                }
            }
        })

        binding.addData.setOnClickListener {
            addData()
        }

        return binding.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }


    fun addData() {
        val applicationContext = binding.root.context
        val list = arrayListOf<String>("items", "skills", "enemies")
        var num = 0
        while (num < list.size) {
            val jsonData = applicationContext.resources.openRawResource(
                applicationContext.resources.getIdentifier(
                    list[num],
                    "raw",
                    applicationContext.packageName
                )
            ).bufferedReader().use{it.readText()}

            if (num == 0) {
                val ItemsDATA = JSONObject(jsonData)
                addItems(ItemsDATA)
            } else if (num == 1) {
                val SkillsDATA = JSONObject(jsonData)
                addSkills(SkillsDATA)
            } else {
                val EnemiesDATA = JSONObject(jsonData)
                addEnemies(EnemiesDATA)
            }
            num++
        }

    }

    fun addSkills(SkillsDATA: JSONObject) {
        for (Class in SkillsDATA.keys().iterator()) {
            for (skills in SkillsDATA.getJSONObject(Class).keys().iterator()) {
                val skillsData = SkillsDATA.getJSONObject(Class).getJSONArray(skills)

                val name = skillsData.getString(0)
                val lvlReq = skillsData.getJSONArray(1).getJSONArray(0).getInt(0)
                val fixedMana = skillsData.getJSONArray(1).getJSONArray(0).getDouble(1)
                val percentageMana = skillsData.getJSONArray(1).getJSONArray(0).getDouble(2)
                val type = skillsData.getJSONArray(1).getJSONArray(0).getInt(3)
                val sRatio = skillsData.getJSONArray(1).getJSONArray(1)
                var num = 0

                val statsratio: MutableMap<Int, Double> = mutableMapOf()
                while (num < sRatio.length()) {
                    statsratio[sRatio.getInt(num)] = sRatio.getDouble(num + 1)
                    num += 2
                }
                val skill = Skill(0, name, Class, lvlReq, fixedMana, percentageMana, type, statsratio)
                sUserViewModel.addSkill(skill)
            }
        }
    }

    fun addItems(ItemsDATA: JSONObject) {
        for (category in ItemsDATA.keys().iterator()) {
            for (subCategory in ItemsDATA.getJSONObject(category).keys().iterator()) {
                for (item in ItemsDATA.getJSONObject(category).getJSONObject(subCategory).keys().iterator()) {
                    val itemData = ItemsDATA.getJSONObject(category).getJSONObject(subCategory).getJSONArray(item)
                    val itemNumData = itemData.getJSONArray(1)

                    val rarity: String = itemData.getJSONArray(0).getString(0)
                    val name: String = itemData.getJSONArray(0).getString(1)
                    val cost: Double = itemNumData.getJSONArray(0).getDouble(0)
                    val lvlReq: Int = itemNumData.getJSONArray(0).getInt(1)
                    val type: Int = itemNumData.getJSONArray(0).getInt(2)
                    val effs = itemNumData.getJSONArray(1)
                    var num = 0

                    val effects: MutableMap<Int, Double> = mutableMapOf()
                    while (num < effs.length()) {
                        val x = effs.getInt(num)
                        val y = effs.getDouble(num + 1)
                        effects[x] = y
                        Log.d("TAG_DATA", "$effects")
                        num += 2
                    }

                    val item = Item(0, rarity, name, category, subCategory, cost, lvlReq, type, effects)
                    iUserViewModel.addItem(item)
                }
            }
        }
    }

    fun addEnemies(EnemiesDATA: JSONObject) {
        for (i in EnemiesDATA.keys().iterator()) {
            for (j in EnemiesDATA.getJSONObject(i).keys().iterator())
            {
                val skills: ArrayList<Double> = ArrayList()
                val weaknesses: ArrayList<Double> = ArrayList()
                var num = 0

                val DataArray = EnemiesDATA.getJSONObject(i).getJSONArray(j)
                val SkillsArray = DataArray.getJSONArray(1).getJSONArray(1)
                val WeaknessesArray = DataArray.getJSONArray(1).getJSONArray(2)

                val StatArray = DataArray.getJSONArray(1).getJSONArray(0)
                val id: Int = j.toInt()
                val name: String = DataArray.getString(0)
                val vitality: Double = StatArray.getDouble(0)
                val strength: Double = StatArray.getDouble(1)
                val intelligence: Double = StatArray.getDouble(2)
                val agility: Double = StatArray.getDouble(3)
                val dexterity: Double = StatArray.getDouble(4)
                val wisdom: Double = StatArray.getDouble(5)
                val pDef: Double = StatArray.getDouble(6)
                val mDef: Double = StatArray.getDouble(7)
                val phyPenetration: Double = StatArray.getDouble(8)
                val magPenetration: Double = StatArray.getDouble(9)
                val criticalChance: Double = StatArray.getDouble(10)
                val criticalDamage: Double = StatArray.getDouble(11)
                val expGain: Double = StatArray.getDouble(12)
                val rewards: Double = StatArray.getDouble(13)
                val type: Double = StatArray.getDouble(14)
                while (num < SkillsArray.length()) {
                    skills.add(SkillsArray.getDouble(num))
                    num++
                }
                num = 0
                while (num < WeaknessesArray.length()) {
                    weaknesses.add(WeaknessesArray.getDouble(num))
                    num++
                }
//                Log.d("TAG_DATA", ""+ DataArray.getJSONArray(1).getJSONArray(1).length())

                val enemy = Enemy(id, name, vitality, strength, intelligence, agility, dexterity, wisdom, pDef, mDef, phyPenetration, magPenetration, criticalChance, criticalDamage, expGain, rewards, type, skills, weaknesses)
                eUserViewModel.addEnemy(enemy)
            }
        }
    }


}