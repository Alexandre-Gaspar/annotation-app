package com.alex3g.anotationsapp.ui.screens.home

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex3g.anotationsapp.entities.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(
    viewModel: HomeViewModel,
    onAddClick: () -> Unit,
    onNoteClick: (Int) -> Unit
) {
    val uiState = viewModel.uiState.collectAsState().value
    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = Color(0xFF00796B),
                    titleContentColor = Color.White,
                ),
                title = {
                    Text(
                        text = "Minhas anotações",
                        Modifier.fillMaxWidth(),
                    )
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(
                onClick = {
                    onAddClick()
                },
                containerColor = Color(0xFF00796B),
                contentColor = Color.White) {
                Icon(Icons.Default.Add, contentDescription = "Add new annotation")
            }
        }
    ) { innerPadding ->
        Surface(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding),
            color = MaterialTheme.colorScheme.background,
            shadowElevation = 4.dp
        ) {
            if (uiState.isEmpty()) {
                EmptyNotesScreen()
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(uiState) { note ->
                        AnnotationItem(
                            note = note,
                            onDelete = {
                                viewModel.deleteNote(it)
                            },
                            onNoteClick = { onNoteClick(note.id) }
                        )
                    }
                }
            }
        }
    }
}

@Composable
fun AnnotationItem(
    note: NoteEntity,
    onDelete: (Int) -> Unit,
    onNoteClick: (Int) -> Unit
) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier
            .fillMaxWidth()
            .clickable { onNoteClick(note.id) },
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Row(
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween,
                verticalAlignment = androidx.compose.ui.Alignment.CenterVertically // Centraliza os itens verticalmente
            ) {
                // Coluna para agrupar o título e a data à esquerda
                Column(
                    modifier = Modifier.weight(1f) // Ocupa o espaço restante à esquerda
                ) {
                    Text(
                        text = note.title,
                        fontSize = 20.sp,
                        color = Color(0xFF2E7D32),
                        style = MaterialTheme.typography.titleMedium
                    )
                    Text(
                        text = note.date,
                        textAlign = TextAlign.Start,
                        color = Color(0xFF90A4AE),
                        style = MaterialTheme.typography.bodySmall
                    )
                }

                // Ícone de exclusão à direita
                IconButton(
                    onClick = { onDelete(note.id) }
                ) {
                    Icon(
                        imageVector = Icons.Default.Delete,
                        contentDescription = "Deletar anotação",
                        tint = Color(0xFF2E7D32)
                    )
                }
            }
        }

    }
}

@Composable
fun EmptyNotesScreen() {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp),
        verticalArrangement = Arrangement.Center,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = "Não há nenhuma anotação",
            style = MaterialTheme.typography.headlineMedium,
            color = Color(0xFF004D40),
            fontSize = 18.sp,
            textAlign = TextAlign.Center
        )
    }
}