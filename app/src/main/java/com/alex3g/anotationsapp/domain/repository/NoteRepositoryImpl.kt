package com.alex3g.anotationsapp.domain.repository

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import com.alex3g.anotationsapp.data.local.dao.NoteDao
import com.alex3g.anotationsapp.data.local.entity.NoteEntity
import com.alex3g.anotationsapp.data.local.repository.NoteRepository
import kotlinx.coroutines.flow.Flow

class NoteRepositoryImpl(private val noteDao: NoteDao) : NoteRepository {
    private val notesFlow: Flow<List<NoteEntity>> = noteDao.getNotesOrderedByDateAdded()

    @Suppress("RedundantSuspendModifier")
    @WorkerThread
    override suspend fun upsertNote(note: NoteEntity) {
        try {
            noteDao.upsertNote(note)
        } catch (e: Exception) {
            print("Error upserting note: $e")
        }
    }

    override suspend fun deleteNote(note: NoteEntity) {
        try {
            noteDao.deleteNote(note)
        } catch (e: Exception) {
            print("Error deleting note: $e")
        }
    }

    override fun getNotesOrderedByDateAdded(): Flow<List<NoteEntity>> {
        return notesFlow
    }

    override fun getNotesOrderedByTitle(): Flow<List<NoteEntity>> {
        return noteDao.getNotesOrderedByTitle()
    }
}