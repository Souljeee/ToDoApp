package com.soulje.todoapp.db

import androidx.room.Database
import androidx.room.RoomDatabase

@Database(entities = arrayOf(TaskEntity::class), version = 3, exportSchema = false)

abstract class TasksDataBase : RoomDatabase() {

    abstract fun tasksDao() : TasksDao

}