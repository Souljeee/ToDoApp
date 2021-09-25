package com.soulje.todoapp.view

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import android.view.inputmethod.InputMethodManager
import android.widget.EditText
import android.widget.TextView
import androidx.core.content.ContextCompat.getSystemService
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.bottomsheet.BottomSheetDialog
import com.google.android.material.floatingactionbutton.FloatingActionButton
import com.google.android.material.textview.MaterialTextView
import com.soulje.todoapp.R
import com.soulje.todoapp.adapter.TaskListAdapter
import com.soulje.todoapp.databinding.MainFragmentBinding
import com.soulje.todoapp.model.Task
import com.soulje.todoapp.viewModel.MainViewModel


class MainFragment : Fragment() {

    private lateinit var binding : MainFragmentBinding
    private lateinit var adapter : TaskListAdapter

    private val taskList = mutableListOf(Task("Задание"),Task("Задание"),Task("Задание"),Task("Задание"),Task("Задание"))

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

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        initRecyclerView()
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
                adapter.addTask(Task(title = title))
                editText.setText(" ", TextView.BufferType.EDITABLE)
            }
            bsh.setContentView(bottomSheetView)
            bsh.show()
        }
    }

    private fun initRecyclerView() = with(binding) {
        tasksList.setHasFixedSize(true)
        tasksList.layoutManager = LinearLayoutManager(context)
        adapter = TaskListAdapter(tasksList)
        adapter.setTasks(taskList)
        tasksList.adapter = adapter
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}