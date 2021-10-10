package com.soulje.todoapp.view

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import com.soulje.todoapp.R
import com.soulje.todoapp.adapter.TaskListAdapter
import com.soulje.todoapp.databinding.MainFragmentBinding
import com.soulje.todoapp.db.TaskEntity
import com.soulje.todoapp.model.DataState
import com.soulje.todoapp.viewModel.MainViewModel
import java.util.*


class MainFragment : Fragment() {

    private lateinit var binding : MainFragmentBinding
    private lateinit var adapter : TaskListAdapter
    private lateinit var taskList : MutableList<TaskEntity>
    private lateinit var viewModel: MainViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = MainFragmentBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProvider(this).get(MainViewModel::class.java)
        viewModel.getData().observe(viewLifecycleOwner, Observer {
            renderData(it)
        })
        viewModel.getData()
    }

    private fun renderData(data : DataState) = with(binding) {
        when (data){
            is DataState.Success ->{
                taskList = data.tasksList
                initRecyclerView(taskList,viewModel)
                checkDate(taskList)
                delete.setOnClickListener {
                    viewModel.deleteTask(taskList[0])
                    adapter.removeTask(0)
                }
            }
        }
    }

    private fun checkDate(tasks: MutableList<TaskEntity>): MutableList<TaskEntity> {
        var count = 0
        val dayNow = GregorianCalendar().get(Calendar.DAY_OF_MONTH).toString().toInt()
        Log.d("tag",dayNow.toString())
        val monthNow =  GregorianCalendar().get(Calendar.MONTH).toString().toInt() + 1
        Log.d("tag",monthNow.toString())
        val size = tasks.size
        for(i in 0 until size - count){
            if(tasks[i].day < dayNow){
                if(tasks[i].month <= monthNow){
                    viewModel.deleteTask(tasks[i])
                    //adapter.removeTask(i)
                    count++
                }
            }else{
                if(tasks[i].month < monthNow){
                    viewModel.deleteTask(tasks[i])
                    //adapter.removeTask(i)
                    count++
                }
            }
        }
        adapter.notifyDataSetChanged()
        return tasks
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initBottomSheet(view)
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initBottomSheet(view : View) = with(binding) {
        fabAdd.setOnClickListener {
            val bsh = BottomSheetDialog(requireContext(),R.style.BottomSheetDialogTheme)
            val bottomSheetView = LayoutInflater.from(requireContext()).inflate(R.layout.bottom_sheet_add_task_layout,view.findViewById(R.id.bottom_sheet_container))
            val fab = bottomSheetView.findViewById<FloatingActionButton>(R.id.fab_send)
            fab.setOnClickListener {
                val editText = bottomSheetView.findViewById<EditText>(R.id.task_title1)
                val title = editText.text.toString()
                val task = TaskEntity(title = title,done = false,day = GregorianCalendar().get(Calendar.DAY_OF_MONTH).toString().toInt(), month = GregorianCalendar().get(Calendar.MONTH).toString().toInt()+1)
                viewModel.saveTaskToDB(task)
                adapter.addTask(task)
                editText.setText(" ", TextView.BufferType.EDITABLE)
            }
            bsh.setContentView(bottomSheetView)
            bsh.show()
        }
    }

    private fun initRecyclerView(tasks : MutableList<TaskEntity>,viewModel: MainViewModel) = with(binding) {
        tasksList.setHasFixedSize(true)
        tasksList.layoutManager = LinearLayoutManager(context)
        adapter = TaskListAdapter(viewModel)
        adapter.setTasks(tasks)
        tasksList.adapter = adapter
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}