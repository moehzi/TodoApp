package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding
import java.util.concurrent.RecursiveAction

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: RecyclerView.Adapter<*>
    private lateinit var viewManager: RecyclerView.LayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)
        val todos = mutableListOf<String>("Makan","Bangun","Sikat gigi")

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(todos)

        //Add Data
        binding.btnNew.setOnClickListener {
            todos.add(binding.newText.text.toString())
        }
        recyclerView = binding.myRecyclerView
        recyclerView.apply {

            layoutManager = viewManager


            adapter = viewAdapter
        }
    }
}
