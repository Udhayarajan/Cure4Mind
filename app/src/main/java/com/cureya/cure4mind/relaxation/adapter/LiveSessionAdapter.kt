package com.cureya.cure4mind.relaxation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.LiveSession
import com.cureya.cure4mind.databinding.SessionRegisterCardItemBinding
import com.cureya.cure4mind.relaxation.viewHolder.LiveSessionViewHolder

/**
 * @author Udhaya
 * Created on 05-06-2022
 */

class LiveSessionAdapter(
    private val liveSessions: List<LiveSession>,
    private val onRegisterButtonClicked: (LiveSession) -> Unit,
    private val onCardClicked: (LiveSession) -> Unit
): RecyclerView.Adapter<LiveSessionViewHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): LiveSessionViewHolder {
        return LiveSessionViewHolder(
            SessionRegisterCardItemBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            onRegisterButtonClicked,
            onCardClicked
        )
    }

    override fun onBindViewHolder(holder: LiveSessionViewHolder, position: Int) {
        holder.bind(liveSessions[position])
    }

    override fun getItemCount(): Int {
        return liveSessions.size
    }
}