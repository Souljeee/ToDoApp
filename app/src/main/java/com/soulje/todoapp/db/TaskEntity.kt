package com.soulje.todoapp.db

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity
data class TaskEntity(

    @PrimaryKey(autoGenerate = true)
    val id : Int = 0,
    var title: String,
    var done : Boolean,
    var day : Int = 0,
    var month : Int = 0


)
