package com.cureya.cure4mind.relaxation.ui.live

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.cureya.cure4mind.databinding.FragmentLiveSessionDetailedBinding

class LiveSessionDetailedFragment : Fragment() {

    private lateinit var binding: FragmentLiveSessionDetailedBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentLiveSessionDetailedBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    companion object {
        @JvmStatic
        fun newInstance(param1: String, param2: String) = LiveSessionDetailedFragment()
    }
}