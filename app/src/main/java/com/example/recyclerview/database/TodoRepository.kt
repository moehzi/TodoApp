package com.example.recyclerview.database

class TodoRepository (private val TodoDAO :TodoDAO){
    val allTodos = TodoDAO.loadTodos()

    suspend fun insert(todo:Todo){
        TodoDAO.insert(todo)
    }
    suspend fun delete(todo:Todo){
        TodoDAO.delete(todo)
    }
    suspend fun update(todo:Todo){
        TodoDAO.update(todo)
    }
}