package com.ryanchristopher.quiz.Activities;

import androidx.appcompat.app.AppCompatActivity;

import android.animation.Animator;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.view.WindowManager;
import android.view.animation.DecelerateInterpolator;
import android.widget.Button;
import android.widget.TextView;

import com.ryanchristopher.quiz.Models.QuestionModel;
import com.ryanchristopher.quiz.R;
import com.ryanchristopher.quiz.databinding.ActivityQuestionBinding;
import com.ryanchristopher.quiz.databinding.ActivitySetsBinding;

import java.util.ArrayList;

public class QuestionActivity extends AppCompatActivity {

    ArrayList<QuestionModel> list = new ArrayList<>();
    private int count = 0;
    private int position = 0;
    private int score = 0;
    CountDownTimer timer;

    ActivityQuestionBinding binding;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityQuestionBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());

        getSupportActionBar().hide();

        resetTimer();
        timer.start();

        String setName = getIntent().getStringExtra("level");

        if (setName.equals("Level 1")){

            setOne();

        } else if (setName.equals("Level 2")){

            setTwo();

        }
        
        for (int i=0;i<4;i++){
            binding.optionContainer.getChildAt(i).setOnClickListener(new View.OnClickListener(){
                @Override
                public void onClick(View view){
                    
                    checkAnswer((Button) view);
                    
                }
            });
        }


        playAnimation(binding.question, 0,list.get(position).getQuestion());


        binding.btnNext.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {

                if(timer !=null){

                    timer.cancel();

                }

                timer.start();
                binding.btnNext.setEnabled(false);
                binding.btnNext.setAlpha((float) 0.3);
                enableOption(true);
                position ++;

                if(position ==list.size()){

                    Intent intent = new Intent(QuestionActivity.this,ScoreActivity.class);
                    intent.putExtra("Score",score);
                    intent.putExtra("total",list.size());
                    startActivity(intent);
                    finish();
                    return;

                }

                count=0;
                playAnimation(binding.question, 0,list.get(position).getQuestion());


            }
        });
        
    }

    private void resetTimer() {

        timer = new CountDownTimer(3000,1000) {
            @Override
            public void onTick(long l) {

                binding.timer.setText(String.valueOf(l/3000));

            }

            @Override
            public void onFinish() {

                Dialog dialog = new Dialog(QuestionActivity.this);
                dialog.getWindow().addFlags(WindowManager.LayoutParams.FLAG_BLUR_BEHIND);
                dialog.setCancelable(false);
                dialog.setContentView(R.layout.timer_dialogue);
                dialog.findViewById(R.id.tryAgain).setOnClickListener(new View.OnClickListener(){
                    @Override
                    public void onClick(View view){

                        Intent intent = new Intent(QuestionActivity.this,SetsActivity.class);
                        startActivity(intent);
                        finish();

                    }
                });

                dialog.show();

            }
        };

    }

    private void playAnimation(View view, int value, String data) {

        view.animate().alpha(value).scaleX(value).scaleY(value).setDuration(500).setStartDelay(100).setInterpolator(new DecelerateInterpolator()).setListener(new Animator.AnimatorListener() {
            @Override
            public void onAnimationStart(Animator animator) {

                if (value ==0 && count<4){

                    String option = "";

                    if (count ==0){

                        option = list.get(position).getOptionA();

                    } else if (count ==1){

                        option = list.get(position).getOptionB();

                    } else if (count ==2){

                        option = list.get(position).getOptionC();

                    } else if (count ==3){

                        option = list.get(position).getOptionD();

                    }

                    playAnimation(binding.optionContainer.getChildAt(count), 0,option);
                    count ++;

                }

            }

            @Override
            public void onAnimationEnd(Animator animator) {

                if (value==0){

                    try{

                        ((TextView)view).setText(data);
                        binding.totalQuestion.setText(position+1+"/"+list.size());

                    } catch (Exception e) {

                        ((Button)view).setText(data);

                    }

                    view.setTag(data);
                    playAnimation(view, 1,data);
                }

            }

            @Override
            public void onAnimationCancel(Animator animator) {



            }

            @Override
            public void onAnimationRepeat(Animator animator) {



            }
        });

    }

    private void enableOption(boolean enable) {

        for (int i=0;i<4;i++){

            binding.optionContainer.getChildAt(i).setEnabled(enable);
            if(enable){

                binding.optionContainer.getChildAt(i).setBackgroundResource(R.drawable.btn_opt);

            }

        }

    }

    private void setOne() {

        list.add(new QuestionModel("Makanan Khas Jogja yang rasanya manis dan terbuat dari " +
                "nangka adalah","gudeg","sate","bakpia","wajik","gudeg"));

        list.add(new QuestionModel("Makan gudeg paling cocok bersama lauk…","telur " +
                "pindang","bakso sapi","mie ayam","siomay","telur pindang"));

        list.add(new QuestionModel("Masak gudeg memakan waktu berapa jam ?","10 jam","14 " +
                "jam","18 jam","24 jam","24 jam"));

    }

    private void setTwo() {

        list.add(new QuestionModel("Kenapa memasak gudeg memerlukan waktu yang lama? Kecuali…","agar " +
                "rasanya menjadi asin","agar bumbu meresap sempurna","Agar nangka lebih empuk","agar rasa manis lebih berasa","agar rasanya menjadi asin"));

        list.add(new QuestionModel("Apa rasa dominan gudeg?","asam","Asin","manis","gurih","manis"));

        list.add(new QuestionModel("Kenapa gudeg rasanya manis?","pemakaian gula jawa yang banyak","pemakaian garam yang banyak","pemakaian " +
                "micin yang banyak","rasa nangka memang manis","pemakaian gula jawa yang banyak"));

        list.add(new QuestionModel("Ketika memasak gudeg pasti kita mencampurkan…","parutan kelapa","air kelapa","santan","batok kelapa","santan"));

        list.add(new QuestionModel("Dimana rumah makan gudeg terlezat di jogja…","Gudeg Yu Djum","Gudeg " +
                "You Djum","Gudeg Da Yum","Gudeg Bu Djum","Gudeg Yu Djum"));
    }

    private void checkAnswer(Button selectedOption) {

        if(timer !=null){

            timer.cancel();

        }

        binding.btnNext.setEnabled(true);
        binding.btnNext.setAlpha(1);

        if (selectedOption.getText().toString().equals(list.get(position).getCorrectAnswer())){

            score ++;
            selectedOption.setBackgroundResource(R.drawable.correct_answer);
        } else{

            selectedOption.setBackgroundResource(R.drawable.wrong_answer);

            Button correctOption = (Button) selectedOption.findViewWithTag(list.get(position).getCorrectAnswer());
            correctOption.setBackgroundResource(R.drawable.correct_answer);
        }

    }
    
}