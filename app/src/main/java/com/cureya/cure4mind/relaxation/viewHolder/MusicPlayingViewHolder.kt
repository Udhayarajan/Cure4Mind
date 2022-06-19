package com.cureya.cure4mind.relaxation.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.cureya.cure4mind.databinding.FragmentRelaxationMusicPlayingNowItemBinding
import com.cureya.cure4mind.model.Music

/**
 * @author Udhaya
 * Created on 19-06-2022
 */

class MusicPlayingViewHolder(private val binding: FragmentRelaxationMusicPlayingNowItemBinding) : RecyclerView.ViewHolder(binding.root) {
    fun bind(item: Music) {
        binding.musicHeading.text = item.musicHeading
        binding.musicImage.load(item.thumbnailUrl)
    }
}