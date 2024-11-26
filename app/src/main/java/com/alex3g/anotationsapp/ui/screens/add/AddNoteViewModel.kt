package com.alex3g.anotationsapp.ui.screens.add

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.anotationsapp.dao.NoteDao
import com.alex3g.anotationsapp.entities.NoteEntity
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.time.LocalDate

class AddNoteViewModel(
    private val noteDao: NoteDao
) : ViewModel() {

    fun addNote(title: String, description: String) {
        viewModelScope.launch (Dispatchers.IO){
            noteDao.addNote(
                NoteEntity(
                    title = title,
                    description = description,
                    date = LocalDate.now().toString()
                )
            )
        }
    }

}