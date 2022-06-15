package com.example.retrofitlibapp;
import retrofit2.Call;
import retrofit2.http.GET;
import java.util.List;

public interface MyApi {
    @GET("posts")
    Call<List<Modal>> getModels();
}
