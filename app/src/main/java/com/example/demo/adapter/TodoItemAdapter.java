package com.example.demo.adapter;

import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.CheckBox;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.demo.R;
import com.example.demo.models.entity.ToDo;

import java.util.ArrayList;
import java.util.List;

public class TodoItemAdapter extends RecyclerView.Adapter<TodoItemAdapter.MyViewHolder> {

    private static final String TAG = "todo";
    private List<ToDo> todo=new ArrayList<>();
    OnItemClickListerner mListener;
    public interface OnItemClickListerner{
        void OnItemClick(ToDo toDos);
    }

    public void setOnItemClickListener(OnItemClickListerner onItemClickListerner) {
        this.mListener=onItemClickListerner;
    }
    public TodoItemAdapter(){}

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(parent.getContext()).inflate(R.layout.item_row,parent,false);
        MyViewHolder myViewHolder=new MyViewHolder(view);
        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, final int position) {
        final ToDo currentItem=todo.get(position);
        holder.title.setText(currentItem.getTitle());
        holder.description.setText(currentItem.getDescription());
        if(currentItem.getPriority()==0){
          holder.checkBox.setChecked(false);
        }else {
            holder.checkBox.setChecked(true);
        }
    }

    @Override
    public int getItemCount() {
        return todo.size();
    }

    public void setTodo(List<ToDo> toDoEntityList){
        this.todo=toDoEntityList;
        notifyDataSetChanged();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title,description;
        CheckBox checkBox;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.item);
            description=itemView.findViewById(R.id.descript);
            checkBox=itemView.findViewById(R.id.checkbox);
            checkBox.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    int pos=getAdapterPosition();
                    mListener.OnItemClick(todo.get(pos));
                }
            });
        }
    }
    public void updateList(List<ToDo> list){
        this.todo = list;
        notifyDataSetChanged();
    }
}
