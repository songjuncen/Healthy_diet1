package cn.edu.healthydiet.guide;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;

import java.util.ArrayList;
import java.util.List;

import cn.edu.healthydiet.HomeMenuActivity;
import cn.edu.healthydiet.R;

public class GuideActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager guide_Vp;
    TextView tv1,tv2,tv3;
    Button guideBtn;
    List<View> viewList;//ViewPager的数据源
    List<TextView>numList;//表示页码集合
    int resID[]={R.mipmap.pic1,R.mipmap.pic2,R.mipmap.pic3};//将图片放到数组中，所显示的图片资源数组
    private GuideAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_guide);
//        查找上面定义的控件
        guide_Vp=findViewById(R.id.guide_vp);
        tv1=findViewById(R.id.guide_tv1);
        tv2=findViewById(R.id.guide_tv2);
        tv3=findViewById(R.id.guide_tv3);
        guideBtn=findViewById(R.id.guide_btn);
        guideBtn.setOnClickListener(this);//设置按钮的监听器

//        初始化两个List集合
        viewList=new ArrayList<>();
        numList= new ArrayList<>();

        numList.add(tv1);
        numList.add(tv2);
        numList.add(tv3);


//        初始化ViewPager的页面资源，遍历数组，数组中有一个数据，集合中就有一个数据，有一个就创建一个
        for(int i=0;i< resID.length;i++){
            View view= LayoutInflater.from(this).inflate(R.layout.item_guide1,null);
            view.setBackgroundResource(resID[i]);//设置guide的背景
            viewList.add(view);
        }//数据源初始化完成
//        创建适配器对象
        adapter = new GuideAdapter(viewList);
//        设置适配器
        guide_Vp.setAdapter(adapter);//此时viewpager便可以滑动，text1,2,3也就是，tv1,2,3都未改变，所以监听guide_Vp
        tv1.setTextColor(Color.RED);


//        设置ViewPager监听器的监听
        setVPListener();

    }

    private void setVPListener() {
        guide_Vp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }
            @Override
            public void onPageSelected(int position) {
                for (int i = 0; i < numList.size(); i++) {
                    numList.get(i).setTextColor(Color.WHITE);
                }
                numList.get(position).setTextColor(Color.RED);//只有被选中的位置设置为红色

//                在进入到第三个页面时，立即进入的按钮就显示出来，否则不显示
                if (position == 2) {//现在已经到最后一个界面了
                    guideBtn.setVisibility(View.VISIBLE);
                }else {
                    guideBtn.setVisibility(View.INVISIBLE);
                }
            }//知道现在哪个页面被滑动了
            @Override
            public void onPageScrollStateChanged(int state) {
            }
        });
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.guide_btn:
                //                被点击时跳转到选项界面
                Intent intent = new Intent(GuideActivity.this, HomeMenuActivity.class);
                startActivity(intent);
                //从选项页面返回，也不会返回到引导界面，所以直接finish
                finish();
                break;
        }
    }
}//引导页面，第一次模拟APP进入时，可以对APP的内容有所了解。