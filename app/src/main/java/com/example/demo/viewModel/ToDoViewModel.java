package com.example.demo.viewModel;

import android.app.Application;

import androidx.annotation.NonNull;
import androidx.lifecycle.AndroidViewModel;
import androidx.lifecycle.LiveData;

import com.example.demo.models.entity.ToDo;
import com.example.demo.models.repository.ToDoRepository;

import java.util.List;

public class ToDoViewModel extends AndroidViewModel {

    private ToDoRepository repository;
    private LiveData<List<ToDo>> liveData;

    public ToDoViewModel(@NonNull Application application) {
        super(application);
        repository=new ToDoRepository(application);
        liveData=repository.getAllItems();
    }
    public void insert(ToDo toDoEntity){
        repository.insert(toDoEntity);
    }
    public void update(ToDo toDoEntity){
        repository.update(toDoEntity);
    }
    public LiveData<List<ToDo>> getAllItems(){
        return liveData;
    }
}
