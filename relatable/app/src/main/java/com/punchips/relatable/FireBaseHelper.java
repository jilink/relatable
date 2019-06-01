package com.punchips.relatable;

import android.support.annotation.NonNull;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class FireBaseHelper {
    private FirebaseDatabase mDatabase;
    private DatabaseReference mReferencePost;
    private List<Post> posts = new ArrayList<>();

    public interface  DataStatus{
        void DataIsLoaded(List<Post> posts, List <String> keys);
        void DataIsInserted();
        void DataIsUpdated();
        void DataisDeleted();



    }
    public FireBaseHelper() {
        mDatabase=FirebaseDatabase.getInstance();
        mReferencePost=mDatabase.getReference("posts");
    }

    public void readPosts(final DataStatus datastatus){
        mReferencePost.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                posts.clear();
                List<String> keys = new ArrayList<>();
                for(DataSnapshot keyNode : dataSnapshot.getChildren()){
                    keys.add(keyNode.getKey());
                    Post post = keyNode.getValue(Post.class);
                    posts.add(post);
                }
                datastatus.DataIsLoaded(posts,keys);

            }

            @Override
            public void onCancelled(@NonNull DatabaseError databaseError) {

            }
        });
    }
}
