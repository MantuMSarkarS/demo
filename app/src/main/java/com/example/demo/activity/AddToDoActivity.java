package com.example.demo.activity;

import androidx.appcompat.app.AppCompatActivity;
import androidx.databinding.DataBindingUtil;
import androidx.lifecycle.ViewModelProviders;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;

import com.example.demo.R;
import com.example.demo.databinding.ActivityAddToDoBinding;
import com.example.demo.models.entity.ToDo;
import com.example.demo.viewModel.ToDoViewModel;

public class AddToDoActivity extends AppCompatActivity {

    private ToDoViewModel viewModel;
    private static final String TAG = "todo";
    ActivityAddToDoBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding= DataBindingUtil.setContentView(AddToDoActivity.this,R.layout.activity_add_to_do);
        binding.setActivity(this);
        binding.cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddToDoActivity.this,MainActivity.class));
            }
        });
        viewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        binding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                //saveToDoItem();
                ToDo updatetoDo=new ToDo(binding.title.getText().toString(),binding.desc.getText().toString(),0);
                viewModel.insert(updatetoDo);
                finish();
            }
        });

    }

    private void saveToDoItem() {
        String toDoItemTitle=binding.title.getText().toString();
        String toDoItemDesc=binding.desc.getText().toString();
        if(toDoItemTitle.trim().isEmpty()){
            binding.title.setError("Give an item name");
            return;
        }else if(toDoItemDesc.trim().isEmpty()){
            binding.desc.setError("Give a description");
            return;
        }
        Log.d(TAG, "add : "+toDoItemTitle+" "+toDoItemDesc+" ");
        Intent data=new Intent();
        data.putExtra("title",toDoItemTitle);
        data.putExtra("desc",toDoItemDesc);
        setResult(RESULT_OK,data);
        finish();
    }
}
