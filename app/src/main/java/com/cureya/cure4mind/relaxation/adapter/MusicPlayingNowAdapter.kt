package com.cureya.cure4mind.relaxation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.FragmentRelaxationMusicPlayingNowItemBinding
import com.cureya.cure4mind.model.Music
import com.cureya.cure4mind.relaxation.viewHolder.MusicPlayingViewHolder

/**
 * @author Udhaya
 * Created on 19-06-2022
 */

class MusicPlayingNowAdapter(
    private val musicList: List<Music>,
) : RecyclerView.Adapter<MusicPlayingViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MusicPlayingViewHolder {
        return MusicPlayingViewHolder(
            FragmentRelaxationMusicPlayingNowItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            )
        )
    }

    override fun onBindViewHolder(holder: MusicPlayingViewHolder, position: Int) {
        holder.bind(musicList[position])
    }

    override fun getItemCount(): Int {
        return musicList.size
    }
}