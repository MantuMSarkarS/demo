package com.example.demo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.demo.models.entity.ToDo;
import com.example.demo.models.repository.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ToDoRepository mRepository;
    private LiveData<List<ToDo>> mLiveData;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        mRepository=new ToDoRepository(application);
        mLiveData=mRepository.getAllItems();
    }
    public void insert(ToDo toDoEntity){
        mRepository.insert(toDoEntity);
    }
    public void update(ToDo toDoEntity){
        mRepository.update(toDoEntity);
    }
    public LiveData<List<ToDo>> getAllItems(){
        return mLiveData;
    }
}
