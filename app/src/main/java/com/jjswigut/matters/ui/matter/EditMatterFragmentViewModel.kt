package com.jjswigut.matters.ui.matter

import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jjswigut.matters.database.Matter
import com.jjswigut.matters.database.MatterRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class EditMatterFragmentViewModel @ViewModelInject constructor(
    private val repo: MatterRepository,
) : ViewModel() {

    fun insert(matter: Matter) = viewModelScope.launch(Dispatchers.IO) {
        repo.insert(matter)
    }

    fun delete(matter: Matter) = viewModelScope.launch(Dispatchers.IO) {
        repo.delete(matter)
    }

    fun update(matter: Matter) = viewModelScope.launch(Dispatchers.IO) {
        repo.update(matter)
    }
}