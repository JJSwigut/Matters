package com.jjswigut.matters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jjswigut.matters.database.MatterDatabase
import com.jjswigut.matters.databinding.FragmentMatterListBinding
import kotlinx.coroutines.launch


/**
 * A fragment representing a list of Items.
 */
class MatterListFragment : BaseFragment() {

    private var _binding: FragmentMatterListBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatterListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.newFab.setOnClickListener {
            val action = MatterListFragmentDirections.actionMatterListFragmentToEditMatterFragment()
            Navigation.findNavController(it).navigate(action)
        }

        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initRecyclerView()
    }


    private fun initRecyclerView() {
        binding.recyclerView.apply {
            layoutManager = StaggeredGridLayoutManager(3, StaggeredGridLayoutManager.VERTICAL)
            setHasFixedSize(true)
            launch {
                context?.let {
                    val matters = MatterDatabase.getInstance(it).matterDataBaseDao.getAllMatters()
                    adapter = MatterListRecyclerViewAdapter(matters)
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}