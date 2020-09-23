package com.jjswigut.matters.ui

import android.os.Bundle
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.jjswigut.matters.ui.MatterListRecyclerViewAdapter
import com.jjswigut.matters.R
import com.jjswigut.matters.dummy.DummyContent

/**
 * A fragment representing a list of Items.
 */
class MatterListFragment : Fragment() {

    private var columnCount = 1

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_matter_list, container, false)

        // Set the adapter
        if (view is RecyclerView) {
            with(view) {
                layoutManager = when {
                    columnCount <= 1 -> LinearLayoutManager(context)
                    else -> GridLayoutManager(context, columnCount)
                }
                //This is where I'm supposed to pass the list but I didn't finish
                adapter = MatterListRecyclerViewAdapter()
            }
        }
        return view
    }



}