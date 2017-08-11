package com.example.jdagnogo.alertlebonsoinappart.adapter;

import android.app.Activity;
import android.content.Context;
import android.graphics.Bitmap;
import android.support.v4.view.PagerAdapter;
import android.support.v7.app.AlertDialog;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.resource.drawable.GlideDrawable;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.animation.GlideAnimation;
import com.bumptech.glide.request.target.SimpleTarget;
import com.bumptech.glide.request.target.Target;
import com.example.jdagnogo.alertlebonsoinappart.AlertLEboncoinApplication;
import com.example.jdagnogo.alertlebonsoinappart.R;
import com.example.jdagnogo.alertlebonsoinappart.models.Appart;

import java.util.List;

/**
 * Created by Jeff on 10/06/2017.
 */

public class ResultResearchAppartAdapter extends RecyclerView.Adapter<ResultResearchAppartAdapter.MyViewHolder> {
    private List<Appart> data;
    private Activity activity;

    public ResultResearchAppartAdapter(Activity activity) {
        this.activity = activity;
    }

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.result_research_list_row, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        holder.title.setText(data.get(position).getTitle());
        holder.price.setText(data.get(position).getPrice().substring(0, data.get(position).getPrice().length() - 5));
        holder.date.setText(data.get(position).getDate());
        Glide.with(AlertLEboncoinApplication.getContext())
                .load("http:" + data.get(position).getImage())
                .diskCacheStrategy(DiskCacheStrategy.RESULT)
                .listener(new RequestListener<String, GlideDrawable>() {
                    @Override
                    public boolean onException(Exception e, String model, Target<GlideDrawable> target, boolean isFirstResource) {
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(GlideDrawable resource, String model, Target<GlideDrawable> target, boolean isFromMemoryCache, boolean isFirstResource) {
                        return false;
                    }
                })
                .into(holder.imageView);
    }

    public void setData(List<Appart> appart) {
        data = appart;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder {
        public TextView title, date, price;
        public ImageView imageView;

        public MyViewHolder(View view) {
            super(view);
            title = (TextView) view.findViewById(R.id.title);
            date = (TextView) view.findViewById(R.id.date);
            price = (TextView) view.findViewById(R.id.price);
            imageView = (ImageView) view.findViewById(R.id.image);

            itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    int itemPosition = getLayoutPosition();
                    final AlertDialog.Builder dialogBuilder = new AlertDialog.Builder(activity);
                    LayoutInflater inflater = activity.getLayoutInflater();
                    View dialogView = inflater.inflate(R.layout.detail_appar_dialog, null);
                    TextView appartName = (TextView) dialogView.findViewById(R.id.appart_name);
                    appartName.setText(data.get(itemPosition).getTitle());
                    dialogBuilder.setView(dialogView);
                    // dialog fragment !
                    final AlertDialog alertDialog = dialogBuilder.create();
                    alertDialog.show();
                }
            });

        }
    }

}
