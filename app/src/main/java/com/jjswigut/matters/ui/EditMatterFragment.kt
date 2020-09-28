package com.jjswigut.matters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.navigation.Navigation
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.database.MatterDatabase
import com.jjswigut.matters.databinding.FragmentEditMatterBinding
import kotlinx.coroutines.launch

class EditMatterFragment : BaseFragment() {

    private var _binding: FragmentEditMatterBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentEditMatterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        binding.saveFab.setOnClickListener { view ->

            val title = binding.titleInput.text.toString().trim()
            val content = binding.contentInput.text.toString().trim()

            if (title.isEmpty()) {
                binding.titleInput.error = "Give it a name!"
                binding.titleInput.requestFocus()
                return@setOnClickListener
            }

            if (content.isEmpty()) {
                binding.contentInput.error = "You forgot the whole point of this app!"
                binding.contentInput.requestFocus()
                return@setOnClickListener
            }

            launch {
                val matter = Matter(title, content)
                context?.let {
                    MatterDatabase.getInstance(it).matterDataBaseDao.insert(matter)
                    it.toast("Your Matter now matters")
                }
            }
            Navigation.findNavController(view).navigate(
                EditMatterFragmentDirections.actionEditMatterFragmentToMatterListFragment()
            )

        }

    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

}