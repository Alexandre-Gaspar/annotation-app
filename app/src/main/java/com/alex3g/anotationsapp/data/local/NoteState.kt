package com.alex3g.anotationsapp.data.local

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import com.alex3g.anotationsapp.data.local.entity.NoteEntity

data class NoteState(
    val notes: List<NoteEntity> = emptyList(),
    val title: MutableState<String> = mutableStateOf(""),
    val description: MutableState<String> = mutableStateOf("")
)
