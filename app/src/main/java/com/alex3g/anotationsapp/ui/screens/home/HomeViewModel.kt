package com.alex3g.anotationsapp.ui.screens.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.anotationsapp.dao.NoteDao
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class HomeViewModel(
    private val noteDao: NoteDao
): ViewModel() {

    val uiState = noteDao.getAllNotes( )
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            emptyList()
        )

    fun deleteNote(id: Int) {
        viewModelScope.launch(Dispatchers.IO)  {
            noteDao.deleteNote(id)
        }
    }
}