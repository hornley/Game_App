package com.example.hornley.fragments.shopmenu

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
import com.example.hornley.database.model.Item

class ShopMenuAdapter(private val character: Character): RecyclerView.Adapter<ShopMenuAdapter.MyViewHolder>() {

    private var items = listOf<Item>()

    class MyViewHolder(itemView: View): RecyclerView.ViewHolder(itemView) {
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ShopMenuAdapter.MyViewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.shop_list_row, parent, false)
        return MyViewHolder(view)
    }

    @SuppressLint("SetTextI18n", "SuspiciousIndentation")
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        val item = items[position]
        holder.itemView.findViewById<TextView>(R.id.txvShopItemName).text = item.name
        holder.itemView.findViewById<TextView>(R.id.txvShopItemCost).text = "Cost: ${item.cost}"

        holder.itemView.findViewById<ConstraintLayout>(R.id.shopListRow).setOnClickListener {
            val action = ShopMenuFragmentDirections.actionShopMenuFragmentToItemBuyFragment(item, character)
            holder.itemView.findNavController().navigate(action)
        }

    }

    override fun getItemCount(): Int {
        return items.size
    }

    @SuppressLint("NotifyDataSetChanged")
    fun setData(items: List<Item>) {
        this.items = items
        notifyDataSetChanged()
    }

}