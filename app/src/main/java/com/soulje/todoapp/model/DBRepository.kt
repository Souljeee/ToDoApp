package com.soulje.todoapp.model

import com.soulje.todoapp.db.TaskEntity

interface DBRepository {
    fun getAllTasks() : MutableList<TaskEntity>
    fun saveEntity(task : TaskEntity)
    fun updateEntity(task : TaskEntity)
    fun deleteEntity(task : TaskEntity)
}