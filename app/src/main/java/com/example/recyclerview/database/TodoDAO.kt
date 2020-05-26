package com.example.recyclerview.database

import androidx.lifecycle.LiveData
import androidx.room.*

@Dao
interface TodoDAO {
    @Query("SELECT*FROM todo")
    fun loadTodos(): LiveData<List<Todo>>

    @Insert
    fun insert(todo:Todo)

    @Delete
    fun delete(todo:Todo)

    @Update
    fun update(todo:Todo)
}
