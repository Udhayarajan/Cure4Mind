package com.cureya.cure4mind

import android.app.Activity
import android.content.Context
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.RecyclerView
import com.cureya.cure4mind.databinding.AsanaCardBinding
import com.cureya.cure4mind.databinding.FragmentRelaxationFilterBinding
import com.cureya.cure4mind.relaxation.ui.RelaxationFragmentDirections
import com.cureya.cure4mind.relaxation.ui.YogaFragmentDirections
import com.google.android.material.dialog.MaterialAlertDialogBuilder


class RelaxationFilterFragment : Fragment() {

    private lateinit var binding: FragmentRelaxationFilterBinding
    private var adapter: AsanaAdapter? = null
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View? {
        binding = FragmentRelaxationFilterBinding.inflate(inflater, container, false)
        binding.asanaRecyclerView.adapter = adapter
        return binding.root
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val list = resources.getStringArray(R.array.yoga_types).toList()
        adapter = AsanaAdapter(list) {
            val listOfPose = when (it) {
                list[0] -> resources.getStringArray(R.array.recommended_anxious)
                else -> TODO("Still not implemented")
            }.asList()
            RecommendedYogaPose.newInstance(it, listOfPose).apply {
                fragment = this@RelaxationFilterFragment
                show((context as MainActivity).supportFragmentManager, null)
            }
        }
    }

    fun gotoYogaDetailedFragment(itemTitle: String) = findNavController().navigate(
        RelaxationFilterFragmentDirections.actionRelaxationFilterFragmentToYogaDetailsFragment(itemTitle)
    )
}
class AsanaAdapter(
    private val types: List<String>,
    private val onClickListener: (String) -> Unit,
) : RecyclerView.Adapter<AsanaAdapter.AsanaViewHolder>() {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): AsanaViewHolder {
        return AsanaViewHolder(
            AsanaCardBinding.inflate(LayoutInflater.from(parent.context), parent, false),
            onClickListener
        )
    }

    override fun getItemCount(): Int {
        return types.size
    }

    override fun onBindViewHolder(holder: AsanaViewHolder, position: Int) {
        holder.bind(types[position])
    }

    class AsanaViewHolder(
        private val asanaCardBinding: AsanaCardBinding,
        private val onClickListener: (String) -> Unit,
    ) : RecyclerView.ViewHolder(asanaCardBinding.root) {
        fun bind(asana: String) {
            asanaCardBinding.asanaText.text = asana
            asanaCardBinding.root.setOnClickListener {
                onClickListener(asana)
            }
        }
    }
}

