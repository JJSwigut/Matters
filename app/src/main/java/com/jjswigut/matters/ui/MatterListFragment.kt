package com.jjswigut.matters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.jjswigut.matters.databinding.FragmentMatterListBinding


/**
 * A fragment representing a list of Items.
 */
class MatterListFragment : Fragment() {

    private var _binding: FragmentMatterListBinding? = null
    private val binding get() = _binding!!
    private lateinit var listAdapter: MatterListRecyclerViewAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatterListBinding.inflate(inflater, container, false)
        val view = binding.root
        initRecyclerView()
        addDataSet()

        return view
    }

    private fun addDataSet() {
        val data = DataSource.createDataSet()
        listAdapter.submitList(data)
    }

    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = LinearLayoutManager(context)
            listAdapter = MatterListRecyclerViewAdapter()
            adapter = listAdapter
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}