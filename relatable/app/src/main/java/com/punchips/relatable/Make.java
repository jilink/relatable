package com.punchips.relatable;

import android.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

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


            }
        });
    }


}
