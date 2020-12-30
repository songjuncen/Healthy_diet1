package cn.edu.healthydiet.food_grid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.GridView;

import java.util.List;

import cn.edu.healthydiet.R;
import cn.edu.healthydiet.bean.FoodBean;
import cn.edu.healthydiet.bean.FoodUtils;
import cn.edu.healthydiet.food_list.InfoListActivity;

public class FoodGridActivity extends AppCompatActivity {
    GridView gv;
    List<FoodBean>mDatas;
    private FoodGridAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_grid);
        gv = findViewById(R.id.food_gird_gv);//查找控件
        //数据源
        mDatas= FoodUtils.getAllFoodList();
        //创建适配器对象
        adapter = new FoodGridAdapter(this, mDatas);//得到adapter对象
        //设置适配器
        gv.setAdapter(adapter);
        setListener();


    }

    private void setListener() {
        gv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//监听setOnItemClickListener的接口
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                FoodBean foodBean = mDatas.get(position);//将当前位置的对象传送过去
                Intent intent=new Intent(FoodGridActivity.this, FoodDescActivity.class);//传递对象，是从InfoListActivity.this到详情页面的跳转
                intent.putExtra("food",foodBean);//传递可以这个接口这个类可以被序列化的对象
                startActivity(intent);//进行跳转


            }
        });
    }




}