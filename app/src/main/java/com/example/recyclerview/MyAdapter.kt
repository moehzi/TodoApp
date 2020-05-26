package com.example.recyclerview

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.databinding.ListItemBinding

class MyAdapter(private val viewModel : TodoViewModel):
    RecyclerView.Adapter<MyAdapter.MyViewHolder>() {




    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.todoText.text = viewModel.todos.value!![holder.adapterPosition].task
        //Menghapus Data
        holder.delBtn.setOnClickListener {
            viewModel.removeTodo(holder.adapterPosition)
            notifyDataSetChanged()
            notifyItemRangeChanged(position,viewModel.todos.value!!.size)
        }
        //Mengedit data
        holder.editBtn.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_item,null)

            //Mengambil data sebelumnya
            val prevText = viewModel.todos.value!![holder.adapterPosition].task
             val editText =view.findViewById<TextView>(R.id.editText)
            editText.text = prevText

            //Dialog
            var alertDialog = AlertDialog.Builder(context)
            alertDialog.setTitle("Edit Item")
                .setView(view)
                .setPositiveButton("Update",DialogInterface.OnClickListener {
                        dialog, id ->
                    //edit
                    val editedText = editText.text.toString()
                    viewModel.updateTodo(holder.adapterPosition,editedText)
                    holder.todoText.text = editedText
                    notifyDataSetChanged()
                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, id ->
                })

            alertDialog.create().show()
        }

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = viewModel.todos.value!!.size

    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val todoText = binding.todoText
        val delBtn = binding.btnDelete
        val editBtn = binding.btnEdit
    }
}