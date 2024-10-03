package com.example.myapplication

import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.RadioGroup
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
class MainActivity : AppCompatActivity() {

    private lateinit var adapter: TaskAdapter
    private lateinit var ds: MutableList<Outdata>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)

        // Khởi tạo danh sách gốc
        ds = mutableListOf()
        adapter = TaskAdapter(ds) { task ->
            removeTask(task)

        }

        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)
        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val editText = findViewById<EditText>(R.id.editTextText)
        val button = findViewById<Button>(R.id.button)
        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)


        button.setOnClickListener {
            val taskName = editText.text.toString()
            if (taskName.isNotEmpty()) {
                val task = Outdata(taskName, false)
                ds.add(task)
                updateDisplayedTasks(radioGroup)
                editText.text.clear()
            }
        }

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            updateDisplayedTasks(radioGroup)
        }
    }

    private fun removeTask(task: Outdata) {
        ds.remove(task)
        adapter.ds.remove(task)
        adapter.notifyDataSetChanged()
    }

    private fun updateDisplayedTasks(radioGroup: RadioGroup) {
        when (radioGroup.checkedRadioButtonId) {
            R.id.radioButton -> {
                adapter.ds = ds.toMutableList()
            }
            R.id.radioButton2 -> {
                adapter.ds = ds.filter { !it.isCompleted }.toMutableList()
            }
            R.id.radioButton3 -> {
                adapter.ds = ds.filter { it.isCompleted }.toMutableList()
            }
        }
        adapter.notifyDataSetChanged()
    }
}
