package com.example.locnt.app_project;

import android.content.Intent;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.TextView;

public class InfoActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info);
        TextView back = findViewById(R.id.txtBack);
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(InfoActivity.this,MainActivity.class);
                intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK);
                startActivity(intent);
            }
        });
        TextView username = findViewById(R.id.txtUsername);
        TextView name = findViewById(R.id.txtName);
        TextView ho = findViewById(R.id.txtHo);
        TextView phone = findViewById(R.id.txtPhone);
        username.setText("username");
        name.setText("tom");
        ho.setText("chery");
        phone.setText("0919876589");

    }
}
