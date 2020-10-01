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

    private lateinit var matter: Matter

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
        binding.contentInput.setText(args.content)
        binding.titleInput.setText(args.title)


        binding.saveFab.setOnClickListener {
            input()
            validateInput()
            if (validateInput()) {
                if (args.title.isNotEmpty()) {
                    updateMatter()

                } else {
                    saveMatter()
                }
                navigate()
            }

        }

    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        super.onCreateOptionsMenu(menu, inflater)
        inflater.inflate(R.menu.menu, menu)
    }

    private fun input(): Matter {
        var title = binding.titleInput.text.toString().trim()
        var content = binding.contentInput.text.toString().trim()
        matter = Matter(title, content)
        return matter
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

    private fun saveMatter() {
        launch {
            val matter = Matter(input().matterTitle, input().matterContent)
            context?.let {
                MatterDatabase.getInstance(it).matterDataBaseDao.insert(matter)
                it.toast("Your Matter now matters")
            }
        }
    }

    private fun updateMatter() {
        launch {
            val matter = Matter(input().matterTitle, input().matterContent)
            context?.let {
                MatterDatabase.getInstance(it).matterDataBaseDao.update(matter)
                it.toast("Your Matter now matters")
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