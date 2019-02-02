package com.skillslevel.nowshowing;

import android.content.Intent;
import android.content.res.Resources;
import android.os.Build;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.DividerItemDecoration;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.android.volley.Request;
import com.android.volley.RequestQueue;
import com.android.volley.Response;
import com.android.volley.VolleyError;
import com.android.volley.toolbox.JsonObjectRequest;
import com.android.volley.toolbox.Volley;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.Locale;

public class MainActivity extends AppCompatActivity implements RecyclerViewAdapter.OnItemClickListener{
    private RecyclerViewAdapter recyclerViewAdapter;
    private RecyclerView recyclerView;
    private ArrayList<Movie> movies;
    private RequestQueue requestQueue;
    private static int pageKey = 1;

    public static final String mTitle = "mTitle";
    public static final String mVote = "mVote";
    public static final String posterPath = "posterPath";
    public static final String mOverview = "mOverview";
    public static final String mRelease = "mRelease";
    public static final String mAdult = "mAdult";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        recyclerView = findViewById(R.id.recyclerView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

//        DividerItemDecoration dividerItemDecoration = new DividerItemDecoration(this, DividerItemDecoration.HORIZONTAL);
//        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
//            dividerItemDecoration.setDrawable(MainActivity.this.getResources().getDrawable(R.drawable.ic_hori_line, getResources().newTheme()));
//        }
//        recyclerView.addItemDecoration(dividerItemDecoration);

        movies = new ArrayList<>();

        requestQueue = Volley.newRequestQueue(this);
        parseJsonAPI();
    }

    private void parseJsonAPI() {
        String api_key = "d1b35a49eae5d1ecd6e398466afe0f74";
        final String URL = "https://api.themoviedb.org/3/movie/now_playing?api_key=" + api_key +  "&language=en-US&page=" + pageKey;
                final String imgUrl = "https://api.themoviedb.org";
//                final String imgUrl = "https://cdn.pixabay.com/photo/2017/09/05/22/35/goats-2719445__340.jpg";
        JsonObjectRequest jsonObjectRequest = new JsonObjectRequest(Request.Method.GET, URL, null, new Response.Listener<JSONObject>() {
            @Override
            public void onResponse(JSONObject response) {
                try {
                    JSONArray jsonArray = response.getJSONArray("results");
                    for (int i = 0; i < jsonArray.length(); i++){
                        JSONObject jsonObject = jsonArray.getJSONObject(i);

                        String title = jsonObject.getString("title");
                        Double vote_average = jsonObject.getDouble("vote_average");
                        String poster_path = imgUrl + jsonObject.getString("poster_path");
                        String backdrop_path = imgUrl + jsonObject.getString("backdrop_path");
                        String overview = jsonObject.getString("overview");
                        String release_date = jsonObject.getString("release_date");
                        String adult = Boolean.toString(jsonObject.getBoolean("adult"));

                        movies.add(new Movie(title, vote_average,poster_path,backdrop_path,overview,
                                release_date,adult));
                    }
                    RecyclerViewAdapter recyclerViewAdapter = new RecyclerViewAdapter(movies, MainActivity.this);
                    recyclerView.setAdapter(recyclerViewAdapter);
                    recyclerViewAdapter.setOnItemClickListener(MainActivity.this);
                } catch (JSONException e) {
                    e.printStackTrace();
                }
            }
        }, new Response.ErrorListener() {
            @Override
            public void onErrorResponse(VolleyError error) {
                error.printStackTrace();
            }
        });
        requestQueue.add(jsonObjectRequest);
    }

    @Override
    public void onItemClick(int position) {
        Intent mIntent = new Intent(this, DetailActivity.class);
        Movie movie = movies.get(position);

        mIntent.putExtra(mTitle, movie.getTitle());
        mIntent.putExtra(mVote, String.format(Locale.getDefault(),"%.1f", movie.getVote_average()));
        mIntent.putExtra(posterPath, movie.getPoster_path());
        mIntent.putExtra(mOverview, movie.getOverview());
        mIntent.putExtra(mRelease, movie.getRelease_date());
        mIntent.putExtra(mAdult, movie.getAdult());

        startActivity(mIntent);
    }
}
