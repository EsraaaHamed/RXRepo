package com.example.rxrecycleview;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<String> firstObserberData;
    List<String> secondObserverData;
    public MyAdapter(List<String> firstObserberData,List<String> secondObserverData)
    {
        this.firstObserberData=firstObserberData;
        this.secondObserverData=secondObserverData;
    }
    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(viewGroup.getContext())
                .inflate(R.layout.card_layout, viewGroup   , false);
        MyViewHolder myViewHolder = new MyViewHolder(view);

        return myViewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder myViewHolder, int i) {
        myViewHolder.title.setText(firstObserberData.get(i));
        myViewHolder.desc.setText(secondObserverData.get(i));

    }

    @Override
    public int getItemCount() {
        return firstObserberData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        TextView title;
        TextView desc;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            title=itemView.findViewById(R.id.titleID);
            desc=itemView.findViewById(R.id.descriptionID);
        }
    }
}
