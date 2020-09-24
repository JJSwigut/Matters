package com.jjswigut.matters.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.jjswigut.matters.databinding.FragmentEditMatterBinding

class EditMatterFragment : Fragment() {

    private lateinit var binding: FragmentEditMatterBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentEditMatterBinding.inflate(inflater, container, false)
        val view = binding.root
        return view
    }

}