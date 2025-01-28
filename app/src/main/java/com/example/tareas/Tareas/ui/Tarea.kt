package com.example.tareas.Tareas.ui

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults.topAppBarColors
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.example.tareas.Tareas.data.Tarea
import java.sql.Date


@Composable
fun TaskApp(viewModel: TareaViewModel = hiltViewModel()) {
    val tasks by viewModel.tasks.collectAsState()
    Scaffold(
        topBar = {
            TopAppBar(
                title = { Text("Task Manager") },
                actions = {
                    IconButton(onClick = { viewModel.toggleSort() }) {
                        Icon(Icons.Default.Favorite, contentDescription = "Sort")
                    }
                }
            )
        },
        floatingActionButton = {
            FloatingActionButton(onClick = { /* Open add task dialog */ }) {
                Icon(Icons.Default.Add, contentDescription = "Add Task")
            }
        }
    ) { padding ->
        LazyColumn(
            contentPadding = padding,
            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(Tarea) { Tarea ->
                TaskItem(Tarea = Tarea, onDelete = { viewModel.deleteTask(it) })
            }
        }
    }
}

@Composable
fun TaskItem(Tarea: Tarea, onDelete: (Tarea) -> Unit) {
    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(horizontal = 8.dp),
    )
    {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column {
                Text(Tarea.nombre, style = MaterialTheme.typography.bodySmall)
                Text("Deadline: ${Date(Tarea.deadline)}", style = MaterialTheme.typography.bodyMedium)
                Text("Type: ${Tarea.tipo}", style = MaterialTheme.typography.bodyMedium)
            }
            IconButton(onClick = { onDelete(Tarea) }) {
                Icon(Icons.Default.Delete, contentDescription = "Delete Task")
            }
        }
    }
}