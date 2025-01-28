package com.example.tareas.Tareas.data
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import kotlinx.coroutines.flow.Flow

@Dao
interface TaskDao {
    @Query("SELECT * FROM Tarea ORDER BY deadline ASC")
    fun getAllTasksByDeadline(): Flow<List<Tarea>>

    @Query("SELECT * FROM Tarea ORDER BY tipo ASC")
    fun getAllTasksByType(): Flow<List<Tarea>>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertTask(Tarea: Tarea)

    @Delete
    suspend fun deleteTask(Tarea: Tarea)
}