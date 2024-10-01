package com.example.myapplication

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import android.widget.CheckBox
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView


class TaskAdapter (var ds:MutableList<Outdata>):RecyclerView.Adapter<TaskAdapter.TaskViewHolder>(){


    inner class  TaskViewHolder(itemView:View):RecyclerView.ViewHolder(itemView){
        val taskName: TextView = itemView.findViewById(R.id.tvTaskName)
        val checkbox: CheckBox = itemView.findViewById(R.id.cbTaskStatus)
        val deleteButton: Button = itemView.findViewById(R.id.btnDelete)


    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): TaskViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item_task,parent,false)
        return TaskViewHolder(view)
    }

    override fun onBindViewHolder(holder: TaskViewHolder, position: Int) {
            holder.itemView.apply {
                holder.taskName.text = ds[position].name
                holder.checkbox.isChecked=ds[position].isCompleted
            }
        holder.checkbox.setOnCheckedChangeListener { _, isChecked ->
            ds[position].isCompleted = isChecked
        }
        holder.deleteButton.setOnClickListener {
            removeItem(position)
        }
    }

    override fun getItemCount(): Int {
        return ds.size
    }
    fun removeItem(position: Int) {
        if (position >= 0 && position < ds.size) {
            ds.removeAt(position) // Xóa mục ở vị trí đã cho
            notifyItemRemoved(position) // Thông báo cho RecyclerView rằng một mục đã bị xóa
            notifyItemRangeChanged(position, ds.size) // Cập nhật lại các mục còn lại
        }
    }

}