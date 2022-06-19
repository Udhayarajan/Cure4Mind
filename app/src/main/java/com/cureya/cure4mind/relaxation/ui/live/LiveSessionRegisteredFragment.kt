package com.cureya.cure4mind.relaxation.ui.live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.navArgs
import com.cureya.cure4mind.databinding.FragmentLiveSessionRegisteredBinding


class LiveSessionRegisteredFragment : Fragment() {

    private lateinit var binding:FragmentLiveSessionRegisteredBinding
    private val args by navArgs<LiveSessionRegisteredFragmentArgs>()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        // Inflate the layout for this fragment
        binding = FragmentLiveSessionRegisteredBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        val session = LiveSessionFragment.sampleLiveSessions.find {
            it.sessionId == args.sessionId
        }
        binding.registeredText.text = "You have successfully registered \nthe ${session?.sessionName} session"
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = LiveSessionRegisteredFragment()
    }
}