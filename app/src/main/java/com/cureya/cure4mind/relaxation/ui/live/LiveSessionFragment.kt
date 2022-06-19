package com.cureya.cure4mind.relaxation.ui.live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cureya.cure4mind.LiveSession
import com.cureya.cure4mind.R
import com.cureya.cure4mind.databinding.FragmentRelaxationLiveSessionBinding
import com.cureya.cure4mind.relaxation.adapter.LiveSessionAdapter
import java.util.*

class LiveSessionFragment : Fragment() {

    private lateinit var binding: FragmentRelaxationLiveSessionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentRelaxationLiveSessionBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.apply {
            sessionStatus.text = "2/7"
            sessionProgressIndicator.progress = 2
            sessionProgressIndicator.max = 7
            sessionName.text = "Session Name"
            hostName.text = "Host Name"
            sessionDescription.text =
                "liqua id fugiat nostrud irure ex duis ea quis id quis ad et. Sunt qui esse pariatur duis deserunt mollit dolore cillirure tempor cupidatat incididunt sint deserunt ut voluptate aute id deserunt nisi.Aliqua id fugiat nostrud irure ex duis ea quis id quis ad et."
            toolbar.setOnMenuItemClickListener { item ->
                if (item.itemId == R.id.article) {
                    findNavController().navigate(R.id.action_liveSessionFragment_to_myLiveSessionFragment)
                    return@setOnMenuItemClickListener true
                }
                false
            }

            val onRegisterButtonClicked:(LiveSession)->Unit ={
                val action =
                    LiveSessionFragmentDirections.actionGlobalLiveSessionRegisteredFragment(
                        it.sessionId)
                findNavController().navigate(action)
            }

            val onCardClicked : (LiveSession)->Unit = {
                val action =
                    LiveSessionFragmentDirections.actionLiveSessionFragmentToLiveSessionDetailedFragment(
                        it.sessionId)
                findNavController().navigate(action)
            }

            sessionRecyclerView.adapter = LiveSessionAdapter(sampleLiveSessions, onRegisterButtonClicked, onCardClicked)
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() = LiveSessionFragment()
        val sampleLiveSessions = listOf(
            LiveSession(
                1,
                "Session 1",
                "Host 1",
                Calendar.getInstance().time,
                "liqua id fugiat nostrud irure ex duis ea quis id quis ad et. Sunt qui esse pariatur duis deserunt mollit dolore cillirure tempor cupidatat incididunt sint deserunt ut voluptate aute id deserunt nisi.Aliqua id fugiat nostrud irure ex duis ea quis id quis ad et.",
            ),
            LiveSession(
                2,
                "Session 2",
                "Host 2",
                Calendar.getInstance().time,
                "liqua id fugiat nostrud irure ex duis ea quis id quis ad et. Sunt qui esse pariatur duis deserunt mollit dolore cillirure tempor cupidatat incididunt sint deserunt ut voluptate aute id deserunt nisi.Aliqua id fugiat nostrud irure ex duis ea quis id quis ad et.",
            ),
            LiveSession(
                3,
                "Session 3",
                "Host 3",
                Calendar.getInstance().time,
                "liqua id fugiat nostrud irure ex duis ea quis id quis ad et. Sunt qui esse pariatur duis deserunt mollit dolore cillirure tempor cupidatat incididunt sint deserunt ut voluptate aute id deserunt nisi.Aliqua id fugiat nostrud irure ex duis ea quis id quis ad et.",
            )

        )
    }
}