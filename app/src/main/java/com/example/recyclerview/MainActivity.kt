package com.example.recyclerview

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    private lateinit var recyclerView: RecyclerView
    private lateinit var viewAdapter: MyAdapter
    private lateinit var viewManager: RecyclerView.LayoutManager
    private lateinit var viewModel: TodoViewModel

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        val binding: ActivityMainBinding =
            DataBindingUtil.setContentView(this, R.layout.activity_main)

        viewModel = ViewModelProviders.of(this).get(TodoViewModel::class.java)

        viewManager = LinearLayoutManager(this)
        viewAdapter = MyAdapter(viewModel)

        recyclerView = binding.myRecyclerView
        recyclerView.apply {

            layoutManager = viewManager


            adapter = viewAdapter
        }

        //Add Data
        binding.btnNew.setOnClickListener {
            viewModel.todos.value!!.add(Todo(1,binding.newText.text.toString()))
        }
    }
}
