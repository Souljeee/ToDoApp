package com.soulje.todoapp.db

import androidx.room.*

@Dao
interface TasksDao {

    @Query("SELECT * FROM TaskEntity")
    fun all() : MutableList<TaskEntity>

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(entity: TaskEntity)

    @Update
    fun update(entity: TaskEntity)

    @Delete
    fun delete(entity: TaskEntity)
}