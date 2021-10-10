package com.soulje.todoapp.viewModel

import android.provider.ContactsContract
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.soulje.todoapp.App.App
import com.soulje.todoapp.db.TaskEntity
import com.soulje.todoapp.model.DBRepositoryImpl
import com.soulje.todoapp.model.DataState
import kotlinx.coroutines.*


class MainViewModel(
    val liveData: MutableLiveData<DataState> = MutableLiveData(),
    private val dbRepositoryImpl: DBRepositoryImpl = DBRepositoryImpl(App.getTasksDao())
) : ViewModel(), CoroutineScope by MainScope() {

    fun getData() : LiveData<DataState>{
        getDataFromDB()
        return liveData
    }

    fun saveTaskToDB(task: TaskEntity) {
        launch {
            async(Dispatchers.IO) {
                dbRepositoryImpl.saveEntity(task)
            }
        }
    }

    fun updateTask(task: TaskEntity){
        launch {
            async(Dispatchers.IO) {
                dbRepositoryImpl.updateEntity(task)
            }
        }
    }

    fun deleteTask(task: TaskEntity){
        launch {
            async(Dispatchers.IO) {
                dbRepositoryImpl.deleteEntity(task)
            }
        }
    }

    private fun getDataFromDB() {
        liveData.value = DataState.Loading(null)
        launch {
            val job = async(Dispatchers.IO) { dbRepositoryImpl.getAllTasks() }
            liveData.value = DataState.Success(job.await())
        }
    }

}