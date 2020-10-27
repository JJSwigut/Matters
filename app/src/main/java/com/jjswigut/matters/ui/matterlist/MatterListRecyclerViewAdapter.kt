package com.jjswigut.matters.ui.matterlist

import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.databinding.FragmentMatterBinding
import com.jjswigut.matters.ui.MatterAction
import com.jjswigut.matters.util.ListDiffCallback


class MatterListRecyclerViewAdapter(private val actionHandler: MatterActionHandler) :
    RecyclerView.Adapter<MatterListRecyclerViewAdapter.ViewHolder>() {

    private val elements: ArrayList<Matter> = arrayListOf()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(
            binding = FragmentMatterBinding.inflate(
                LayoutInflater.from(parent.context),
                parent,
                false
            ),
            actionHandler = actionHandler,
            elements = elements
        )
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = (elements[position])
        holder.bind(item)
    }

    override fun getItemCount(): Int = elements.size


    fun updateData(newData: List<Matter>) {

        val diffResult = DiffUtil.calculateDiff(
            ListDiffCallback
                (newList = newData, oldList = elements)
        )
        elements.clear()
        elements.addAll(newData)
        diffResult.dispatchUpdatesTo(this)
    }


    inner class ViewHolder(
        private val binding: FragmentMatterBinding,
        private val actionHandler: MatterActionHandler,
        private val elements: List<Matter>
    ) : RecyclerView.ViewHolder(binding.root) {

        private val titleView: TextView = binding.matterTitle
        private val contentView: TextView = binding.matterContent
        private fun element() = elements[adapterPosition]

        fun bind(item: Matter) {
            //use element() here to set up your view and do any onclicks that ya want
            titleView.text = element().matterTitle
            contentView.text = element().matterContent
            binding.matterView.setOnClickListener {
                actionHandler(MatterAction.MatterClicked(position, item))
            }


        }
    }

}

typealias MatterActionHandler = (MatterAction) -> Unit
