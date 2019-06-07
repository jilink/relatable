package com.punchips.relatable;

import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Html;
import android.text.Spanned;
import android.text.method.LinkMovementMethod;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class CGU_Agreement extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_cgu_agreement);

        TextView termsOfUse = (TextView)findViewById(R.id.textView2);
        termsOfUse.setMovementMethod(LinkMovementMethod.getInstance());

        Button agree = (Button) findViewById(R.id.agree);
        agree.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences settings = getSharedPreferences("pref", 0);
                SharedPreferences.Editor editor = settings.edit();
                editor.putBoolean("cgu", true);
                editor.commit();
                Intent myIntent = new Intent(CGU_Agreement.this, Home.class);
                startActivity(myIntent);
                finish();



            }
        });
    }
}
