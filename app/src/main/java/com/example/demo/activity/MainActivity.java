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


    private ToDo mUpdatetoDo;
    private TodoItemAdapter mItemAdapter;
    private ToDoViewModel mViewModel;
    ActivityMainBinding mBinding;
    public static final int REQUEST_CODE = 1;
    private List<ToDo> mItemlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding = DataBindingUtil.setContentView(MainActivity.this, R.layout.activity_main);
        mBinding.setActivity(this);

        mBinding.add.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, AddToDoActivity.class);
                startActivityForResult(intent, REQUEST_CODE);
            }
        });

        mBinding.recyclerView.setLayoutManager(new LinearLayoutManager(getBaseContext(), LinearLayoutManager.VERTICAL, false));
        mBinding.recyclerView.setHasFixedSize(true);
        mItemAdapter = new TodoItemAdapter();
        mBinding.recyclerView.setAdapter(mItemAdapter);
        mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        mViewModel.getAllItems().observe(this, new Observer<List<ToDo>>() {
            @Override
            public void onChanged(List<ToDo> toDoEntities) {
                mItemAdapter.setTodo(toDoEntities);
                mItemlist =toDoEntities;
            }
        });

        mItemAdapter.setOnItemClickListener(new TodoItemAdapter.OnItemClickListerner() {
            @Override
            public void OnItemClick(ToDo toDos) {
                if (toDos.getPriority() == 0) {
                    mUpdatetoDo = new ToDo(toDos.getTitle(), toDos.getDescription(), 1);
                } else {
                    mUpdatetoDo = new ToDo(toDos.getTitle(), toDos.getDescription(), 0);
                }
                mUpdatetoDo.setId(toDos.getId());
                mViewModel.update(mUpdatetoDo);
            }
        });
        mBinding.search.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {

            }

            @Override
            public void afterTextChanged(Editable s) {
                filter(s.toString());
            }
        });
    }

    public void filter(String text) {
        List<ToDo> temp = new ArrayList<>();
        for (ToDo item : mItemlist) {
            if (item.getTitle().toLowerCase().contains(text.toLowerCase())) {
                temp.add(item);
            }
        }
        mItemAdapter.updateList(temp);
    }
}
