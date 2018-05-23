package com.xema.shopmanager.data;

import android.content.Context;

import com.xema.shopmanager.model.Posts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class PostsRepository {
    private Context context;
    private RetroService retroService;
    private RestClient<RetroService> restClient;

    public PostsRepository(Context context)
    {
        this.context = context;
    }

    private void Connect()
    {
        restClient = new RestClient<>();
        retroService = restClient.getClient(RetroService.class);
    }

    private ArrayList<Posts> getPosts()
    {
        Call<ArrayList<Posts>> call = retroService.getPosts(10,4);

        call.enqueue(new Callback<ArrayList<Posts>>() {
            @Override
            public void onResponse(Call<ArrayList<Posts>> call, Response<ArrayList<Posts>> response) {

            }

            @Override
            public void onFailure(Call<ArrayList<Posts>> call, Throwable t) {

            }
        });
        return null;
    }



}
