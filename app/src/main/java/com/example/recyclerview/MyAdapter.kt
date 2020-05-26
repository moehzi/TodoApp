package com.example.recyclerview

import android.app.AlertDialog
import android.content.DialogInterface
import android.view.LayoutInflater
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.recyclerview.database.Todo
import com.example.recyclerview.databinding.ListItemBinding

class MyAdapter(private val viewModel : TodoViewModel):
    ListAdapter<Todo, MyAdapter.MyViewHolder>(TodoDiffCallBack()) {


    override fun onCreateViewHolder(parent: ViewGroup,
                                    viewType: Int): MyAdapter.MyViewHolder {
        // create a new view
        val inflater = LayoutInflater.from(parent.context)
        val binding = ListItemBinding.inflate(inflater)

        return MyViewHolder(binding)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.todoText.text = getItem(holder.adapterPosition).task
        //Menghapus Data
        holder.delBtn.setOnClickListener {
            viewModel.removeTodo(getItem(holder.adapterPosition))

        }
        //Mengedit data
        holder.editBtn.setOnClickListener {
            val context = holder.itemView.context
            val inflater = LayoutInflater.from(context)
            val view = inflater.inflate(R.layout.edit_item,null)

            //Mengambil data sebelumnya
            val prevText =getItem(holder.adapterPosition).task
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

                })
                .setNegativeButton("Cancel",DialogInterface.OnClickListener { dialog, id ->
                })

            alertDialog.create().show()
        }

    }


    class MyViewHolder(val binding: ListItemBinding) : RecyclerView.ViewHolder(binding.root){
        val todoText = binding.todoText
        val delBtn = binding.btnDelete
        val editBtn = binding.btnEdit
    }
    class TodoDiffCallBack:DiffUtil.ItemCallback<Todo>(){
        override fun areItemsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.id == newItem.id

        }

        override fun areContentsTheSame(oldItem: Todo, newItem: Todo): Boolean {
            return oldItem.equals(newItem)
        }
    }
}