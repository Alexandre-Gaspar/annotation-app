package com.alex3g.anotationsapp.data.local.repository

import androidx.room.Delete
import androidx.room.Query
import com.alex3g.anotationsapp.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

interface NoteRepository {
    suspend fun upsertNote(note: NoteEntity)

    suspend fun deleteNote(note: NoteEntity)

    fun getNotesOrderedByDateAdded(): Flow<List<NoteEntity>>

    fun getNotesOrderedByTitle(): Flow<List<NoteEntity>>
}