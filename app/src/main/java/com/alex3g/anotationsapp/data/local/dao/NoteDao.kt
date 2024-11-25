package com.alex3g.anotationsapp.data.local.dao

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Query
import androidx.room.Upsert
import com.alex3g.anotationsapp.data.local.entity.NoteEntity
import kotlinx.coroutines.flow.Flow

@Dao
interface NoteDao {

    @Upsert
    suspend fun upsertNote(note: NoteEntity)

    @Delete
    suspend fun deleteNote(note: NoteEntity)

    @Query("SELECT * FROM note ORDER BY dateAdded")
    fun getNotesOrderedByDateAdded(): Flow<List<NoteEntity>>

    @Query("SELECT * FROM note ORDER BY title ASC")
    fun getNotesOrderedByTitle(): Flow<List<NoteEntity>>

}