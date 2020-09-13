package com.example.todo.database;

import android.content.Context;

import androidx.room.Room;

public class TaskRepository {

    private static TaskDatabase taskDatabase = null;

    private TaskRepository(){};

    public static void makeDatabase(Context context) {
        if (taskDatabase == null) {
            taskDatabase = Room.databaseBuilder(context, TaskDatabase.class, "taskDatabase")
                    .allowMainThreadQueries()
                    .build();
        }
    }
    public static TaskDatabase getDatabase() {
        return taskDatabase;
    }
}
