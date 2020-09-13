package com.example.todo;

import androidx.appcompat.app.AppCompatActivity;
import androidx.lifecycle.LiveData;
import androidx.lifecycle.Observer;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.widget.Button;
import android.widget.EditText;

import com.example.todo.database.Task;
import com.example.todo.database.TaskRepository;
import com.example.todo.recycler.TaskAdapter;

import java.util.List;

public class MainActivity extends AppCompatActivity {
    private RecyclerView mRecyclerView;
    private RecyclerView.Adapter mAdapter;
    private RecyclerView.LayoutManager mLayoutManager;

    private EditText mEditTextTask;
    private Button mButtonAdd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Create the database using the TaskRepository Helper class
        TaskRepository.makeDatabase(this);

        // Bind our views
        mEditTextTask = findViewById(R.id.editTestTask);
        mButtonAdd = findViewById(R.id.buttonAddTask);
        mRecyclerView = findViewById(R.id.recyclerView);

       // Create a ViewModel to observe our data
        final TaskAdapter adapter = new TaskAdapter();
        mLayoutManager = new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(adapter);


        TaskRepository.getDatabase().getTaskDao().getAllTasks().observe(this, new Observer<List<Task>>() {
            @Override
            public void onChanged(List<Task> tasks) {
                adapter.submitList(tasks);
            }
        });

        // Setup Button
        mButtonAdd.setOnClickListener(view -> {
            Task newTask = new Task();
            newTask.description = mEditTextTask.getText().toString();
            TaskRepository.getDatabase().getTaskDao().insert(newTask);
            mEditTextTask.setText("");
        });
    }
}