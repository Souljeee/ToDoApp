package com.soulje.todoapp.view

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.LayoutManager
import com.soulje.todoapp.viewModel.MainViewModel
import com.soulje.todoapp.R
import com.soulje.todoapp.adapter.TaskListAdapter
import com.soulje.todoapp.databinding.MainFragmentBinding
import com.soulje.todoapp.model.Task

class MainFragment : Fragment() {

    private lateinit var binding : MainFragmentBinding
    private val adapter = TaskListAdapter()

    private val tasksList = mutableListOf<Task>(Task("Задание"),Task("Задание"),Task("Задание"),Task("Задание"),Task("Задание"))

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
        adapter.setTasks(tasksList)
        initRecyclerView()
        super.onViewCreated(view, savedInstanceState)
    }

    private fun initRecyclerView() = with(binding) {
        tasksList.setHasFixedSize(true)
        tasksList.layoutManager = LinearLayoutManager(context)
        tasksList.adapter = adapter
    }

    companion object {
        fun newInstance() = MainFragment()
    }

}