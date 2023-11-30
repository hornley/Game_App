package com.example.hornley.fragments.list

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
import com.example.hornley.database.viewmodel.CharacterViewModel
import com.google.android.material.floatingactionbutton.FloatingActionButton

class ListAdapter(private var mUserViewModel: CharacterViewModel): RecyclerView.Adapter<ListAdapter.MyViewHolder>() {

    private var userList = emptyList<Character>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.custom_row, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val character = userList[position]
        holder.itemView.findViewById<TextView>(R.id.nameTextView).text = character.name
        holder.itemView.findViewById<TextView>(R.id.txvClass).text = "(${character.characterClass} - Level: ${character.level})"

        holder.itemView.findViewById<ConstraintLayout>(R.id.rowLayout).setOnClickListener {
            val action = ListFragmentDirections.actionListFragmentToCharacterConfirmation(character)
            holder.itemView.findNavController().navigate(action)
        }

        holder.itemView.findViewById<FloatingActionButton>(R.id.deleteCharbtn).setOnClickListener {
            mUserViewModel.deleteCharacter(character)
        }

    }

    override fun getItemCount(): Int {
        return userList.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(character: List<Character>) {
        this.userList = character
        notifyDataSetChanged()
    }

}