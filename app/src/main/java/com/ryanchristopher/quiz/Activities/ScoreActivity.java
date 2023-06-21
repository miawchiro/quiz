package com.ryanchristopher.quiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;

import com.ryanchristopher.quiz.R;
import com.ryanchristopher.quiz.databinding.ActivityScoreBinding;

public class ScoreActivity extends AppCompatActivity {

    ActivityScoreBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityScoreBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        int totalScore = getIntent().getIntExtra("total", 0);
        int correctAnswer = getIntent().getIntExtra("score", 0);

        int wrong = totalScore - correctAnswer;

        binding.totalQuestions.setText(String.valueOf(totalScore));
        binding.rightAnswer.setText(String.valueOf(correctAnswer));
        binding.wrongAnswer.setText(String.valueOf(wrong));

        binding.btnRetry.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(ScoreActivity.this, SetsActivity.class);
                startActivity(intent);
                finish();
            }
        });

        binding.btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                finish();
            }
        });
    }
}
