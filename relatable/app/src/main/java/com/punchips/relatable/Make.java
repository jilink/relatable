package com.punchips.relatable;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Make extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_make);

        Button submit =(Button) findViewById(R.id.submit);
        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                EditText text = (EditText) findViewById(R.id.editText);

                String Text =text.getText().toString();

                Toast toast = Toast.makeText(getApplicationContext(),Text,Toast.LENGTH_SHORT);
                toast.show();
// WRITING
                FirebaseDatabase database = FirebaseDatabase.getInstance();
                DatabaseReference myRef = database.getReference("posts");

                String id= Long.toString(System.currentTimeMillis()); // MAKING ID

                DatabaseReference Id = myRef.child(id);
                Id.child("text").setValue(Text);
                Id.child("yes").setValue(0);
                Id.child("no").setValue(0);




            }
        });
    }


}
