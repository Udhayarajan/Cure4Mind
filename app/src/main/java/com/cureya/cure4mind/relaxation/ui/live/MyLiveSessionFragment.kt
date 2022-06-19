package com.cureya.cure4mind.relaxation.ui.live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cureya.cure4mind.LiveSession
import com.cureya.cure4mind.R
import com.cureya.cure4mind.databinding.FragmentMyLiveSessionBinding
import com.cureya.cure4mind.relaxation.adapter.LiveSessionAdapter


class MyLiveSessionFragment : Fragment() {

    private lateinit var binding: FragmentMyLiveSessionBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentMyLiveSessionBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        val onRegisterButtonClicked: (LiveSession) -> Unit = {
            val action =
                LiveSessionFragmentDirections.actionGlobalLiveSessionRegisteredFragment(
                    it.sessionId)
            findNavController().navigate(action)
        }

        val onCardClicked: (LiveSession) -> Unit = {
            val action =
                LiveSessionFragmentDirections.actionLiveSessionFragmentToLiveSessionDetailedFragment(
                    it.sessionId)
            findNavController().navigate(action)
        }
        binding.chips.setOnCheckedChangeListener { group, checkedId ->
            if (checkedId == R.id.chip_upcoming) {
                binding.recyclerView.swapAdapter(LiveSessionAdapter(
                    LiveSessionFragment.sampleLiveSessions.filter {
                        it.sessionId <= 1
                    },
                    onRegisterButtonClicked,
                    onCardClicked
                ), true)
            } else if (checkedId == R.id.chip_completed) {
                binding.recyclerView.swapAdapter(LiveSessionAdapter(
                    LiveSessionFragment.sampleLiveSessions.filter {
                        it.sessionId > 1
                    },
                    onRegisterButtonClicked,
                    onCardClicked
                ),
                    true)
            }
        }
        binding.chips.check(R.id.chip_upcoming)
        return binding.root
    }

    companion object {
        @JvmStatic
        fun newInstance() = MyLiveSessionFragment()
    }
}