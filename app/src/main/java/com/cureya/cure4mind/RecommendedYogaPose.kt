package com.cureya.cure4mind

import android.app.Dialog
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AlertDialog
import androidx.fragment.app.DialogFragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.FragmentRecommendedYogaPoseBinding
import com.cureya.cure4mind.databinding.RecommendedYogaPoseDialogItemBinding
import com.cureya.cure4mind.relaxation.ui.RelaxationFragment
import com.cureya.cure4mind.relaxation.ui.YogaFragmentDirections

private const val ARG_MOOD = "Mood name"
private const val ARG_POSE_LIST = "recommended poses"

class RecommendedYogaPose : DialogFragment() {
    private var moodName: String? = null
    private var recommendedYogaPose: List<String>? = null
    private lateinit var binding: FragmentRecommendedYogaPoseBinding
     var fragment: RelaxationFilterFragment? = null

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
            RecommendedYogaPose().apply {
                arguments = Bundle().apply {
                    putString(ARG_MOOD, name)
                    putStringArrayList(ARG_POSE_LIST, ArrayList(listOfPose))
                }
            }
    }
}

class PoseAdapter(
    private val listOfPose: List<String>,
    private val onClick: (String) -> Unit,
) : RecyclerView.Adapter<PoseAdapter.ViewHolder>() {

    class ViewHolder(
        private val binding: RecommendedYogaPoseDialogItemBinding,
        private val onClick: (String) -> Unit,
    ) : RecyclerView.ViewHolder(binding.root) {
        fun bind(pose: String) {
            binding.poseName.text = pose
            binding.root.setOnClickListener {
                onClick(pose)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            RecommendedYogaPoseDialogItemBinding.inflate(LayoutInflater.from(parent.context),
                parent,
                false),
            onClick
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(listOfPose[position])
    }

    override fun getItemCount(): Int {
        return listOfPose.size
    }
}