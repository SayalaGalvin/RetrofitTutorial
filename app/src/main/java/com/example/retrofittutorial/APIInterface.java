package com.example.retrofittutorial;

import java.util.List;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.DELETE;
import retrofit2.http.Field;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;

public interface APIInterface {
    //get by ID
     @GET("SayalaGalvin/RetrofitTutorial/posts/{id}")
     Call<Post> getPostByID(@Path("id") int id);

     //get by title
    @GET("SayalaGalvin/RetrofitTutorial/posts/")
    Call<List<Post>> getPostsByTitle(@Query("title") String title);

    //get all
     @GET("SayalaGalvin/RetrofitTutorial/posts")
     Call<List<Post>> getPosts();

     //insert Post
    @POST("SayalaGalvin/RetrofitTutorial/posts")
    Call<Post> insertPost(@Body Post post); // Field

    //update post
    @PUT("SayalaGalvin/RetrofitTutorial/posts/{id}")
    Call<Post> updatePost(@Path("id") int id, @Body Post post); //Patch

    //delete post
    @DELETE("SayalaGalvin/RetrofitTutorial/posts/{id}")
    Call<Post> deletePost(@Path("id") int id);
}
