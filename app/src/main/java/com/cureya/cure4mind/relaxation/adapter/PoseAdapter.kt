package com.cureya.cure4mind.relaxation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.RecommendedYogaPoseDialogItemBinding
import com.cureya.cure4mind.relaxation.viewHolder.PoseViewHolder

/**
 * @author Udhaya
 * Created on 06-06-2022
 */
class PoseAdapter(
    private val listOfPose: List<String>,
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<PoseViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PoseViewHolder {
        return PoseViewHolder(
            RecommendedYogaPoseDialogItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: PoseViewHolder, position: Int) {
        holder.bind(listOfPose[position])
    }

    override fun getItemCount(): Int {
        return listOfPose.size
    }
}