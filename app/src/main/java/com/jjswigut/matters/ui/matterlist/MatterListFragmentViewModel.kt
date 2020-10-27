package com.jjswigut.matters.ui.matterlist

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.database.MatterRepository

class MatterListFragmentViewModel @ViewModelInject constructor(
    private val repo: MatterRepository,
) : ViewModel() {

    val allMatters: LiveData<List<Matter>> = repo.allMatters

}