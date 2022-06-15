package com.example.retrofitlibapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    TextView textView;
    String url = "https://jsonplaceholder.typicode.com/";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        textView = findViewById(R.id.tv);
        textView.setText("");
        //1: Ist we have to create a retrofit object creation
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(url)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        //2: Convert json data into modal class onject
        MyApi myApi = retrofit.create(MyApi.class);
        //3:create a Call of modal class and enqueue for processing
        Call<List<Modal>> call = myApi.getModels();
        call.enqueue(new Callback<List<Modal>>() {
            @Override
            public void onResponse(Call<List<Modal>> call, Response<List<Modal>> response) {
                List<Modal> modalDataList = response.body();
                for (int i = 0; i<modalDataList.size();i++){
                    textView.append("SL No: "+modalDataList.get(i).getId()+"\nTitle: "+modalDataList.get(i).getTitle()+"\n\n");
                }
            }

            @Override
            public void onFailure(Call<List<Modal>> call, Throwable t) {

            }
        });

    }
}