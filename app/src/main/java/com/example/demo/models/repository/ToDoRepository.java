package com.example.demo.models.repository;

import android.app.Application;
import android.os.AsyncTask;

import androidx.lifecycle.LiveData;

import com.example.demo.models.DAO.ToDoDAO;
import com.example.demo.models.database.ToDoDatabase;
import com.example.demo.models.entity.ToDo;

import java.util.List;

public class ToDoRepository {
    // repository class
    private ToDoDAO toDoDAO;
    private LiveData<List<ToDo>> allItems;

    public ToDoRepository(Application application) {
        ToDoDatabase toDoDatabase = ToDoDatabase.getInstance(application);
        toDoDAO = toDoDatabase.toDoDAO();
        allItems = toDoDAO.getAllItems();
    }

    public void insert(ToDo toDoEntity) {
        new InsertIntoAsynctask(toDoDAO).execute(toDoEntity);
    }
    public void update(ToDo toDoEntity) {
        new UpdateIntoAsynctask(toDoDAO).execute(toDoEntity);
    }

    public LiveData<List<ToDo>> getAllItems() {
        return allItems;
    }

    public static class InsertIntoAsynctask extends AsyncTask<ToDo, Void, Void> {

        private ToDoDAO toDoDao;

        private InsertIntoAsynctask(ToDoDAO toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDoEntities) {
            toDoDao.insert(toDoEntities[0]);
            return null;
        }
    }
    public static class UpdateIntoAsynctask extends AsyncTask<ToDo, Void, Void> {

        private ToDoDAO toDoDao;

        private UpdateIntoAsynctask(ToDoDAO toDoDao) {
            this.toDoDao = toDoDao;
        }

        @Override
        protected Void doInBackground(ToDo... toDoEntities) {
            toDoDao.update(toDoEntities[0]);
            return null;
        }
    }
}
