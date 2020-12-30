package cn.edu.healthydiet;

import android.view.View;
import android.view.ViewGroup;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import java.util.List;

public class AboutAdapter extends PagerAdapter {
    List<View>viewList;

    public AboutAdapter(List<View> viewList) {
        this.viewList = viewList;
    }//通过构造函数进行传递，传入数据源

    @Override
    public int getCount() {
         return  Integer.MAX_VALUE;//保证滑动哪五张图片时，连续不断滑下去
    }//        return viewList.size();
    //返回可滑动页面数目，也就是加载多少页，view的长度


    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view==object;
    }//判断生成的view与生成的对象是否是同一个对象

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        View view=viewList.get(position%viewList.size());//保证集合长度的不变，获取角标不会太大，不会越界，从而实现无限滚动
        container.addView(view);
        return view;
    }//拿到指定位置的view添加到指定容器当中,并将view返回

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        View view=viewList.get(position%viewList.size());//保证集合长度的不变，获取角标不会太大，不会越界，从而实现无限滚动
        container.removeView(view);
    }//view离开界面就将其销毁，销毁指定位置的view
}
