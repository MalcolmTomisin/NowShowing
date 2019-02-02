package com.skillslevel.nowshowing;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class DetailActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail);

        Intent intent = getIntent();
        String title = intent.getStringExtra(MainActivity.mTitle);
        String mVote = intent.getStringExtra(MainActivity.mVote);
        String posterPath = intent.getStringExtra(MainActivity.posterPath);
        String overView = intent.getStringExtra(MainActivity.mOverview);
        String mDate = intent.getStringExtra(MainActivity.mRelease);
        String mAdult = intent.getStringExtra(MainActivity.mAdult);

        TextView textView1 = findViewById(R.id.text_title);
        TextView textView2 = findViewById(R.id.textRating);
        TextView textView3 = findViewById(R.id.text_adult);
        TextView textView4 = findViewById(R.id.text_overview);
        TextView textView5 = findViewById(R.id.text_date);
        ImageView imageView = findViewById(R.id.imageDetail);

        textView1.setText(title);
        textView2.setText(mVote);
        textView3.setText(mAdult);
        textView4.setText(overView);
        textView5.setText(mDate);

        Picasso.with(this).load(posterPath).error(R.drawable.stacked_green).fit().centerInside().into(imageView);

    }
}
