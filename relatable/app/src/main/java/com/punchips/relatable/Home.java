package com.punchips.relatable;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class Home extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);


        // BOUTON make
        Button make = (Button) findViewById(R.id.make);
        make.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                Intent myIntent = new Intent(Home.this, Make.class);
                startActivity(myIntent);



            }
        });


    }
}
