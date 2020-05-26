package com.example.recyclerview

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import com.example.recyclerview.database.Todo
import com.example.recyclerview.database.TodoDAO
import com.example.recyclerview.database.TodoDatabase
import com.example.recyclerview.database.TodoRepository
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch


class TodoViewModel(application:Application):AndroidViewModel(application){
    //add repository
    private val repository: TodoRepository
    private val todoDao:TodoDAO

    private var _todos : LiveData<List<Todo>>
    val todos : LiveData<List<Todo>>
    get() = _todos

    private var vmJob = Job()
    private val UIScope = CoroutineScope(Dispatchers.IO + vmJob)

    init {
        todoDao = TodoDatabase.getInstance(application).todoDAO()
        repository = TodoRepository(todoDao)
        _todos = repository.allTodos
    }
    fun addTodo(text: String){
        UIScope.launch {
        repository.insert(Todo(0,text))
        }
    }
    fun removeTodo(todo: Todo){
        UIScope.launch {
            repository.delete(todo)
        }

    }
    fun updateTodo(pos:Int,text:String){
        UIScope.launch {
            repository.update(Todo(0,text))
        }
    }

    override fun onCleared() {
        super.onCleared()
        vmJob.cancel()
    }
}