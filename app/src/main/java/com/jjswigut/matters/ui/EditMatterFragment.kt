package com.jjswigut.matters.ui

import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import com.jjswigut.matters.R
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.database.MatterDatabase
import com.jjswigut.matters.databinding.FragmentEditMatterBinding
import kotlinx.coroutines.launch

class EditMatterFragment : BaseFragment() {

    private var _binding: FragmentEditMatterBinding? = null
    private val binding get() = _binding!!

    private var matter: Matter? = null

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditMatterBinding.inflate(inflater, container, false)
        val view = binding.root
        setHasOptionsMenu(true)
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        arguments?.let {
            if (it.containsKey(ARG_MATTER)) {
                matter = it.getSerializable(ARG_MATTER) as Matter
            }
            binding.titleInput.setText(matter?.matterTitle)
            binding.contentInput.setText(matter?.matterContent)
        }

        binding.saveFab.setOnClickListener {
            launch {

                context?.let {
                    // if matter is null, we had no arguments to start out fragment
                    val title = binding.titleInput.text.toString().trim()
                    val content = binding.contentInput.text.toString().trim()
                    if (matter == null) {
                        val mMatter = Matter(title, content)
                        MatterDatabase.getInstance(it).matterDataBaseDao.insert(mMatter)
                        it.toast("Your Matter now matters")
                    } else {
                        // smart casting doesn't work for us here, so we have to unwrap it ourself
                        matter?.let { unwrappedMatter ->
                            unwrappedMatter.matterContent = content
                            unwrappedMatter.matterTitle = title
                            MatterDatabase.getInstance(it).matterDataBaseDao.update(unwrappedMatter)
                            it.toast("Your Matter is updated")
                        }
                    }
                }
            }
            navigate()
        }
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.menu_delete -> {
                deleteMatter()
                navigate()
                return true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    private fun validateInput(): Boolean {
        val title = binding.titleInput.text.toString().trim()
        val content = binding.contentInput.text.toString().trim()

        if (title.isEmpty()) {
            binding.titleInput.error = "Give it a name!"
            binding.titleInput.requestFocus()
            return false
        }

        if (content.isEmpty()) {
            binding.contentInput.error = "You forgot the whole point of this app!"
            binding.contentInput.requestFocus()
            return false
        } else return true
    }

//    private fun saveMatter() {
//        launch {
//             matter = Matter(
//                input().matterId,
//                input().matterTitle,
//                input().matterContent
//            )
//            context?.let {
//                MatterDatabase.getInstance(it).matterDataBaseDao.insert(matter!!)
//                it.toast("Your Matter now matters")
//            }
//        }
//    }

    //    private fun updateMatter() {
//        launch {
//            val matter = Matter(input().matterTitle, input().matterContent)
//            context?.let {
//                MatterDatabase.getInstance(it).matterDataBaseDao.update(matter)
//                it.toast("Your Matter now matters")
//            }
//        }
//    }

    private fun deleteMatter() {
        launch {
            if(matter != null) {
                context?.let {
                    MatterDatabase.getInstance(it).matterDataBaseDao.delete(matter!!)
                    it.toast("Your Matter no longer matters")
                }
            } else {
                // should never be here! we're trying to delete a matter that doesn't exist - maybe display a message to the user?
            }
        }
    }

    private fun navigate() {
        view?.let {
            Navigation.findNavController(it).navigate(
                EditMatterFragmentDirections.actionEditMatterFragmentToMatterListFragment()
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    companion object {
        private const val ARG_MATTER = "arg_matter"
        fun newBundle(matter: Matter) = Bundle().apply {
            putSerializable(ARG_MATTER, matter)
        }
    }
}

