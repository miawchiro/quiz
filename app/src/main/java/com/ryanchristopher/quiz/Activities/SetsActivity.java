package com.ryanchristopher.quiz.Activities;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Bundle;

import com.ryanchristopher.quiz.Models.SetModel;
import com.ryanchristopher.quiz.R;
import com.ryanchristopher.quiz.databinding.ActivitySetsBinding;

import java.util.ArrayList;

import com.ryanchristopher.quiz.Adapters.SetAdapter;


public class SetsActivity extends AppCompatActivity {

    ActivitySetsBinding binding;
    ArrayList<SetModel>list;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivitySetsBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        list = new ArrayList<>();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        binding.setsRecy.setLayoutManager(linearLayoutManager);

        list.add(new SetModel("Level 1"));
        list.add(new SetModel("Level 2"));
        list.add(new SetModel("Level 3"));
        list.add(new SetModel("Level 4"));
        list.add(new SetModel("Level 5"));
        list.add(new SetModel("Level 6"));
        list.add(new SetModel("Level 7"));
        list.add(new SetModel("Level 8"));
        list.add(new SetModel("Level 9"));
        list.add(new SetModel("Level 10"));

        binding.setsRecy.setAdapter(adapter);



    }
}