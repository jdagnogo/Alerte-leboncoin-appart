package com.example.jdagnogo.alertlebonsoinappart.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.activities.NewSearchActivity;
import com.example.jdagnogo.alertlebonsoinappart.activities.ResultActivity;
import com.example.jdagnogo.alertlebonsoinappart.models.Search;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.NbRoomSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.RentSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.models.swipeItem.SurfaceSwipeItem;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.DeleteSearchBus;
import com.example.jdagnogo.alertlebonsoinappart.services.eventbus.GlobalBus;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

import static com.example.jdagnogo.alertlebonsoinappart.utils.Constants.NEW_RESEARCH;

/**
 * Created by Jeff on 19/06/2017.
 */

public class ResearchAdapter extends RecyclerView.Adapter<ResearchAdapter.MyViewHolder> {
    private List<Search> data;
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
        if (data.get(position).getTitle().isEmpty()) {
            holder.title.setText("Aucun titre");
        } else
            holder.title.setText(data.get(position).getTitle());
        String dateString = String.format("Dernière maj : %s",
                hourFormatter.format(data.get(position).getMajDate()));
        holder.date.setText(dateString);
        holder.cardView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(activity, ResultActivity.class);
                Bundle args = new Bundle();
                args.putParcelable(NEW_RESEARCH, data.get(position).getRequestItems());
                intent.putExtras(args);
                activity.startActivity(intent);
            }
        });
        RentSwipeItem rentSwipeItem = new RentSwipeItem();
        holder.rentMin.setText(data.get(position).getRequestItems().getRent().getPositionMin());
        holder.rentMax.setText(data.get(position).getRequestItems().getRent().getPositionMax());

        SurfaceSwipeItem surfaceSwipeItem = new SurfaceSwipeItem();
        holder.surfaceMin.setText(surfaceSwipeItem.getDescription(Integer.valueOf(data.get(position).getRequestItems().getSurface().getPositionMin())));
        holder.surfaceMax.setText(surfaceSwipeItem.getDescription(Integer.valueOf(data.get(position).getRequestItems().getSurface().getPositionMax())));

        holder.nbRoomMin.setText(data.get(position).getRequestItems().getNbRoom().getPositionMin());
        holder.nbRoomMax.setText(data.get(position).getRequestItems().getNbRoom().getPositionMax());
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                DeleteSearchBus deleteSearchBus = new DeleteSearchBus(data.get(position));
                GlobalBus.getBus().post(deleteSearchBus);
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

        public TextView title, date, rentMin, rentMax, surfaceMin, surfaceMax, nbRoomMin, nbRoomMax;
        public CardView cardView;
        public ImageView delete;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            cardView = (CardView) view.findViewById(R.id.card);
            delete = (ImageView) view.findViewById(R.id.delete);
            rentMin = (TextView) view.findViewById(R.id.rent_min);
            rentMax = (TextView) view.findViewById(R.id.rent_max);
            surfaceMin = (TextView) view.findViewById(R.id.surface_min);
            surfaceMax = (TextView) view.findViewById(R.id.surface_max);
            nbRoomMax = (TextView) view.findViewById(R.id.nb_room_max);
            nbRoomMin = (TextView) view.findViewById(R.id.nb_room_min);

        }
    }
}
