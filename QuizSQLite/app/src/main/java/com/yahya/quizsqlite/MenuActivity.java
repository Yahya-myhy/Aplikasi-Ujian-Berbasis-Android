package com.yahya.quizsqlite;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MenuActivity extends AppCompatActivity {
    UserDBHelper db;
    Button logout, ipa, ips, profile;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_menu);

        db = new UserDBHelper(this);

        logout = (Button)findViewById(R.id.btn_logout);

        profile = (Button)findViewById(R.id.btn_profile);

        ipa = (Button)findViewById(R.id.btn_IPA);

        ips = (Button)findViewById(R.id.btn_IPS);

        Boolean checkSession = db.checkSession("ada");
        if (checkSession == false) {
            Intent loginIntent = new Intent(MenuActivity.this, LoginActivity.class);
            startActivity(loginIntent);
            finish();
        }


        //Logout
        logout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil Keluar", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MenuActivity.this, LoginActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        //soal
        ipa.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil ", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MenuActivity.this, DashboardActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        ips.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil ", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MenuActivity.this, DashboardActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });

        profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Boolean updtSession = db.upgradeSession("kosong", 1);
                if (updtSession == true) {
                    Toast.makeText(getApplicationContext(), "Berhasil ", Toast.LENGTH_SHORT).show();
                    Intent loginIntent = new Intent(MenuActivity.this, ProfileActivity.class);
                    startActivity(loginIntent);
                    finish();
                }
            }
        });
    }
}
