package com.example.jdagnogo.alertlebonsoinappart.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.activities.ResultActivity;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;

/**
 * Created by Jeff on 19/06/2017.
 */

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.MyViewHolder> {
private List<Search>data;
    private Activity activity;
    public static final SimpleDateFormat hourFormatter = new SimpleDateFormat("HH:mm", Locale.FRANCE);
    @Override
    public ResearchAdapter.MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.research_adapter_list_row, parent, false);

        return new ResearchAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(ResearchAdapter.MyViewHolder holder, final int position) {
        holder.title.setText(data.get(position).getTitle());
        String dateString = String.format("Dernière maj : %s",
                hourFormatter.format(data.get(position).getMajDate()));
        holder.date.setText(dateString);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ResultActivity.class);
                Bundle args = new Bundle();
                args.putParcelable(NEW_RESEARCH, data.get(position).getRequestItemsRealm().getRequestItem());
                intent.putExtras(args);
                activity.startActivity(intent);
            }
        });

    }

    public ResearchAdapter(Activity activity) {
        this.activity = activity;
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
        public CardView cardView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            cardView = (CardView) view.findViewById(R.id.card);

        }
    }
}
