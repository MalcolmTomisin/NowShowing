package com.skillslevel.nowshowing;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.ArrayList;
import java.util.Locale;

public class RecyclerViewAdapter extends RecyclerView.Adapter<RecyclerViewAdapter.ViewHolder> {
    private ArrayList<Movie> movieArrayList;
    private Context context;
    public OnItemClickListener mlistener;

    public interface OnItemClickListener{
        void onItemClick(int position);
    }

    public void setOnItemClickListener(OnItemClickListener listener){
        mlistener = listener;
    }

    public RecyclerViewAdapter(ArrayList<Movie> movieArrayList, Context context) {
        this.movieArrayList = movieArrayList;
        this.context = context;
    }


    @Override
    public ViewHolder onCreateViewHolder( ViewGroup viewGroup, int i) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_layout_2, viewGroup, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder( ViewHolder viewHolder, int i) {
        final Movie movie = movieArrayList.get(i);
        viewHolder.textView.setText(movie.getTitle());
        viewHolder.textView2.setText(String.format(Locale.getDefault(),"%.1f",movie.getVote_average()));
        String imageUrlPoster = movie.getPoster_path();
        Picasso.with(context).load(imageUrlPoster).error(R.drawable.ic_baseline_theaters_24px).fit().centerInside().into(viewHolder.imageView);

    }

    @Override
    public int getItemCount() {
        return movieArrayList.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener{
        TextView textView;
        TextView textView2;
        ImageView imageView, imageBase;


        public ViewHolder( View itemView) {
            super(itemView);
            textView = itemView.findViewById(R.id.textView3);
            textView2 = itemView.findViewById(R.id.textView4);
            imageView = itemView.findViewById(R.id.imageView2);
            imageBase = itemView.findViewById(R.id.imageView3);


            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View v) {
            if (mlistener != null){
                int position = getAdapterPosition();
                if (position != RecyclerView.NO_POSITION){
                    mlistener.onItemClick(position);
                }
            }
        }
    }
}
