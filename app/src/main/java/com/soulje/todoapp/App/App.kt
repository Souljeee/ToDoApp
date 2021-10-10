package com.soulje.todoapp.App

import android.app.Application
import androidx.room.Room
import com.soulje.todoapp.db.TasksDao
import com.soulje.todoapp.db.TasksDataBase
import java.lang.IllegalStateException

class App : Application() {

    override fun onCreate() {
        super.onCreate()
        appInstance = this
    }

    companion object{

        private var appInstance: App? = null
        private var db: TasksDataBase? = null
        private const val DB_NAME = "Tasks.db"

        fun getTasksDao():TasksDao{
            if(db == null){
                synchronized(TasksDataBase::class.java){
                    if (db == null){
                        if(appInstance == null){
                            throw IllegalStateException("Application is null while creating DataBase")
                        }
                        db = Room.databaseBuilder(
                            appInstance!!.applicationContext,
                            TasksDataBase::class.java,
                            DB_NAME
                        )
                            .fallbackToDestructiveMigration()
                            .build()
                    }
                }
            }

            return db!!.tasksDao()
        }
    }
}