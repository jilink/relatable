package com.punchips.relatable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.List;
import java.util.Random;

public class Play extends AppCompatActivity {

    private TextView text;
    private String key;
    private Post post;

    private int browsed; // combien de post ont été parcourus ? (utilisé pour l'algorithme qui propose les posts)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        text = (TextView) findViewById(R.id.text);
        new FireBaseHelper().readPosts(new FireBaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                text.setText(posts.get(0).getText());
                key=keys.get(0);
                post=posts.get(0);
            }

            @Override
            public void DataIsInserted() {

            }

            @Override
            public void DataIsUpdated() {

            }

            @Override
            public void DataisDeleted() {

            }
        });

        Button yes =(Button) findViewById(R.id.yes); // this is relatable!
        yes.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("posts");

                DatabaseReference Id = myRef.child(key);
                Id.child("yes").setValue(post.getYes()+1);


            }
        });



    }

    public String browsingAlgorithme(List<Post> posts){

        Random r = new Random();
        int RandPost = r.nextInt(posts.size()); // on choisit un post aléatoire



        return posts.get(RandPost).getText();

    }


}


