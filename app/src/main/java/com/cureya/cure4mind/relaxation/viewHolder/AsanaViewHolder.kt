package com.cureya.cure4mind.relaxation.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.AsanaCardBinding

/**
 * @author Udhaya
 * Created on 06-06-2022
 */

class AsanaViewHolder(
    private val asanaCardBinding: AsanaCardBinding,
    private val onClickListener: (String) -> Unit,
) : RecyclerView.ViewHolder(asanaCardBinding.root) {
    fun bind(asana: String) {
        asanaCardBinding.asanaText.text = asana
        asanaCardBinding.root.setOnClickListener {
            onClickListener(asana)
        }
    }
}