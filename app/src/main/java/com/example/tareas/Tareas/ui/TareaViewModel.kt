package com.example.tareas.Tareas.ui

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.tareas.Tareas.data.Tarea
import com.example.tareas.Tareas.data.TareaRepo
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.flatMapLatest
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch

class TareaViewModel(private val repository: TareaRepo) : ViewModel() {
    private val _sortByDeadline = MutableStateFlow(true)
    val tasks: StateFlow<List<Tarea>> = _sortByDeadline.flatMapLatest { sortByDeadline ->
        if (sortByDeadline) repository.getTasksSortedByDeadline()
        else repository.getTasksSortedByType()
    }.stateIn(viewModelScope, SharingStarted.Lazily, emptyList())

    fun toggleSort() {
        _sortByDeadline.value = !_sortByDeadline.value
    }

    fun addTask(nombre: String, deadline: Long, tipo: String) {
        viewModelScope.launch {
            repository.insertTask(Tarea(nombre = nombre, deadline = deadline, tipo = tipo))
        }
    }

    fun deleteTask(task: Tarea) {
        viewModelScope.launch {
            repository.deleteTask(task)
        }
    }
}