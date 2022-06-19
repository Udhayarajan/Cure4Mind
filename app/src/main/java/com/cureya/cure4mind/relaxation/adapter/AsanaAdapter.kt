package com.cureya.cure4mind.relaxation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.AsanaCardBinding
import com.cureya.cure4mind.relaxation.viewHolder.AsanaViewHolder

/**
 * @author Udhaya
 * Created on 06-06-2022
 */

class AsanaAdapter(
    private val types: List<String>,
    private val onClickListener: (String) -> Unit,
) : RecyclerView.Adapter<AsanaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsanaViewHolder {
        return AsanaViewHolder(
            AsanaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickListener
        )
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: AsanaViewHolder, position: Int) {
        holder.bind(types[position])
    }
}