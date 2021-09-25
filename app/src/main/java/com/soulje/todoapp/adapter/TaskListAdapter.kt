package com.soulje.todoapp.adapter

import android.graphics.Color
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.soulje.todoapp.R
import com.soulje.todoapp.model.Task

class TaskListAdapter(rv: RecyclerView): Adapter<TaskListAdapter.ViewHolder>() {
    private lateinit var holder : ViewHolder
    private lateinit var tasks : MutableList<Task>
    private var rc = rv

    fun setTasks(tasks: MutableList<Task>){
        this.tasks = tasks
        Handler().post {
            notifyDataSetChanged()
        }
    }

    fun addTask(task : Task){
        tasks.add(0,task)
        Handler().post {
            notifyItemInserted(0)
        }
    }

    fun removeTask(position: Int){
        tasks.removeAt(position)
        notifyItemRemoved(position)
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
        return tasks.size
    }


    inner class ViewHolder(itemView : View) : RecyclerView.ViewHolder(itemView){
        private var taskCheck : CheckBox = itemView.findViewById(R.id.toggle)
        private var title: TextView = itemView.findViewById(R.id.task_title)

        fun bind(task : Task){
            taskCheck.isChecked = false
            title.text = task.title
            taskCheck.setOnCheckedChangeListener { toggle, isChecked ->
                if(isChecked){
                    title.setTextColor(Color.parseColor("#676666"))
                    val string = SpannableString(title.text)
                    string.setSpan(StrikethroughSpan(),0,string.length,0)
                    title.text = string
                    Handler().post {
                        notifyItemMoved(adapterPosition,tasks.size-1)
                    }
                    //removeTask(adapterPosition)
                }else{
                    title.setTextColor(Color.BLACK)
                    title.text = task.title
                    Handler().post {
                        notifyItemMoved(adapterPosition,0)
                    }
                }
            }
        }
    }
}