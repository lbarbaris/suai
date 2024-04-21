package com.example.nastyalab3

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.RecyclerView
import com.example.nastyalab3.adapter.WorkerAdapter
import com.example.nastyalab3.databinding.ActivityMainBinding
import com.example.nastyalab3.model.StartViewModel
import com.example.nastyalab3.model.WorkerModel

class MainActivity : AppCompatActivity() {
    lateinit var binding: ActivityMainBinding
    lateinit var adapter: WorkerAdapter
    lateinit var recyclerView: RecyclerView


    override fun onCreate(savedInstanceState: Bundle?) {
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        super.onCreate(savedInstanceState)
        create()
    }


    private fun create(){
        val viewModel = ViewModelProvider(this).get(StartViewModel::class.java)
        viewModel.initDataBase()
        recyclerView = binding.rvUser
        adapter = WorkerAdapter(this)
        recyclerView.adapter = adapter

        viewModel.getAllWorkers().observe(this) { ListWorkers ->
            ListWorkers.asReversed()
            adapter.setList(ListWorkers)

        }

        binding.btnNext.setOnClickListener() {
            val intent = Intent(this,SecondActivity::class.java)
            startActivity(intent)
        }
    }



}

