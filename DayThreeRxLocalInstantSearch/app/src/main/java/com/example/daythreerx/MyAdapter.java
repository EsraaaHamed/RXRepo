package com.example.daythreerx;

import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class MyAdapter extends RecyclerView.Adapter<MyAdapter.MyViewHolder> {
    List<String> firstObserberData;
    public MyAdapter(List<String> firstObserberData)
    {
        this.firstObserberData=firstObserberData;
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


        }
    }
}
