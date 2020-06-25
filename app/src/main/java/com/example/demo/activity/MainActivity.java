package com.example.demo.activity;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.util.Log;
import android.view.View;

import com.example.demo.R;
import com.example.demo.adapter.TodoItemAdapter;
import com.example.demo.databinding.ActivityMainBinding;
import com.example.demo.models.entity.ToDo;
import com.example.demo.viewModel.ToDoViewModel;

import java.util.ArrayList;
import java.util.List;

public class MainActivity extends AppCompatActivity {


    private ToDo updatetoDo;
    private TodoItemAdapter itemAdapter;
    private static final String TAG = "todo";
    private ToDoViewModel viewModel;
    private RecyclerView recyclerView;
    ActivityMainBinding binding;
    public static final int REQUEST_CODE = 1;
    private List<ToDo> itemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        binding.setActivity(this);

        binding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        binding.recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        binding.recyclerView.setHasFixedSize(true);
        itemAdapter = new TodoItemAdapter();
        binding.recyclerView.setAdapter(itemAdapter);
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        viewModel.getAllItems().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDoEntities) {
                itemAdapter.setTodo(toDoEntities);
                itemlist =toDoEntities;
            }
        });

        itemAdapter.setOnItemClickListener(new TodoItemAdapter.OnItemClickListerner() {
            @Override
            public void OnItemClick(ToDo toDos) {
                if (toDos.getPriority() == 0) {
                    updatetoDo = new ToDo(toDos.getTitle(), toDos.getDescription(), 1);
                } else {
                    updatetoDo = new ToDo(toDos.getTitle(), toDos.getDescription(), 0);
                }
                updatetoDo.setId(toDos.getId());
                viewModel.update(updatetoDo);
            }
        });
        binding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.d(TAG, "afterTextChanged: " + s.toString());
                filter(s.toString());
            }
        });
    }

    public void filter(String text) {
        List<ToDo> temp = new ArrayList<>();
        for (ToDo item : itemlist) {
            Log.d(TAG, "filter: 1 " + item);
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                Log.d(TAG, "filter: " + item.getTitle());
                temp.add(item);
            }
        }
        itemAdapter.updateList(temp);
    }
}
