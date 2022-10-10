package com.example.scoringdevice;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.os.SystemClock;
import android.view.View;
import android.widget.Button;
import android.widget.Chronometer;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Vector;

public class MainActivity extends AppCompatActivity {

    //声明要切换的图片及其切换按钮
    private ImageView image_left;
    private ImageView image_right;
    private Button btn_l;
    private Button btn_r;
    private int id=R.drawable.nation1;

    //声明加分控件
    private Button score1_l;
    private Button score2_l;
    private Button score3_l;
    private Button score1_r;
    private Button score2_r;
    private Button score3_r;
    private TextView score_l;
    private TextView score_r;
    private int score_array[]={1,2,3};
    private int score_a=0;
    private int score_b=0;
    private Vector a_list=new Vector<>();
    private Vector b_list=new Vector<>();
    private Vector mark_list=new Vector<>();

    //声明重置分数、撤销控件
    private Button reset;
    private Button turn_back;
    private int mark=0;

    //声明时间显示、开始、暂停、重置控件
    private Chronometer timer;
    private Button btn1,btn2;
    private boolean judge=true;
    private long recording_time=0;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        //实现图片切换
        image_left = findViewById(R.id.image_left);
        image_right = findViewById(R.id.image_right);
        btn_l = findViewById(R.id.btn_l);
        btn_r = findViewById(R.id.btn_r);

        btn_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == R.drawable.nation1)
                    id = R.drawable.nation2;
                else if (id == R.drawable.nation2)
                    id = R.drawable.nation3;
                else if (id == R.drawable.nation3)
                    id = R.drawable.nation4;
                else if (id == R.drawable.nation4)
                    id = R.drawable.nation5;
                else if (id == R.drawable.nation5)
                    id = R.drawable.nation6;
                else if (id == R.drawable.nation6)
                    id = R.drawable.nation1;
                image_left.setImageResource(id);
            }
        });

        btn_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (id == R.drawable.nation1)
                    id = R.drawable.nation2;
                else if (id == R.drawable.nation2)
                    id = R.drawable.nation3;
                else if (id == R.drawable.nation3)
                    id = R.drawable.nation4;
                else if (id == R.drawable.nation4)
                    id = R.drawable.nation5;
                else if (id == R.drawable.nation5)
                    id = R.drawable.nation6;
                else if (id == R.drawable.nation6)
                    id = R.drawable.nation1;
                image_right.setImageResource(id);
            }
        });

        //加分+分数改变
        score_l = findViewById(R.id.score_l);
        score_r = findViewById(R.id.score_r);
        score1_l = findViewById(R.id.score1_l);
        score2_l = findViewById(R.id.score2_l);
        score3_l = findViewById(R.id.score3_l);
        score1_r = findViewById(R.id.score1_r);
        score2_r = findViewById(R.id.score2_r);
        score3_r = findViewById(R.id.score3_r);

        score1_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a_list.add(score_a);
                mark = 0;
                mark_list.add(mark);
                score_a += 1;
                score_l.setText(Integer.toString(score_a));
            }
        });

        score2_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a_list.add(score_a);
                mark = 0;
                mark_list.add(mark);
                score_a += 2;
                score_l.setText(Integer.toString(score_a));
            }
        });

        score3_l.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                a_list.add(score_a);
                mark = 0;
                mark_list.add(mark);
                score_a += 3;
                score_l.setText(Integer.toString(score_a));
            }
        });

        score1_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_list.add(score_b);
                mark = 1;
                mark_list.add(mark);
                score_b += 1;
                score_r.setText(Integer.toString(score_b));
            }
        });

        score2_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_list.add(score_b);
                mark = 1;
                mark_list.add(mark);
                score_b += 2;
                score_r.setText(Integer.toString(score_b));
            }
        });

        score3_r.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                b_list.add(score_b);
                mark = 1;
                mark_list.add(mark);
                score_b += 3;
                score_r.setText(Integer.toString(score_b));
            }
        });

        //实现重置分数、撤销操作
        reset = findViewById(R.id.btn_reset);
        turn_back = findViewById(R.id.btn_back);

        reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                score_a = 0;
                score_b = 0;
                a_list.removeAllElements();
                b_list.removeAllElements();
                mark_list.removeAllElements();
                score_l.setText(Integer.toString(score_a));
                score_r.setText(Integer.toString(score_b));
            }
        });

        turn_back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(mark_list.isEmpty())
                    ;
                else{
                    if (mark_list.lastElement().equals(0)) {
                        score_a = (int) a_list.lastElement();
                        a_list.removeElementAt(a_list.size() - 1);
                        mark_list.removeElementAt(mark_list.size() - 1);
                        score_l.setText(Integer.toString(score_a));
                    } else if (mark_list.lastElement().equals(1)) {
                        score_b = (int) b_list.lastElement();
                        b_list.removeElementAt(b_list.size() - 1);
                        mark_list.removeElementAt(mark_list.size() - 1);
                        score_r.setText(Integer.toString(score_b));
                    }
                }
            }
        });

        //计时器
        timer=findViewById(R.id.timer);
        btn1=findViewById(R.id.start_end);
        btn2=findViewById(R.id.reset);

        btn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(judge){
                    btn1.setText("stop");
                    judge=false;
                    timer.setBase(SystemClock.elapsedRealtime()-recording_time);
                    timer.start();
                }
                else{
                    btn1.setText("start");
                    judge=true;
                    timer.stop();
                    recording_time=SystemClock.elapsedRealtime()-timer.getBase();
                }
            }
        });

        btn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                recording_time=0;
                timer.setBase(SystemClock.elapsedRealtime());
            }
        });
    }

}