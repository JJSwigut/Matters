package com.jjswigut.matters.ui.matterlist

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import androidx.navigation.Navigation
import androidx.recyclerview.widget.StaggeredGridLayoutManager
import com.jjswigut.matters.R
import com.jjswigut.matters.databinding.FragmentMatterListBinding
import com.jjswigut.matters.ui.BaseFragment
import com.jjswigut.matters.ui.MatterAction
import com.jjswigut.matters.ui.matter.EditMatterFragment
import dagger.hilt.android.AndroidEntryPoint


/**
 * A fragment representing a list of Items.
 */

@AndroidEntryPoint
class MatterListFragment : BaseFragment() {

    private var _binding: FragmentMatterListBinding? = null
    private val binding get() = _binding!!

    private lateinit var listAdapter: MatterListRecyclerViewAdapter

    private val viewModel: MatterListFragmentViewModel by viewModels()


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        listAdapter = MatterListRecyclerViewAdapter(::handleAction)

    }

    override fun onResume() {
        super.onResume()
        viewModel.allMatters.observe(this, Observer { list ->
            list?.let { listAdapter.updateData(it) }
        })

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMatterListBinding.inflate(inflater, container, false)
        val view = binding.root

        binding.newFab.setOnClickListener {
            val action =
                MatterListFragmentDirections.actionMatterListFragmentToEditMatterFragment()
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
            adapter = listAdapter
        }
    }

    private fun handleAction(action: MatterAction) {
        when (action) {
            is MatterAction.MatterClicked -> {

                view?.let {
                    Navigation.findNavController(it).navigate(
                        R.id.action_matterListFragment_to_editMatterFragment,
                        EditMatterFragment.newBundle(action.matter)
                    )
                }
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}
