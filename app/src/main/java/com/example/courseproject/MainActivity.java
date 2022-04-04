package com.example.courseproject;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.os.Bundle;
import android.os.Handler;
import android.provider.ContactsContract;
import android.util.Log;
import android.view.View;
import android.widget.GridView;
import android.widget.ImageView;

import com.squareup.picasso.Picasso;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.BufferedInputStream;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.ProtocolException;
import java.net.URL;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    ArrayList<Movies> List = new ArrayList<>();
    public static JSONArray OurDataArray;
    GridView grid_view;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Intent receivedIntent = getIntent();
        new ouAsyncTask().execute();
        grid_view = findViewById(R.id.grid_view);


    }

    class ouAsyncTask extends AsyncTask<Void, Void, List<Movies>> {

        @Override
        protected java.util.List<Movies> doInBackground(Void... voids) {
            try {
                URL url = new URL("https://api.themoviedb.org/3/movie/popular?api_key=bd217557f305c54f2585b640242feddd&language=en-US&page=1");
                HttpURLConnection httpURLConnection = (HttpURLConnection) url.openConnection();
                httpURLConnection.setRequestMethod("GET");
                InputStream inputStream = new BufferedInputStream(httpURLConnection.getInputStream());
                String JsonString = convertFromInputToString(inputStream);
                Log.d("json text", JsonString);
                JSONObject parentJsonObject = new JSONObject(JsonString);
                JSONArray OurDataArray = parentJsonObject.getJSONArray("products");
                for (int i = 0; i < OurDataArray.length(); i++) {
                    JSONObject jsonobject = OurDataArray.getJSONObject(i);
                    String title = jsonobject.getString("title");
                    String vote_average = jsonobject.getString("vote_average");
                    String overview = jsonobject.getString("overview");
                    String release_date = jsonobject.getString("release_date");
                    String poster_path = jsonobject.getString("poster_path");
                    List.add(new Movies(title, release_date, vote_average, poster_path, overview));
                }

                return List;

            } catch (MalformedURLException e) {
                e.printStackTrace();
            } catch (ProtocolException e) {
                e.printStackTrace();
            } catch (IOException e) {
                e.printStackTrace();
            } catch (JSONException e) {
                e.printStackTrace();
            }

            return null;
        }

        @Override
        protected void onPostExecute(java.util.List<Movies> movies) {
            Adapter adapter = new Adapter(MainActivity.this, List);
            grid_view.setAdapter(adapter);

        }
    }

    public String convertFromInputToString(InputStream inputStream) {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(inputStream));
        StringBuilder stringBuilder = new StringBuilder();
        String Line;
        try {
            while ((Line = bufferedReader.readLine()) != null) {
                stringBuilder.append(Line).append("/n");

            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            try {
                inputStream.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        return stringBuilder.toString();
    }


}
