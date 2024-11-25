import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ElevatedCard
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.alex3g.anotationsapp.NoteViewModel
import com.alex3g.anotationsapp.data.local.entity.NoteEntity

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun HomeScreen(viewModel: NoteViewModel, onAddClick: () -> Unit) {
    val notes = viewModel.readAllData.collectAsState(initial = emptyList())

    Scaffold(
        topBar = {
            TopAppBar(
                colors = topAppBarColors(
                    containerColor = MaterialTheme.colorScheme.primaryContainer,
                    titleContentColor = MaterialTheme.colorScheme.primary,
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
            FloatingActionButton(onClick = { onAddClick() }) {
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
            if (notes.value.isEmpty()) {
                EmptyStateMessage("Nenhuma anotação disponível")
            } else {
                LazyColumn(
                    modifier = Modifier.fillMaxSize(),
                    verticalArrangement = Arrangement.spacedBy(16.dp),
                    contentPadding = PaddingValues(16.dp),
                ) {
                    items(notes.value) { note ->
                        AnnotationItem(note = note)
                    }
                }
            }
        }
    }
}

@Composable
fun AnnotationItem(note: NoteEntity) {
    ElevatedCard(
        elevation = CardDefaults.cardElevation(
            defaultElevation = 6.dp
        ),
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.elevatedCardColors(containerColor = Color.White)
    ) {
        Column(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            Text(text = note.title, fontSize = 20.sp, color = Color.Black)
            Text(text = note.description, fontSize = 17.sp, color = Color.Black)
            Text(
                text = formatDate(note.dateAdded),
                textAlign = TextAlign.End,
                modifier = Modifier.fillMaxWidth(),
            )
        }
    }
}

@Composable
fun EmptyStateMessage(message: String, modifier: Modifier = Modifier) {
    Text(
        text = message,
        modifier = modifier
            .fillMaxSize()
            .padding(16.dp),
        textAlign = TextAlign.Center,
        fontSize = 18.sp,
        color = MaterialTheme.colorScheme.onBackground
    )
}


fun formatDate(timestamp: Long): String {
    val formatter = java.text.SimpleDateFormat("dd/MM/yyyy", java.util.Locale.getDefault())
    return formatter.format(java.util.Date(timestamp))
}
