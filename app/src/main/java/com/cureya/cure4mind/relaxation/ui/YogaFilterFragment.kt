package com.cureya.cure4mind.relaxation.ui

import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import com.cureya.cure4mind.MainActivity
import com.cureya.cure4mind.R
import com.cureya.cure4mind.databinding.FragmentYogaFilterBinding
import com.cureya.cure4mind.relaxation.adapter.AsanaAdapter


class YogaFilterFragment : Fragment() {

    private lateinit var binding: FragmentYogaFilterBinding
    private var adapter: AsanaAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentYogaFilterBinding.inflate(inflater, container, false)
        binding.toolbar.setNavigationOnClickListener {
            requireActivity().onBackPressed()
        }
        binding.asanaRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val list = resources.getStringArray(R.array.yoga_types).toList()
        adapter = AsanaAdapter(list) {
            val listOfPose = when (it) {
                list[0] -> resources.getStringArray(R.array.recommended_anxious)
                list[1] -> resources.getStringArray(R.array.recommended_guilty)
                list[2] -> resources.getStringArray(R.array.recommended_depressive)
                list[3] -> resources.getStringArray(R.array.recommended_obsessive)
                list[4] -> resources.getStringArray(R.array.recommended_angry)
                list[5] -> resources.getStringArray(R.array.recommended_apathetic)
                list[6] -> resources.getStringArray(R.array.recommended_mood_swings)
                list[7] -> resources.getStringArray(R.array.recommended_confused)
                else -> TODO("Still not implemented")
            }.asList()
            RecommendedYogaPoseDialogFragment.newInstance(it, listOfPose).apply {
                fragment = this@YogaFilterFragment
                show((context as MainActivity).supportFragmentManager, null)
            }
        }
    }

    fun gotoYogaDetailedFragment(itemTitle: String) = findNavController().navigate(
        YogaFilterFragmentDirections.actionRelaxationFilterFragmentToYogaDetailsFragment(
            itemTitle)
    )
}

