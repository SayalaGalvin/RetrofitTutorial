package com.example.retrofittutorial;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {

    TextView outputView;
    EditText inputText;
    Button searchByID, searchByTitle, viewAll, insertAll, updatePost, deletePost;

    APIInterface apiInterface;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //access the layout components
        searchByID = findViewById(R.id.searchByID);
        searchByTitle = findViewById(R.id.searchByTitle);
        viewAll = findViewById(R.id.viewAll);
        insertAll = findViewById(R.id.postAll);
        updatePost = findViewById(R.id.updatePost);
        deletePost = findViewById(R.id.deletePost);

        outputView = findViewById(R.id.outputText);
        inputText = findViewById(R.id.inputText);


        //view All
        viewAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<Post>> listCall = apiInterface.getPosts();
                listCall.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if(!response.isSuccessful()){
                            outputView.setText("404");
                        }
                        else{
                            List<Post> posts = response.body();
                            String content = "";
                            for (Post post: posts){
                                content += post.getId()+" "+post.getTitle()+"\n";
                                outputView.setText(content);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        outputView.setText(t.toString());
                    }
                });
            }
        });

        //search by ID
        searchByID.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<Post> call = apiInterface.getPostByID(Integer.parseInt(inputText.getText().toString()));
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(!response.isSuccessful()){
                            outputView.setText("404");
                        }
                        else{
                            String content = response.body().getId() + " " + response.body().getTitle();
                            outputView.setText(content);
                        }
                    }
                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        outputView.setText(t.toString());
                    }
                });
            }
        });

        //search by Title
        searchByTitle.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apiInterface = APIClient.getClient().create(APIInterface.class);
                Call<List<Post>> listCall = apiInterface.getPostsByTitle(inputText.getText().toString());
                listCall.enqueue(new Callback<List<Post>>() {
                    @Override
                    public void onResponse(Call<List<Post>> call, Response<List<Post>> response) {
                        if(!response.isSuccessful()){
                            outputView.setText("404");
                        }
                        else{
                            List<Post> posts = response.body();
                            String content = "";
                            for (Post post: posts){
                                content += post.getId()+" "+post.getTitle()+"\n";
                                outputView.setText(content);
                            }
                        }
                    }
                    @Override
                    public void onFailure(Call<List<Post>> call, Throwable t) {
                        outputView.setText(t.toString());
                    }
                });
            }
        });

        //post data set
        insertAll.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post("Today is a rainy day");
                Call<Post> call = apiInterface.insertPost(post);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {
                        if(!response.isSuccessful()){
                            outputView.setText("404");
                        }
                        else {
                            outputView.setText(response.code()+" "+ response.body().getId()+" " +response.body().getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        outputView.setText(t.toString());
                    }
                });
            }
        });

        updatePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Post post = new Post("Happy Coding");
                Call<Post> call = apiInterface.updatePost(1, post);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                        if(!response.isSuccessful()){
                            outputView.setText(" ");
                        }
                        else {
                            outputView.setText(response.code()+" "+ response.body().getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        outputView.setText(t.toString());
                    }
                });
            }
        });
        deletePost.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Call<Post> call = apiInterface.deletePost(1);
                call.enqueue(new Callback<Post>() {
                    @Override
                    public void onResponse(Call<Post> call, Response<Post> response) {

                        if(!response.isSuccessful()){
                            outputView.setText(" ");
                        }
                        else {
                            outputView.setText(response.code()+" "+ response.body().getTitle());
                        }
                    }

                    @Override
                    public void onFailure(Call<Post> call, Throwable t) {
                        outputView.setText(t.toString());
                    }
                });
            }
        });
    }
}
