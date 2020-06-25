package com.example.demo.models.DAO;

import androidx.lifecycle.LiveData;
import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;
import androidx.room.Update;

import com.example.demo.models.entity.ToDo;

import java.util.List;

@Dao // Data Access Object is use for access the database
public interface ToDoDAO {

    @Insert // it means room will automatically do the all necessay code for us.
    void insert(ToDo toDoEntity);

    @Update
    void update(ToDo toDo);

    @Query("SELECT * FROM to_do_item ORDER BY priority ASC") //room allow us to create query for our requirements
    LiveData<List<ToDo>> getAllItems(); //live data is an observable data holder, it observe changes and update the view when it active
}
