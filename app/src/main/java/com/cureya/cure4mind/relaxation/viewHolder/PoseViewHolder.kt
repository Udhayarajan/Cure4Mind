package com.cureya.cure4mind.relaxation.viewHolder

import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.RecommendedYogaPoseDialogItemBinding

/**
 * @author Udhaya
 * Created on 06-06-2022
 */

class PoseViewHolder(
    private val binding: RecommendedYogaPoseDialogItemBinding,
    private val onClick: (String) -> Unit,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(pose: String) {
        binding.poseName.text = pose
        binding.root.setOnClickListener {
            onClick(pose)
        }
    }
}
