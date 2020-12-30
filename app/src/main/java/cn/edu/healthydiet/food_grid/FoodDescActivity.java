package cn.edu.healthydiet.food_grid;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import cn.edu.healthydiet.R;
import cn.edu.healthydiet.bean.FoodBean;

public class FoodDescActivity extends AppCompatActivity {
    TextView titleTv1,titleTv2,descTv,notTv;
    ImageView backIv,bigPicIv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_food_desc);
        initView();
        //接受上一级页面传来的数据
       Intent intent= getIntent();
       FoodBean foodBean= (FoodBean) intent.getSerializableExtra("food");//传的是Serializable类型的对象，并转换成foodBean的对象，数据都在foodBean当中
        //设置显示控件
        titleTv1.setText(foodBean.getTitle());
        titleTv2.setText(foodBean.getTitle());
        descTv.setText(foodBean.getDesc());
        notTv.setText(foodBean.getNotmatch());
        bigPicIv.setImageResource(foodBean.getPicId());

    }

    private void initView() {
        titleTv1=findViewById(R.id.fooddesc_tv_title1);
        titleTv2=findViewById(R.id.fooddesc_tv_title2);
        descTv=findViewById(R.id.fooddesc_tv_desc);
        notTv=findViewById(R.id.fooddesc_tv_not);
        backIv=findViewById(R.id.fooddesc_iv_back);//只有backIv才是销毁当前的activity，从栈当中消失，其余五个与上个页面传过来的数据相关
        bigPicIv=findViewById(R.id.fooddesc_iv_bigpic);
        backIv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                finish();//销毁当前的activity
            }
        });

    }
}