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


class MatterListRecyclerViewAdapter(private var matters: List<Matter>) :
    RecyclerView.Adapter<MatterListRecyclerViewAdapter.ViewHolder>() {


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_matter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = matters[position]
        holder.titleView.text = item.matterTitle
        holder.contentView.text = item.matterContent

//        holder.itemView.setOnClickListener {
//            val action = MatterListFragmentDirections.actionMatterListFragmentToEditMatterFragment()
//            action.matter = matters[position]
//            Navigation.findNavController(it).navigate(action)
//        }
    }

    override fun getItemCount(): Int = matters.size


    fun submitList(matterList: List<Matter>) {
        val diffCallback = ListDiffCallback(matters, matterList)
        val diffResult = DiffUtil.calculateDiff(diffCallback)
        matters = matterList
        diffResult.dispatchUpdatesTo(this)
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.matter_title)
        val contentView: TextView = view.findViewById(R.id.matter_content)


    }

}
