package com.example.recyclerview

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import org.w3c.dom.Text
import java.text.FieldPosition

class TodoViewModel:ViewModel(){
    private val _todos = MutableLiveData<ArrayList<Todo>>()
    val todos : LiveData<ArrayList<Todo>>
    get() = _todos

    init {
        _todos.value = arrayListOf(
        )
    }
    fun addTodo(text: String){
        var newId = _todos.value!!.size+1
        _todos.value!!.add(Todo(3,text))
    }
    fun removeTodo(position:Int){
        _todos.value!!.removeAt(position)
    }
    fun updateTodo(pos:Int,text:String){
        _todos.value!![pos].task = text
    }
}