package com.example.cory.feedthekitty;

import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.cory.feedthekitty.models.Event;

public class EventViewHolder extends RecyclerView.ViewHolder {

    public TextView titleView;
    public TextView authorView;
    public ImageView starView;
    public TextView numStarsView;
    public TextView bodyView;
//    public ImageView owner_photo;

    public EventViewHolder(View itemView) {
        super(itemView);

        titleView = itemView.findViewById(R.id.event_name);
        authorView = itemView.findViewById(R.id.event_owner);
        starView = itemView.findViewById(R.id.star);
        numStarsView = itemView.findViewById(R.id.post_num_stars);
        bodyView = itemView.findViewById(R.id.event_body);
//        owner_photo = itemView.findViewById(R.id.owner_photo);
    }

    public void bindToEvent(Event event) {
        titleView.setText(event.name);
        authorView.setText(event.owner_name);
        numStarsView.setText(String.valueOf(5));

//        bodyView.setText(post.body);

//        starView.setOnClickListener(starClickListener);
    }
}
