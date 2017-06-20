package com.example.jdagnogo.alertlebonsoinappart.adapter;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;

import java.util.List;

/**
 * Created by Jeff on 19/06/2017.
 */

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.MyViewHolder> {
private List<Search>data;
    @Override
    public ResearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.research_adapter_list_row, parent, false);

        return new ResearchAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResearchAdapter.MyViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.date.setText(data.get(position).getMajDate().toString());

    }

    public void setData(List<Search> searches) {
        data = searches;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }
    public class MyViewHolder extends RecyclerView.ViewHolder {

        public TextView title, date;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);

        }
    }
}
