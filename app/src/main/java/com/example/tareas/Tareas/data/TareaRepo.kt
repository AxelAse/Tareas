package com.example.tareas.Tareas.data

class TareaRepo(private val TareaDAO: TaskDao) {
    fun getTasksSortedByDeadline() = TareaDAO.getAllTasksByDeadline()
    fun getTasksSortedByType() = TareaDAO.getAllTasksByType()

    suspend fun insertTask(task: Tarea) = TareaDAO.insertTask(task)
    suspend fun deleteTask(task: Tarea) = TareaDAO.deleteTask(task)
}