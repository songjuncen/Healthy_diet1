package cn.edu.healthydiet.food_list;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import java.util.ArrayList;
import java.util.List;

import cn.edu.healthydiet.R;
import cn.edu.healthydiet.bean.FoodBean;
import cn.edu.healthydiet.bean.FoodUtils;
import cn.edu.healthydiet.food_grid.FoodDescActivity;

public class InfoListActivity extends AppCompatActivity implements View.OnClickListener {
    EditText searchEt;
    ImageView searchIv,flushIv;
    ListView showLv;
    //    ListView内部数据源
    List<FoodBean>mDatas;
    List<FoodBean>allFoodList;//全部的数据源
    private InfoListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_info_list);
        //        查找控件
        initView();
//        2.找到ListView对应的数据源
        mDatas = new ArrayList<>();
        allFoodList = FoodUtils.getAllFoodList();
        mDatas.addAll(allFoodList);
//        3.创建适配器  BaseAdapter的子类
        adapter = new InfoListAdapter(this, mDatas);
        showLv.setAdapter(adapter); //4.设置适配器
//        设置单向点击监听功能
       setListener();
    }

    private void setListener() {
        showLv.setOnItemClickListener(new AdapterView.OnItemClickListener() {//监听setOnItemClickListener的接口
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
               FoodBean foodBean = mDatas.get(position);//将当前位置的对象传送过去
                Intent intent=new Intent(InfoListActivity.this, FoodDescActivity.class);//传递对象，是从InfoListActivity.this到详情页面的跳转
                intent.putExtra("food",foodBean);//传递可以这个接口这个类可以被序列化的对象
                startActivity(intent);//进行跳转


            }
        });
    }

    private void initView() {
        searchEt = findViewById(R.id.info_et_search);
        searchIv = findViewById(R.id.info_iv_search);
        flushIv = findViewById(R.id.info_iv_flush);
        showLv = findViewById(R.id.infolist_lv);//本地数据，要拿到listview的数据源
        searchIv.setOnClickListener(this); //添加点击事件的监听器
        flushIv.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.info_iv_flush://flush刷新被点击，数据源回到原来的数据源
                searchEt.setText("");
                mDatas.clear();//清空原来的搜索内容
                mDatas.addAll(allFoodList);//搜索到的新的信息放入指定数据源中
                adapter.notifyDataSetChanged();//提示适配器更新


                break;
            case R.id.info_iv_search://搜索被点击
                //1.获取输入内容，判断不为空
                String msg=searchEt.getText().toString().trim();//获取输入信息
                if(TextUtils.isEmpty(msg)){
                    Toast.makeText(this,"输入内容不能为空！",Toast.LENGTH_SHORT).show();
                    return;
                }
                //判断所有食物列表标题标题，是否包含输入内容，如果包含，就添加到小的list集合里
                List<FoodBean>list=new ArrayList<>();
                for(int i=0;i<allFoodList.size();i++) {
                    String title = allFoodList.get(i).getTitle();
                    if (title.contains(msg)) {
                        list.add(allFoodList.get(i));
                    }
                }
                    //清空ListView的适配器数据源的内容
                    mDatas.clear();//原来的数据源清空
                    mDatas.addAll(list);//添加新的数据源中
                    adapter.notifyDataSetChanged();//提示适配器更新
                    break;
        }

    }
}