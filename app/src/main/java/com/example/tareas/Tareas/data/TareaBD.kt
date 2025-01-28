package com.example.tareas.Tareas.data

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Tarea::class], version = 1)
abstract class TareaBD : RoomDatabase() {
    abstract fun taskDao(): TaskDao

    companion object {
        @Volatile
        private var INSTANCE: TareaBD? = null

        fun getDatabase(context: Context): TareaBD {
            return INSTANCE ?: synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    TareaBD::class.java,
                    "task_database"
                ).build()
                INSTANCE = instance
                instance
            }
        }
    }
}