package com.alex3g.anotationsapp

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import com.alex3g.anotationsapp.data.local.NoteDB
import com.alex3g.anotationsapp.data.local.entity.NoteEntity
import com.alex3g.anotationsapp.data.local.repository.NoteRepository
import com.alex3g.anotationsapp.domain.repository.NoteRepositoryImpl
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class NoteViewModel(application: Application): AndroidViewModel(application) {

    private val repository: NoteRepository
    val readAllData: StateFlow<List<NoteEntity>> // Alteração para StateFlow

    init {
        val noteDao = NoteDB.getDatabase(application).noteDao()
        repository = NoteRepositoryImpl(noteDao)
        // Transformar Flow em StateFlow
        readAllData = repository.getNotesOrderedByDateAdded()
            .stateIn(viewModelScope, SharingStarted.WhileSubscribed(), emptyList())
    }

    fun upsertNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) { repository.upsertNote(note) }
    }

    fun deleteNote(note: NoteEntity) {
        viewModelScope.launch(Dispatchers.IO) { repository.deleteNote(note) }
    }
}
