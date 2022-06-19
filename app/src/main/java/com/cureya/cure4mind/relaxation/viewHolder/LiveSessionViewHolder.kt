package com.cureya.cure4mind.relaxation.viewHolder

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.LiveSession
import com.cureya.cure4mind.databinding.SessionRegisterCardItemBinding

/**
 * @author Udhaya
 * Created on 05-06-2022
 */

class LiveSessionViewHolder(
    private val binding: SessionRegisterCardItemBinding,
    private val onRegisterClicked: (LiveSession) -> Unit,
    private val onCardClicked: (LiveSession) -> Unit
    ) :
    RecyclerView.ViewHolder(binding.root) {
    fun bind(session: LiveSession) {
        binding.apply {
            sessionName.text = session.sessionName
            hostName.text = session.hostName
            sessionDate.text = session.getDate()
            sessionDescription.text = session.sessionDescription
            registerButton.setOnClickListener {
                onRegisterClicked(session)
            }
            binding.root.setOnClickListener { onCardClicked(session) }
        }
    }
}