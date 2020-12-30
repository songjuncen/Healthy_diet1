package cn.edu.healthydiet.guide;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.healthydiet.HomeMenuActivity;
import cn.edu.healthydiet.R;

public class MainActivity extends AppCompatActivity {
    TextView tv;
    int time = 5;
    SharedPreferences preferences;  //存储键值对数据
    private SharedPreferences.Editor editor;//利用SharedPreferences,完成主界面和引导界面分别跳转的功能

    @SuppressLint("HandlerLeak")
    Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {
            //检测接受的信息编号是否为发送的信息编号1

            if (msg.what==1) {
                time--;
                if (time ==0) {
                    //  跳转home页面
                    Intent intent = new Intent();
                    boolean isfirst = preferences.getBoolean("isfirst", true);
//                    键值对存储以前是否有进入过APP，如果之前没有存储过，默认就是true

                    if (isfirst) {
                        intent.setClass(MainActivity.this, GuideActivity.class);//isfirst为ture,则是第一次进入APP,就会进去时跳转到指引界面
                        editor.putBoolean("isfirst",false);  //写入不是第一次进入的纪录
                        editor.commit();    // 提交本次修改纪录
                    }else {
                        intent.setClass(MainActivity.this, HomeMenuActivity.class);//不是第一次进入APP，就进入homemenu页面
                    }
                    startActivity(intent);
                    finish();//最后返回时，不会返回开始的倒计时页面，直接关闭activity
                }else {
                    tv.setText(time+"");
                    handler.sendEmptyMessageDelayed(1,1000);//一秒钟之后再发信息，time--，前面检测是否为0，不为0就继续--
                }
            }
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        tv = findViewById(R.id.main_tv);
        preferences = getSharedPreferences("health_pref",MODE_PRIVATE);
        editor = preferences.edit(); //写入数据的对象
        handler.sendEmptyMessageDelayed(1,1000);//发送信息编号为1，发送信息在1秒钟之后，
    }
}