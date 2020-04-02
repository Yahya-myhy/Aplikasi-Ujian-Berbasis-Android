package com.yahya.quizsqlite;

import android.content.Intent;
import android.graphics.Color;
import android.net.Uri;
import android.os.Bundle;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.Objects;

import static com.yahya.quizsqlite.CategoryAdapter.CATEGORY_COLOR;
import static com.yahya.quizsqlite.CategoryAdapter.CATEGORY_ID;

/**
 * Created by Azhar Rivaldi on 10/07/2019.
 */

public class QuestionActivity extends AppCompatActivity {

    private RecyclerView mRecyclerView;
    private QuestionAdapter mAdapter;
    private ArrayList<Question> mQuestionList;
    private QuizDBHelper mDbHelper;

    private ConstraintLayout mParentLayout;
    private TextView mScoreTextView;
    private TextView mRemaningQuestionsTextView;
    private int mTotalQuestions;
    private int mScore;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_question);

        findViewById(R.id.btn_link).setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                click_btn("https://blog.ruangguru.com/latihan-dan-pembahasan-soal-hots-sbmptn-matematika-dasar-2019");
            }
        });

        Objects.requireNonNull(getSupportActionBar()).setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setDisplayShowHomeEnabled(true);

        Bundle categoryBundle = null;
        if (getIntent() != null) {
            categoryBundle = getIntent().getExtras();
        }

        mParentLayout = findViewById(R.id.question_layout);
        if (categoryBundle != null) {
            String hexColor = String.format("#%06X", (0xFFFFFF & categoryBundle.getInt(CATEGORY_COLOR)));
            hexColor = "#44"+hexColor.substring(1);
            mParentLayout.setBackgroundColor(Color.parseColor(hexColor));
        }

        mScoreTextView = findViewById(R.id.score);
        mRemaningQuestionsTextView = findViewById(R.id.remaining_questions);

        mRecyclerView = findViewById(R.id.recyclerView);
        mRecyclerView.setNestedScrollingEnabled(false);
        mRecyclerView.setHasFixedSize(true);

        mDbHelper = new QuizDBHelper(this, categoryBundle);
        if (categoryBundle != null) {
            mQuestionList = mDbHelper.getAllQuestions(categoryBundle.getString(CATEGORY_ID));
            mTotalQuestions = mQuestionList.size();
            mScore = 0;
            displayScore();
        }
        mAdapter = new QuestionAdapter(this, mQuestionList, categoryBundle.getString(CATEGORY_ID));
        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        mRecyclerView.setAdapter(mAdapter);
    }

    public void displayScore() {
        String scoreString = "Score " + mScore + "/" + mTotalQuestions;
        mScoreTextView.setText(scoreString);
        mRemaningQuestionsTextView.setText("Remaining: " + mTotalQuestions--);
    }
    public void click_btn(String url){
        Intent intent = new Intent(Intent.ACTION_VIEW);
        intent.setData(Uri.parse(url));
        startActivity(intent);
    }

    public void updateScore() {
        mScore++;
    }
}
