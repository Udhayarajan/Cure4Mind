package com.cureya.cure4mind.relaxation.ui

import android.app.Dialog
import android.os.Bundle
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import com.cureya.cure4mind.databinding.FragmentRecommendedYogaPoseBinding
import com.cureya.cure4mind.relaxation.adapter.PoseAdapter


class RecommendedYogaPoseDialogFragment : DialogFragment() {
    private var moodName: String? = null
    private var recommendedYogaPose: List<String>? = null
    private lateinit var binding: FragmentRecommendedYogaPoseBinding
    var fragment: YogaFilterFragment? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            moodName = it.getString(ARG_MOOD)
            recommendedYogaPose = it.getStringArrayList(ARG_POSE_LIST)
        }
    }

    override fun onCreateDialog(savedInstanceState: Bundle?): Dialog {
        binding = FragmentRecommendedYogaPoseBinding.inflate(layoutInflater)
        val dialog = AlertDialog.Builder(requireContext()).run {
            setView(binding.root)
            create()
        }

        binding.moodName.text = moodName
        binding.close.setOnClickListener { dialog.dismiss() }

        binding.posesRecyclerView.adapter = recommendedYogaPose?.let {
            PoseAdapter(it) { title ->
                dismiss()
                fragment?.gotoYogaDetailedFragment(title)
            }
        }

        return dialog
    }

    companion object {
        @JvmStatic
        fun newInstance(name: String, listOfPose: List<String>) =
            RecommendedYogaPoseDialogFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_MOOD, name)
                    putStringArrayList(ARG_POSE_LIST, ArrayList(listOfPose))
                }
            }

        private const val ARG_MOOD = "Mood name"
        private const val ARG_POSE_LIST = "recommended poses"
    }
}