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
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }

        // Dữ liệu ban đầu
        var ds = mutableListOf<Outdata>()

        val adapter = TaskAdapter(ds)  // Khởi tạo adapter một lần
        val recyclerView = findViewById<RecyclerView>(R.id.recyclerview)

        recyclerView.adapter = adapter
        recyclerView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)

        val editText = findViewById<EditText>(R.id.editTextText)
        val button = findViewById<Button>(R.id.button)

        // Thêm công việc mới
        button.setOnClickListener {
            val taskName = editText.text.toString()
            if (taskName.isNotEmpty()) {
                val task = Outdata(taskName, false)
                ds.add(task)
                adapter.notifyDataSetChanged()  // Cập nhật RecyclerView
                editText.text.clear()  // Xóa nội dung EditText
            }
        }

        val radioGroup = findViewById<RadioGroup>(R.id.radioGroup)

        radioGroup.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.radioButton -> {
                    adapter.ds = ds
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
}
