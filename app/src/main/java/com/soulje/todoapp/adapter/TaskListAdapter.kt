package com.soulje.todoapp.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.soulje.todoapp.R
import com.soulje.todoapp.model.Task

class TaskListAdapter(): Adapter<TaskListAdapter.ViewHolder>() {
    private lateinit var holder : ViewHolder
    private lateinit var tasks : MutableList<Task>

    fun setTasks(tasks: MutableList<Task>){
        this.tasks = tasks
        notifyDataSetChanged()
    }


    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(parent.context).inflate(R.layout.task_layout,parent,false)
        holder = ViewHolder(v)
        return holder
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(tasks[position])
    }

    override fun getItemCount(): Int {
        return 5
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var title: TextView = itemView.findViewById(R.id.task_title)

        fun bind(task : Task){
            title.text = task.title
        }
    }
}