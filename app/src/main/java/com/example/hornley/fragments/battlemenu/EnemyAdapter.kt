package com.example.hornley.fragments.battlemenu

import android.annotation.SuppressLint
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hornley.R
import com.example.hornley.database.model.Character
import com.example.hornley.database.model.Enemy
import com.example.hornley.database.viewmodel.EnemyViewModel

class EnemyAdapter(private val eUserViewModel: EnemyViewModel, private val character: Character): RecyclerView.Adapter<EnemyAdapter.MyViewHolder>() {

    private var enemyList = emptyList<Enemy>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): EnemyAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.enemy_list_row, parent, false)
        return EnemyAdapter.MyViewHolder(view)
    }

    override fun getItemCount(): Int {
        return enemyList.size
    }

    override fun onBindViewHolder(holder: EnemyAdapter.MyViewHolder, position: Int) {
        val enemy = enemyList[position]

        holder.itemView.findViewById<TextView>(R.id.txvFightEnemyName).text = enemy.name

        holder.itemView.findViewById<ConstraintLayout>(R.id.enemy_row_layout).setOnClickListener {
            val action = EnemyFragmentDirections.actionEnemyFragmentToBattleMenuFragment(character, enemy)
            holder.itemView.findNavController().navigate(action)
        }
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(enemy: List<Enemy>) {
        this.enemyList = enemy
        notifyDataSetChanged()
    }

}