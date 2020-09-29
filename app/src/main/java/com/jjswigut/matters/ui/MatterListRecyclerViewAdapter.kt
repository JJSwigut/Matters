package com.jjswigut.matters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.jjswigut.matters.R
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.util.ListDiffCallback


class MatterListRecyclerViewAdapter(private val actionHandler: MatterActionHandler) :
    RecyclerView.Adapter<MatterListRecyclerViewAdapter.ViewHolder>() {

    private var elements = listOf<Matter>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_matter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = elements[position]
        holder.titleView.text = item.matterTitle
        holder.contentView.text = item.matterContent

        holder.itemView.setOnClickListener {
            actionHandler(MatterAction.MatterClicked(position, item))
        }
    }

    override fun getItemCount(): Int = elements.size


    fun updateData(newData: List<Matter>) {

        val diffResult = DiffUtil.calculateDiff(
            ListDiffCallback
                (newList = newData, oldList = elements)
        )
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.matter_title)
        val contentView: TextView = view.findViewById(R.id.matter_content)


    }

}

typealias MatterActionHandler = (MatterAction) -> Unit
