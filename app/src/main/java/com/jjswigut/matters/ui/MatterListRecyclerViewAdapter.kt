package com.jjswigut.matters.ui

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.jjswigut.matters.R
import com.jjswigut.matters.database.DummyMatter


class MatterListRecyclerViewAdapter :
    RecyclerView.Adapter<MatterListRecyclerViewAdapter.ViewHolder>() {

    private var values: List<DummyMatter> = ArrayList()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.fragment_matter, parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        val item = values[position]
        holder.titleView.text = item.matterTitle
        holder.contentView.text = item.matterContent
    }

    override fun getItemCount(): Int = values.size

    fun submitList(dummyList: List<DummyMatter>) {
        values = dummyList
    }

    inner class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.matter_title)
        val contentView: TextView = view.findViewById(R.id.matter_content)
    }
}

class DataSource {
    companion object {
        fun createDataSet(): ArrayList<DummyMatter> {
            val list = ArrayList<DummyMatter>()
            list.add(
                DummyMatter(
                    "Note 1", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 2", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 3", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 4", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 5", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 6", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 7", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            list.add(
                DummyMatter(
                    "Note 8", "Wikis are enabled " +
                            "by wiki software, otherwise known as wiki engines. " +
                            "A wiki engine, being a form of a content management system, " +
                            "differs from other web-based systems such as blog software,"
                )
            )
            return list
        }
    }
}
