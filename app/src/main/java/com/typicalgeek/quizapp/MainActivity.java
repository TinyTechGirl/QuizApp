package com.typicalgeek.quizapp;

import android.content.Intent;
import android.os.Bundle;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationSet;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RadioButton;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int counter=0, correct =0, TEXT_FADE_DELAY=1000;
    String[] questions = {"Which country colonized Kenya?", "In which year did Kenya gain independence?",
            "Who was the first president of Kenya?", "Which one was among the Kapenguria six?"};
    String[][] choices =  {{"Britain", "Spain", "Nigeria", "Croatia"},
            {"1924", "1963", "1999", "1945"},
            {"Oginga Odinga", "Babu Owino", "Livingstone Kamau", "Thabo Mbeki"},
            {"Micheal Kamau" ,"Oginga Odinga", "Sammy James", "Fred Kubai"}};
    String[] answers = {"A", "B", "C", "D"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);

        Button btnBegin = (Button) findViewById(R.id.btnBegin),
                btnSubmit = (Button) findViewById(R.id.btnSubmit),
                btnQuit = (Button) findViewById(R.id.btnQuit);
        final RelativeLayout rlWelcome = (RelativeLayout) findViewById(R.id.rlWelcome),
                rlQuestion = (RelativeLayout) findViewById(R.id.rlQuestion),
                rlResult = (RelativeLayout) findViewById(R.id.rlResult);
        final RadioButton rbA = (RadioButton) findViewById(R.id.choiceA),
                rbB = (RadioButton) findViewById(R.id.choiceB),
                rbC = (RadioButton) findViewById(R.id.choiceC),
                rbD = (RadioButton) findViewById(R.id.choiceD);
        final TextView tvQuestion = (TextView) findViewById(R.id.tvQuestion),
                tvResultHead = (TextView) findViewById(R.id.tvResultHead),
                tvResultBody = (TextView) findViewById(R.id.tvResultBody);
        final ImageView imgC = findViewById(R.id.ivCorrect),
                imgW = findViewById(R.id.ivWrong);

        final Animation in = new AlphaAnimation(0.0f, 1.0f),
                out = new AlphaAnimation(1.0f, 0.0f);
        in.setDuration(TEXT_FADE_DELAY);
        out.setDuration(TEXT_FADE_DELAY);

        final AnimationSet outSet = new AnimationSet(true);
        outSet.addAnimation(out);

        final AnimationSet imgSet = new AnimationSet(true);
        imgSet.addAnimation(in);
        out.setStartOffset(2000);
        imgSet.addAnimation(out);

        AnimationSet inSet = new AnimationSet(true);
        in.setStartOffset(2000);
        outSet.addAnimation(in);


        final FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Quit this quiz?", Snackbar.LENGTH_LONG)
                        .setAction("Confirm", new View.OnClickListener() {
                            @Override
                            public void onClick(View view) {
                                Toast.makeText(MainActivity.this, "Quitting...", Toast.LENGTH_SHORT).show();
                                counter = 0;
                                rlWelcome.setVisibility(View.VISIBLE);
                                rlQuestion.setVisibility(View.GONE);
                                rlResult.setVisibility(View.GONE);
                                fab.setVisibility(View.GONE);
                            }
                        }).show();
            }
        });

        btnBegin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Good Luck", Toast.LENGTH_SHORT).show();
                tvQuestion.setText(questions[counter]);
                rbA.setText(choices[counter][0]);
                rbB.setText(choices[counter][1]);
                rbC.setText(choices[counter][2]);
                rbD.setText(choices[counter][3]);
                rlWelcome.setVisibility(View.GONE);
                rlQuestion.setVisibility(View.VISIBLE);
                rlResult.setVisibility(View.GONE);
                fab.setVisibility(View.VISIBLE);
            }
        });

        btnSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (counter==answers.length-1){
                    if (correct == 4) {
                        tvResultHead.setText("Perfect");
                        tvResultBody.setText("You have scored "+correct+" out of "+answers.length);
                    } else if (correct == 3) {
                        tvResultHead.setText("Excellent");
                        tvResultBody.setText("You have scored "+correct+" out of "+answers.length);
                    } else if (correct == 2) {
                        tvResultHead.setText("Good");
                        tvResultBody.setText("You have scored "+correct+" out of "+answers.length);
                    } else if (correct == 1) {
                        tvResultHead.setText("Okay");
                        tvResultBody.setText("You have scored "+correct+" out of "+answers.length);
                    } else {
                        tvResultHead.setText("Horrible");
                        tvResultBody.setText("You have scored "+correct+" out of "+answers.length);
                    }
                    rlQuestion.setVisibility(View.GONE);
                    rlResult.setVisibility(View.VISIBLE);
                } else {
                    String choice = "";
                    if (rbA.isChecked() || rbB.isChecked() || rbC.isChecked() || rbD.isChecked()) {
                        if (rbA.isChecked()) {
                            choice = "A";
                        } else if (rbB.isChecked()) {
                            choice = "B";
                        } else if (rbC.isChecked()) {
                            choice = "C";
                        } else if (rbD.isChecked()) {
                            choice = "D";
                        }
                        if (isCorrect(counter, choice)) {
                            Toast.makeText(MainActivity.this, "Correct!", Toast.LENGTH_SHORT).show();
                        } else {
                            Toast.makeText(MainActivity.this, "Wrong!", Toast.LENGTH_SHORT).show();
                        }
                        counter++;
                        rbA.setChecked(false);
                        rbB.setChecked(false);
                        rbC.setChecked(false);
                        rbD.setChecked(false);
                        tvQuestion.setText(questions[counter]);
                        rbA.setText(choices[counter][0]);
                        rbB.setText(choices[counter][1]);
                        rbC.setText(choices[counter][2]);
                        rbD.setText(choices[counter][3]);
                        rlWelcome.setVisibility(View.GONE);
                        rlQuestion.setVisibility(View.VISIBLE);
                        fab.setVisibility(View.VISIBLE);
                    } else {
                        Snackbar.make(view, "Please select an option.", Snackbar.LENGTH_LONG)
                                .setAction("Okay", new View.OnClickListener() {
                                    @Override
                                    public void onClick(View view) {
                                        Toast.makeText(MainActivity.this, "Awesome!", Toast.LENGTH_SHORT).show();
                                    }
                                }).show();
                    }
                }
            }
        });

        btnQuit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(MainActivity.this, "Quitting...", Toast.LENGTH_SHORT).show();
                counter = 0;
                rlWelcome.setVisibility(View.VISIBLE);
                rlQuestion.setVisibility(View.GONE);
                rlResult.setVisibility(View.GONE);
                fab.setVisibility(View.GONE);
            }
        });
    }

    private boolean isCorrect(int counter, String choice) {
        boolean corr;
        if (choice.trim().equalsIgnoreCase(answers[counter].trim())){
            correct++;
            return true;
        } else {
            return  false;
        }
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_help) {
            Toast.makeText(this, "View Help.", Toast.LENGTH_SHORT).show();
            Intent i = new Intent(MainActivity.this, HelpActivity.class);
            startActivity(i);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
