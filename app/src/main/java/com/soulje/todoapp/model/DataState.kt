package com.soulje.todoapp.model

import com.soulje.todoapp.db.TaskEntity

sealed class DataState {
    data class Success(val tasksList : MutableList<TaskEntity>) : DataState()
    data class Error(val error : Throwable) : DataState()
    data class Loading(val progress: Int?) : DataState()
}