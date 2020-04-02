package com.yahya.quizsqlite;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class ProfileActivity extends AppCompatActivity {
    protected Cursor cursor;
    UserDBHelper db;
    Button ton;
    TextView text1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile);

        db = new UserDBHelper(this);
        text1 = (TextView) findViewById(R.id.text1);
        SQLiteDatabase baca = db.getReadableDatabase();
        cursor = baca.rawQuery("SELECT * FROM user WHERE username = '" + getIntent().getStringExtra("username") + "'",null);
        cursor.moveToFirst();
        if (cursor.getCount()>0)
        {
            cursor.moveToPosition(0);
            text1.setText(cursor.getString(0).toString());
        }
        ton = (Button) findViewById(R.id.btn_kembali);
        ton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Berhasil Kembali", Toast.LENGTH_SHORT).show();
                Intent loginIntent = new Intent(ProfileActivity.this, DashboardActivity.class);
                startActivity(loginIntent);
                finish();
            }
        });

    }
}
