package cn.edu.healthydiet;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager.widget.ViewPager;
import java.util.ArrayList;
import java.util.List;

public class AboutActivity extends AppCompatActivity implements View.OnClickListener {
    ViewPager aboutVp;
    TextView shareTv;//重写Onclick方法，实现点击控件的功能
    LinearLayout pointLayout;//放置小点滚动的线性布局
    List<View> viewList;//Viewpager的数据源，集合类型为view
    int[]picIds={R.mipmap.ab1,R.mipmap.ab2,R.mipmap.ab3,R.mipmap.ab4,R.mipmap.ab5};//五个图片资源
    private AboutAdapter adapter;
    List<ImageView>pointList;//存放显示器小点点的集合

    @SuppressLint("HandlerLeak")
//    handler安卓中消息通讯机制的代表，控制页面跳转功能
    Handler handler = new Handler(){
        @Override
//        重写一个函数接收消息
        public void handleMessage(Message msg) {
            if (msg.what == 1) {
//                接收到消息之后，需要使ViewPager页面向下滑动一页
                int currentItem = aboutVp.getCurrentItem();//获取当前页面
                aboutVp.setCurrentItem(currentItem+1);//设置显示下一页
                handler.sendEmptyMessageDelayed(1,5000);//继续在5秒钟之后发送消息，显示下一页，从而形成自动播放的效果
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_about);
        aboutVp=findViewById(R.id.about_vp);
        shareTv=findViewById(R.id.about_tv_share);
        pointLayout=findViewById(R.id.about_layout_point);
        shareTv.setOnClickListener(this);//为shareTv设置点击事件
        viewList= new ArrayList<>();//对数据源初始化
        pointList=new ArrayList<>();//对小点点进行初始化
        //初始化ViewPager的页面数据信息
        for(int i=0;i<picIds.length;i++){
            View view= LayoutInflater.from(this).inflate(R.layout.item_aboutvp,null);//创建view对象，将item_aboutvp布局转换成view对象，添加到数据源集合中
            ImageView iv=view.findViewById(R.id.item_aboutvp_iv);//找到view包含的imageview
            iv.setImageResource(picIds[i]);//显示数组中的内容,view设置完毕，
            viewList.add(view); // 内容也添加完了,数据源有了
//            创建指示器内容
            ImageView pointIv=new ImageView(this);//传入所在的activity
//            在代码中设置控件宽高和外边距等属性,imageview在linearlayout布局当中
            LinearLayout.LayoutParams lp=new LinearLayout.LayoutParams(LinearLayout.LayoutParams.WRAP_CONTENT, LinearLayout.LayoutParams.WRAP_CONTENT);
            lp.setMargins(0,0,20,0);//点与点之间的间距
//            將布局参数设置给Imageview
            pointIv.setLayoutParams(lp);
            pointIv.setImageResource(R.mipmap.a2);//显示白色点
            pointList.add(pointIv); //添加到集合当中便于统一管理
            pointLayout.addView(pointIv); //添加到布局当中显示出来
        }
        pointList.get(0).setImageResource(R.mipmap.a3);//设置第一个小圆点为选中状态
        //创建适配器对象
        adapter = new AboutAdapter(viewList);//传入viewlist集合得到adapter对象
        //设置适配器，跟列表和网格视图一致
        aboutVp.setAdapter(adapter);
//        发送切换页面消息的代码
        handler.sendEmptyMessageDelayed(1,5000);//handler自己发送消息，自己在上面那个handlermessage接受消息

//        设置ViewPager页面监听器
        setVPListener();//使上面的页面跳转时，下面的小红点也跟着跳转

    }

    private void setVPListener() {
//        设置ViewPager的监听器，传入OnPageChangeListener的对象
      aboutVp.addOnPageChangeListener(new ViewPager.OnPageChangeListener() {
           @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
            }//代表页面发生滚动时，自动调用的方法，一般不用
            @Override
            public void onPageSelected(int position) {
//
                for (int i = 0; i < pointList.size(); i++) {
                    pointList.get(i).setImageResource(R.mipmap.a2);
                }//先将所有的点改成白色不选中
                pointList.get(position%pointList.size()).setImageResource(R.mipmap.a3);//将选中的点改成红色选中状态
            }//代表选中的位置
            @Override
            public void onPageScrollStateChanged(int state) {
            }//滚动状态
        });

    }

    @Override
    public void onClick(View v) {
//        调用系统自带的分享功能
        Intent intent = new Intent(Intent.ACTION_SEND);
        intent.setType("text/plain");//分享为文本类型
        String msg= "健康饮食非常的重要，了解饮食各种营养素和热量，摄入正确的食物，让你变得更健康，想要了解更多么，快来下载健康饮食app吧~~";
        intent.putExtra(Intent.EXTRA_TEXT,msg);
        startActivity(Intent.createChooser(intent,"健康饮食分享"));
    }
}