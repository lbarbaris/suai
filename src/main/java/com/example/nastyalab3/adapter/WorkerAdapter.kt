package com.example.nastyalab3.adapter

import android.annotation.SuppressLint
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.recyclerview.widget.RecyclerView
import com.example.nastyalab3.R
import com.example.nastyalab3.model.WorkerModel


class WorkerAdapter(private val context: Context): RecyclerView.Adapter<WorkerAdapter.WorkerViewHolder>(){


    private var WorkerList = emptyList<WorkerModel>()




    class WorkerViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val text1: TextView
        val text2: TextView

        init {
            text1 = view.findViewById(R.id.tv_last_name)
            text2 = view.findViewById(R.id.tv_first_name)
        }

    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WorkerViewHolder {
        var view = LayoutInflater.from(parent.context).inflate(R.layout.item_worker_layout, parent, false)
        return WorkerViewHolder(view)
    }



    override fun onBindViewHolder(holder: WorkerViewHolder, position: Int) {

        holder.text1.text = WorkerList[position].lastName
        holder.text2.text= WorkerList[position].firstName
        holder.itemView.setOnClickListener{
            Toast.makeText(context,"You Liked ${holder.text1.text} ${holder.text2.text} ",Toast.LENGTH_SHORT).show()
        }

    }
    override fun getItemCount() = WorkerList.size

    @SuppressLint("NotifyDataSetChanged")
    fun setList(list: List<WorkerModel>){
        WorkerList = list
        notifyDataSetChanged()
    }


}

