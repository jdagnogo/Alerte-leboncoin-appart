package com.example.jdagnogo.alertlebonsoinappart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.*;

import java.util.List;

/**
 * Created by Jeff on 10/06/2017.
 */

public class ResultResearchAppartAdapter extends RecyclerView.Adapter<ResultResearchAppartAdapter.MyViewHolder> {
    private List<Appart> data;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_research_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.price.setText(data.get(position).getPrice());
        holder.date.setText(data.get(position).getDate());
    }

    public void setData(List<Appart> apparts) {
        data = apparts;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, price;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            price = (TextView) view.findViewById(R.id.price);

        }
    }
}
