package com.soulje.todoapp.adapter

import android.graphics.Color
import android.os.Handler
import android.text.Spannable
import android.text.SpannableString
import android.text.style.StrikethroughSpan
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.Adapter
import com.soulje.todoapp.R
import com.soulje.todoapp.db.TaskEntity
import com.soulje.todoapp.viewModel.MainViewModel

class TaskListAdapter(viewModel: MainViewModel): Adapter<TaskListAdapter.ViewHolder>() {
    private lateinit var holder : ViewHolder
    private lateinit var tasks : MutableList<TaskEntity>
    private val viewModel1 = viewModel

    fun setTasks(tasks: MutableList<TaskEntity>){
        this.tasks = tasks
        Handler().post {
            notifyDataSetChanged()
        }
    }

    fun addTask(task : TaskEntity){
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

        fun bind(task : TaskEntity){

            //устанавливаем вьюшку в зависимости от поля done
            if(task.done){
                taskCheck.isChecked = true
                title.text = task.title
                title.setTextColor(Color.parseColor("#676666"))
                val string = SpannableString(title.text)
                string.setSpan(StrikethroughSpan(),0,string.length,0)
                title.text = string
            }
            else{
                taskCheck.isChecked = false
                title.setTextColor(Color.BLACK)
                title.text = task.title
            }

            //слушатель чекбокса
            taskCheck.setOnCheckedChangeListener { _, isChecked ->
                if(isChecked){
                    task.done = true
                    viewModel1.updateTask(task)
                    title.setTextColor(Color.parseColor("#676666"))
                    val string = SpannableString(title.text)
                    string.setSpan(StrikethroughSpan(),0,string.length,0)
                    title.text = string
                    Handler().post {
                        notifyItemMoved(adapterPosition,tasks.size-1)
                    }
                }else{
                    task.done = false
                    viewModel1.updateTask(task)
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