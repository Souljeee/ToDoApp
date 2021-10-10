package com.soulje.todoapp.model

import com.soulje.todoapp.db.TaskEntity
import com.soulje.todoapp.db.TasksDao

class DBRepositoryImpl(private val dbDataSource : TasksDao) : DBRepository {

    override fun getAllTasks(): MutableList<TaskEntity> {
        return dbDataSource.all()
    }

    override fun saveEntity(task: TaskEntity) {
        dbDataSource.insert(task)
    }

    override fun updateEntity(task: TaskEntity) {
        dbDataSource.update(task)
    }

    override fun deleteEntity(task: TaskEntity) {
        dbDataSource.delete(task)
    }

    /*private fun convertTaskEntityToTask(entityList: MutableList<TaskEntity>) : MutableList<Task>{
        val tasks = mutableListOf<Task>()

        for (i in 0 until entityList.size){
            tasks.add(Task(
                title = entityList[i].title,
                done =  entityList[i].done
            ))
        }

        return tasks
    }

    private fun convertTaskToEntity(task: Task,id : Int) : TaskEntity {
        return TaskEntity(id = id, title = task.title, done = task.done)
    }

    private fun convertTaskToEntity(task: Task) : TaskEntity {
        return TaskEntity(id = 0, title = task.title, done = task.done)
    }*/
}