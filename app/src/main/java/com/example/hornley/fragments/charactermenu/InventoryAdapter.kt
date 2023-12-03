package com.example.hornley.fragments.charactermenu

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.navigation.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.example.hornley.R
import com.example.hornley.database.model.Character
import com.example.hornley.database.model.Item

class InventoryAdapter(private val character: Character): RecyclerView.Adapter<InventoryAdapter.MyViewHolder>() {

    private var inventory = listOf<Item>()
    private var invAmount = mutableMapOf<Item, Int>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InventoryAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.inventory_list_row, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = inventory[position]
        holder.itemView.findViewById<TextView>(R.id.txvItemName).text = item.name
        for (items in invAmount.keys.iterator()) {
            if (items.name == item.name)
            holder.itemView.findViewById<TextView>(R.id.txvItemAmount).text = invAmount[items].toString()
        }

        holder.itemView.findViewById<ConstraintLayout>(R.id.inventoryListRow).setOnClickListener {
            val action = InventoryFragmentDirections.actionInventoryFragmentToItemInformationFragment(item, character)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return inventory.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<Item>) {
        val inv: MutableList<Item> = mutableListOf()
        val inven: MutableMap<Item, Int> = mutableMapOf()
        for (item in items) {
            for (i in character.inventory.keys.iterator()) {
                if (item.id == i) {
                    inv.add(item)
                    inven[item] = character.inventory.getValue(i)
                }
            }
        }
        this.inventory = inv
        this.invAmount = inven
        notifyDataSetChanged()
    }
}