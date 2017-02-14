package com.elishapatterson.newpaperlocations.Adapter;

import android.graphics.Color;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.elishapatterson.newpaperlocations.R;
import com.elishapatterson.newpaperlocations.model.NewspaperLocationModel;

import java.util.ArrayList;


/**
 * Adapter for RecyclerView
 */
public class RecyclerAdapter extends RecyclerView.Adapter<RecyclerAdapter.ViewHolder> {

    private ArrayList<NewspaperLocationModel> mDataset;
    private static NewspaperCardViewClickListener newspaperCardViewClickListener;


    public static class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        // Components inside of a the CardView
        public CardView cardView;
        public TextView detailsTextView;
        public TextView styleTextView;
        public TextView latitudeTextView;
        public TextView longitudeTextView;



        public ViewHolder(View itemView) {
            super(itemView);


            // Initialized the view components inside the CardView
            detailsTextView = (TextView) itemView.findViewById(R.id.details_text);
            cardView = (CardView) itemView.findViewById(R.id.newspaper_card_view);
            styleTextView = (TextView) itemView.findViewById(R.id.style_text);
            latitudeTextView = (TextView) itemView.findViewById(R.id.latitude_text);
            longitudeTextView = (TextView) itemView.findViewById(R.id.longitude_text);

            itemView.setOnClickListener(this);

        }

        @Override
        public void onClick(View v) {
            newspaperCardViewClickListener.onItemClick(getAdapterPosition(), v);
        }
    }

    public void setOnItemClickListener(NewspaperCardViewClickListener onItemClickListener) {
        this.newspaperCardViewClickListener = onItemClickListener;
    }

    public RecyclerAdapter(ArrayList<NewspaperLocationModel> myDataset) {
        this.mDataset = myDataset;
    }

    @Override
    public RecyclerAdapter.ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_newspaper_location_model, parent, false);

        ViewHolder vh = new ViewHolder(v);
        return vh;
    }

    @Override
    public void onBindViewHolder(RecyclerAdapter.ViewHolder holder, int position) {

        NewspaperLocationModel singleRowNewspaperModel = mDataset.get(position);

        //CardView Fields

        //Set the Details in the TextView
        setTextViewText(holder.detailsTextView, singleRowNewspaperModel.getDetails());

        // Set the Style
        setTextViewText(holder.styleTextView, singleRowNewspaperModel.getStyle());

        // Set the Latitude
        setTextViewText(holder.latitudeTextView, String.valueOf(singleRowNewspaperModel.getLatitude()));

        // Set the Longitude
        setTextViewText(holder.longitudeTextView, String.valueOf(singleRowNewspaperModel.getLongitude()));

        //Set the background of cardview color based on newspaper_locations style value
        setCardViewBackGroundColor(holder, singleRowNewspaperModel.getStyle());

    }

    @Override
    public int getItemCount() {
        return mDataset.size();
    }


    /**
     * Set Cardview Background color based on Style String
     * @param v ViewHolder from onBindViewHolder in this class
     * @param style
     */
    private void setCardViewBackGroundColor( ViewHolder v, String style ) {
        int color = 0;
        Log.i("Newspaper", "style requested: " + style);
        if(style.equalsIgnoreCase("paperbox")) {
            color = Color.parseColor("#2ECC71"); // Green
        } else {
            color = Color.parseColor("#C0392B"); // Red
        }

        ((CardView) v.cardView).setCardBackgroundColor(color);


    }

    private void setTextViewText(TextView tv, String text) {
        tv.setText(text);
    }

    public interface NewspaperCardViewClickListener {
        public void onItemClick(int position, View v);
    }
}



