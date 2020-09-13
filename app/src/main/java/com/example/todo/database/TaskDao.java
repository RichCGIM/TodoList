package com.example.todo.database;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Delete;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import java.util.List;

@Dao
public interface TaskDao {

    @Insert
    public void insert(Task... task);

    @Update
    public void update(Task task);

    @Delete
    public void delete(Task task);

    @Query("SELECT * FROM task")
    public LiveData<List<Task>> getAllTasks();

    @Query("SELECT * FROM task WHERE id = :id")
    public Task getTaskById(int id);
}
