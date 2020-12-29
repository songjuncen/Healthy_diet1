package cn.edu.healthydiet;

import androidx.appcompat.app.AppCompatActivity;

import cn.edu.healthydiet.food_grid.FoodGridActivity;
import cn.edu.healthydiet.food_list.InfoListActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;


public class HomeMenuActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home_menu);
    }

    public void onClick(View view) {
        Intent intent = new Intent();
        switch (view.getId()) {
            case R.id.home_btn1:
                intent.setClass(HomeMenuActivity.this, InfoListActivity.class);
                break;
            case R.id.home_btn2:
               intent.setClass(HomeMenuActivity.this, FoodGridActivity.class);
                break;
            case R.id.home_btn3:
              intent.setClass(HomeMenuActivity.this, AboutActivity.class);
                break;
        }
        startActivity(intent);
    }
    }
//在这里设置主界面上三个按钮进行跳转，主界面结构完成,利用intent进行跳转,SWITCH结束之后，start进行跳转