package com.example.qiuz;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.graphics.Color;
import android.os.Bundle;
import android.os.CountDownTimer;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class quizapplication extends AppCompatActivity implements View.OnClickListener {
    TextView totque,que;
    TextView text3;
    private Button bt1,bt2,bt3,bt4;
    private  Button submit;
    int totalque = queandans.ques.length;
    int presentque_ind = 0;
    int marks =0;
    int count=1;
    String selans="";

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_quizapplication);

        totque =findViewById(R.id.textView1);
        que=findViewById(R.id.questxt);
        text3 = findViewById(R.id.textView2);
        bt1 = findViewById(R.id.opn1);
        bt2 = findViewById(R.id.opn2);
        bt3 = findViewById(R.id.opn3);
        bt4 = findViewById(R.id.opn4);
        submit = findViewById(R.id.subans);

        bt1.setOnClickListener(this);
        bt2.setOnClickListener(this);
        bt3.setOnClickListener(this);
        bt4.setOnClickListener(this);
        submit.setOnClickListener(this);

        totque.setText("Question : 1/"+totalque);
        starttm();

        checkans();

    }

    private void starttm() {
        new CountDownTimer(120000, 1000) {
            @Override
            public void onTick(long millisUntilFinished) {
                text3.setText("Time : " + millisUntilFinished / 1000);
            }

            @Override
            public void onFinish() {
                quizcompleted();
            }

        }.start();
    }

    @Override
    public void onClick(View view) {

        bt1.setBackgroundColor(Color.rgb(255,112,67));
        bt2.setBackgroundColor(Color.rgb(255,112,67));
        bt3.setBackgroundColor(Color.rgb(255,112,67));
        bt4.setBackgroundColor(Color.rgb(255,112,67));
        Button clcbtn = (Button) view;
        if (clcbtn.getId()==R.id.subans){
            if(selans.equals(queandans.ans[presentque_ind])){
                marks++;
            }
            presentque_ind++;
            checkans();
        }else{
            selans = clcbtn.getText().toString();
            clcbtn.setBackgroundColor(Color.rgb(0,0,0));

        }



    }
    void checkans(){
        if(presentque_ind==totalque){
            quizcompleted();
            return;
        }
        que.setText(queandans.ques[presentque_ind]);
        bt1.setText(queandans.opn[presentque_ind][0]);
        bt2.setText(queandans.opn[presentque_ind][1]);
        bt3.setText(queandans.opn[presentque_ind][2]);
        bt4.setText(queandans.opn[presentque_ind][3]);
        totque.setText("Question : " +(count)+"/"+totalque);
        count++;



    }
    void quizcompleted(){
        String status="";
        if(marks>totalque*0.50){
            status = "Passed :)";
        }
        else{
            status="Better Luck next time :(";
        }

        new AlertDialog.Builder(this)
                .setTitle(status)
                .setMessage("Score : "+marks+" out of "+totalque)
                .setPositiveButton("Reattempt",(dialogInterface, i) -> reattemptquiz())
                .setNegativeButton("Exit", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        finishAffinity();
                    }
                })
                .setCancelable(false)
                .show();
    }

    void reattemptquiz(){
        marks=0;
        presentque_ind=0;
        count=1;
        starttm();
        checkans();
    }



}