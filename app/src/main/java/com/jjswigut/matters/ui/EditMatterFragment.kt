package com.jjswigut.matters.ui

import android.os.Bundle
import android.view.*
import androidx.navigation.Navigation
import androidx.navigation.fragment.navArgs
import com.jjswigut.matters.R
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.database.MatterDatabase
import com.jjswigut.matters.databinding.FragmentEditMatterBinding
import kotlinx.coroutines.launch

class EditMatterFragment : BaseFragment() {

    private var _binding: FragmentEditMatterBinding? = null
    private val binding get() = _binding!!

    private var matter: Matter? = null

    val args: EditMatterFragmentArgs by navArgs()


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
            matter = EditMatterFragmentArgs.fromBundle(it).matter
            binding.titleInput.setText(matter?.matterTitle)
            binding.contentInput.setText(matter?.matterContent)
        }

        binding.saveFab.setOnClickListener {
            input()

            launch {

                context?.let {
                    val mMatter = Matter(input().matterTitle, input().matterContent)

                    if (matter == null) {
                        MatterDatabase.getInstance(it).matterDataBaseDao.insert(mMatter)
                        it.toast("Your Matter now matters")
                    } else {
                        mMatter.matterId = matter!!.matterId
                        MatterDatabase.getInstance(it).matterDataBaseDao.update(mMatter)
                        it.toast("Your Matter is updated")
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

    // Fix the input part
    private fun input(): Matter {
        var title = binding.titleInput.text.toString().trim()
        var content = binding.contentInput.text.toString().trim()
        matter = Matter(title, content)
        return matter as Matter
    }

    private fun validateInput(): Boolean {

        if (input().matterTitle.isEmpty()) {
            binding.titleInput.error = "Give it a name!"
            binding.titleInput.requestFocus()
            return false
        }

        if (input().matterContent.isEmpty()) {
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
            val matter = Matter(input().matterTitle, input().matterContent)
            context?.let {
                MatterDatabase.getInstance(it).matterDataBaseDao.delete(matter)
                it.toast("Your Matter no longer matters")
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
}

