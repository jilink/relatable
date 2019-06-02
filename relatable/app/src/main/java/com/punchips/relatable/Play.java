package com.punchips.relatable;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
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

    private List<Post> Posts;
    private List<String> Keys;
    private int browsed=0; // combien de post ont été parcourus ? (utilisé pour l'algorithme qui propose les posts)

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_play);
        text = (TextView) findViewById(R.id.text);
        new FireBaseHelper().readPosts(new FireBaseHelper.DataStatus() {
            @Override
            public void DataIsLoaded(List<Post> posts, List<String> keys) {
                Posts=posts;
                Keys=keys;
                browsingAlgorithme();
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

                Button next =(Button) findViewById(R.id.next);
                Button no =(Button) findViewById(R.id.no);
                Button yes =(Button) findViewById(R.id.yes);
                ProgressBar ratebar =(ProgressBar) findViewById(R.id.ratebar);
                next.setVisibility(View.VISIBLE);
                ratebar.setVisibility(View.VISIBLE);
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);

                // show rate

                TextView ratetext = (TextView) findViewById(R.id.ratetext);
                ratebar.setProgress(post.getRate());
                String Srate = ""+post.getRate() +"% of people ("+post.getYes()+") think this is relatable!";
                ratetext.setText(Srate);



            }
        });

        Button no =(Button) findViewById(R.id.no); // this is NOT relatable!
        no.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("posts");

                DatabaseReference Id = myRef.child(key);
                Id.child("no").setValue(post.getNo()+1);

                Button next =(Button) findViewById(R.id.next);
                Button no =(Button) findViewById(R.id.no);
                Button yes =(Button) findViewById(R.id.yes);
                ProgressBar ratebar =(ProgressBar) findViewById(R.id.ratebar);
                next.setVisibility(View.VISIBLE);
                ratebar.setVisibility(View.VISIBLE);
                yes.setVisibility(View.INVISIBLE);
                no.setVisibility(View.INVISIBLE);

                // show rate

                TextView ratetext = (TextView) findViewById(R.id.ratetext);
                ratebar.setProgress(post.getRate());
                String Srate = ""+post.getRate() +"% of people ("+post.getYes()+") think this is relatable!";
                ratetext.setText(Srate);




            }
        });


        Button next =(Button) findViewById(R.id.next); // this is NOT relatable!
        next.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                // nouveau post
                browsingAlgorithme();
                Button next =(Button) findViewById(R.id.next);
                Button no =(Button) findViewById(R.id.no);
                Button yes =(Button) findViewById(R.id.yes);
                ProgressBar ratebar =(ProgressBar) findViewById(R.id.ratebar);
                ratebar.setVisibility(View.INVISIBLE);
                next.setVisibility(View.INVISIBLE);
                yes.setVisibility(View.VISIBLE);
                no.setVisibility(View.VISIBLE);
                TextView ratetext = (TextView) findViewById(R.id.ratetext);
                ratetext.setText("");


            }
        });



    }

    public void browsingAlgorithme(){


        Random r = new Random();
        int RandPost = r.nextInt(Posts.size()); // on choisit un post aléatoire
        post=Posts.get(RandPost);


        if (browsed%4==1){ // tous les 4 post on propose un post qui a peu de vue

            for(int i=0;i<30;i++) { // on tente 30 fois pour ne pas bloquer le programme indéfiniement sinon tant pis
                if (post.getYes() + post.getNo() < 10){ // less than 10 people have seen that post, let's show it!
                    i=31;
                }
                else{ // it doesn't, let's find another
                    RandPost = r.nextInt(Posts.size()); // on choisit un post aléatoire
                    post=Posts.get(RandPost);
                }
            }

        }
        else {

            for(int i=0;i<30;i++) { // on tente 30 fois pour ne pas bloquer le programme indéfiniement sinon tant pis
                if (post.getRate() > 60 && post.getYes() + post.getNo() > 10){ // more than 10 people found this relatable !!
                    i=31;
                }
                else{ // it doesn't, let's find another
                    RandPost = r.nextInt(Posts.size()); // on choisit un post aléatoire
                    post=Posts.get(RandPost);
                }
            }

        }

        text.setText(Posts.get(RandPost).getText());
        key=Keys.get(RandPost);


        browsed++; // we browsed one more post

    }


}


