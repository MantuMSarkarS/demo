package com.example.demo.models.database;

import android.content.Context;
import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;
import com.example.demo.models.DAO.ToDoDAO;
import com.example.demo.models.entity.ToDo;

@Database(entities = {ToDo.class},version = 1)   //if we have morethan 1 entity,we can use as {todoentity.class,other.class}
public abstract class ToDoDatabase extends RoomDatabase {

    private static ToDoDatabase instance; // create database instance to make this class as singleton class

    public abstract ToDoDAO toDoDAO();//create an abstract method

    public static synchronized ToDoDatabase getInstance(Context context){
        if(instance==null){
            instance= Room.databaseBuilder(context.getApplicationContext(),
                    ToDoDatabase.class,"to_do_database").fallbackToDestructiveMigration().build();
        }
        return instance;
    };
}
