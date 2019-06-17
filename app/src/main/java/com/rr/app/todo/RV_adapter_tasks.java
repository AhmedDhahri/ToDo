package com.rr.app.todo;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class RV_adapter_tasks extends RecyclerView.Adapter<RV_adapter_tasks.viewHolder> {
    ArrayList<Task_object> list;

    RV_adapter_tasks(ArrayList<Task_object> list){
        this.list = list;
    }

    @NonNull
    @Override
    public viewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.task_item,viewGroup,false);
        viewHolder vh = new viewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(@NonNull viewHolder v, final int i) {
        Task_object t = list.get(i);
        v.name.setText(t.name);
        v.date.setText(t.year+"-"+t.month+"-"+t.day);
        v.from.setText(t.hourf+":"+t.minf);
        v.to.setText(t.hourt+":"+t.mint);

        v.remove.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                Task_Provider.task.remove(i);
                Tasks.rv_adapter.notifyItemRemoved(i);
                Tasks.rv_adapter.notifyDataSetChanged();
                Tasks.tp.save_tasks(Task_Provider.task);
            }
        });
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public static class viewHolder extends RecyclerView.ViewHolder{
        TextView name;
        TextView date;
        TextView from;
        TextView to;
        ImageView remove;
        ImageView set;

        public viewHolder(@NonNull View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_t);
            date = itemView.findViewById(R.id.date_t);
            from = itemView.findViewById(R.id.from_t);
            to = itemView.findViewById(R.id.to_t);

            remove = itemView.findViewById(R.id.suppr);
            set = itemView.findViewById(R.id.set);
        }
    }
}
