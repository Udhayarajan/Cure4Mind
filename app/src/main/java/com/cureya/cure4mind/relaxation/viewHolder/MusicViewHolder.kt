package com.cureya.cure4mind.relaxation.viewHolder

import android.graphics.drawable.Drawable
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.request.target.CustomTarget
import com.bumptech.glide.request.transition.Transition
import com.cureya.cure4mind.databinding.CardMusicAndVideoBinding
import com.cureya.cure4mind.model.Content
import com.cureya.cure4mind.relaxation.ui.MusicVideoFragment

class MusicViewHolder(private val binding: CardMusicAndVideoBinding,
                      private val listener: MusicVideoFragment
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(item: Content, itemPosition: Int) {
        binding.contentTitle.text = item.title
        binding.contentTime.text = item.duration

        // loading image as a layout background
        val view = binding.contentBackgroundContainer
        Glide.with(view.context).load(item.thumbnailUrl)
            .into(object: CustomTarget<Drawable>() {
                @RequiresApi(api = Build.VERSION_CODES.JELLY_BEAN)
                override fun onResourceReady(resource: Drawable, transition: Transition<in Drawable>?) {
                    view.background = resource
                }
                override fun onLoadCleared(placeholder: Drawable?) {}
            })

        binding.cardPlayer.setOnClickListener {
            listener.goToMusicFragment(itemPosition, item.thumbnailUrl!!, item.title!!)
        }
    }
}