package com.example.randomfacts.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.randomfacts.databinding.ItemFactBinding

class HistoryAdapter(
    private val facts: MutableList<String>,
    private val onDelete: ((String) -> Unit)? = null
) : RecyclerView.Adapter<HistoryAdapter.FactViewHolder>() {

    class FactViewHolder(val binding: ItemFactBinding) : RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FactViewHolder {
        val binding = ItemFactBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FactViewHolder(binding)
    }

    override fun onBindViewHolder(holder: FactViewHolder, position: Int) {
        val fact = facts[position]
        holder.binding.factTextView.text = fact

        if (onDelete != null) {
            holder.binding.buttonDelete.visibility = View.VISIBLE
            holder.binding.buttonDelete.setOnClickListener {
                onDelete.invoke(fact)
            }
        } else {
            holder.binding.buttonDelete.visibility = View.GONE
        }

    }

    override fun getItemCount(): Int = facts.size
}
