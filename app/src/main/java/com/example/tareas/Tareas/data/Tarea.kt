package com.example.tareas.Tareas.data

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "Tarea")
data class Tarea(
    @PrimaryKey(autoGenerate = true) val id: Int = 0,
    val nombre: String,
    val deadline: Long,
    val tipo: String
)
