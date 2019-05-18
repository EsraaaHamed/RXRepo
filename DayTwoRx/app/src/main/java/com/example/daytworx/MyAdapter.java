package com.example.daytworx;

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
    public MyAdapter(List<String> firstObserberData, List<String> secondObserverData)
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
        myViewHolder.numText.setText(firstObserberData.get(i));
        myViewHolder.integerText.setText(secondObserverData.get(i));

    }

    @Override
    public int getItemCount() {
        return firstObserberData.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
            TextView numText;
            TextView integerText;
        public MyViewHolder(@NonNull View itemView) {
            super(itemView);
            numText = itemView.findViewById(R.id.name);
            integerText =itemView.findViewById(R.id.num);

        }
    }
}
