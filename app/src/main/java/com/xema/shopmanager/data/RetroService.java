package com.xema.shopmanager.data;

import com.xema.shopmanager.model.Posts;

import java.util.ArrayList;

import retrofit2.Call;
import retrofit2.http.POST;
import retrofit2.http.Query;

public interface RetroService {

    @POST("/get_posts/")
    Call<ArrayList<Posts>> getPosts(@Query("cnt_post") int cnt_post, @Query("last_post_id") int last_post_id);

   /* @GET("/users/{KEY}")
    Call<Test2> getRespos(@Path("KEY") String id);

    @GET("/All_Gift")
    Call<ArrayList<Gift>> getGift();



    //자신이 가지고 있는 혜택
    @GET("/postCardKey")
    Call<ArrayList<Gift>> getMyGift(@Query("card_key") String card_key);

    @GET("/user")
    Call<User> getUser();

    @GET("/findAll")
    Call<List<Test>> get();

    @POST("/registration")
    Call<Test> post(@Body Test test);

    *//*@GET("/users/{KEY}")
    Call<User> getUser(@Path("KEY") String key, @Path("id") String id);*//*

    @POST("/users/{KEY}")
    Call<User> postRespos(@Path("KEY") String id, @Body User user);

    @POST("/updateGift")
    Call<String> updateGift(@Body MyUser user);*/
}
