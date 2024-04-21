package com.example.nastyalab3

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.AttributeSet
import android.util.DumpableContainer
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.example.nastyalab3.databinding.ActivityMainBinding
import com.example.nastyalab3.databinding.ActivitySecondBinding
import com.example.nastyalab3.model.AddWorkerViewModel
import com.example.nastyalab3.model.WorkerModel

class SecondActivity : AppCompatActivity() {
    lateinit var binding: ActivitySecondBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivitySecondBinding.inflate(layoutInflater)

        setContentView(binding.root)
        create()




    }


    private fun create(){
        val viewModel = ViewModelProvider(this).get(AddWorkerViewModel::class.java)

        binding.btnAdd.setOnClickListener() {
            val FirstName = binding.etFirstName.text.toString()
            val LastName = binding.etAddLastName.text.toString()
            viewModel.insert(WorkerModel(lastName = LastName, firstName = FirstName, id = 0))
            {}
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


        binding.btnBack.setOnClickListener(){
            val intent = Intent(this,MainActivity::class.java)
            startActivity(intent)
        }


    }
}