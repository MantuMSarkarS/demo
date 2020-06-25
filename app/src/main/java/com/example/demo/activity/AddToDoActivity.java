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

    private ToDoViewModel mViewModel;
    ActivityAddToDoBinding mBinding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        mBinding= DataBindingUtil.setContentView(AddToDoActivity.this,R.layout.activity_add_to_do);
        mBinding.setActivity(this);
        mBinding.cencel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(AddToDoActivity.this,MainActivity.class));
            }
        });
        mViewModel = ViewModelProviders.of(this).get(ToDoViewModel.class);
        mBinding.done.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ToDo updatetoDo=new ToDo(mBinding.title.getText().toString(),mBinding.desc.getText().toString(),0);
                mViewModel.insert(updatetoDo);
                finish();
            }
        });
    }
}
