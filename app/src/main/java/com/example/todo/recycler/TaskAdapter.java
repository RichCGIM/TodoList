package com.example.todo.recycler;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.ListAdapter;
import androidx.recyclerview.widget.RecyclerView;

import com.example.todo.R;
import com.example.todo.database.Task;
import com.example.todo.database.TaskDatabase;
import com.example.todo.database.TaskRepository;

public class TaskAdapter extends ListAdapter<Task, TaskAdapter.TaskViewHolder> {

    // public static inner class
    public static class TaskViewHolder extends RecyclerView.ViewHolder {
        public TextView mTextViewTask;
        public Button mButtonDeleteTask;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);
            mTextViewTask = itemView.findViewById(R.id.textViewTask);
            mButtonDeleteTask = itemView.findViewById(R.id.buttonDeleteTask);
        }
    }

    public TaskAdapter() {
        super(Task.DIFF_CALLBACK);
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater
                .from(parent.getContext())
                .inflate(R.layout.view_holder_task, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        Task task = getItem(position);
        holder.mTextViewTask.setText(task.description);
        holder.mButtonDeleteTask.setOnClickListener(getDeleteOnClickListener(task));
    }

    private View.OnClickListener getDeleteOnClickListener(final Task task) {
        return view -> {
            TaskDatabase database = TaskRepository.getDatabase();
            database.getTaskDao().delete(task);
        };
    }
}
