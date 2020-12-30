package cn.edu.healthydiet.guide;

import android.view.View;
import android.view.ViewGroup;
import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;
import java.util.List;

public class GuideAdapter extends PagerAdapter {
    List<View>viewList;
    private View view;

    public GuideAdapter(List<View> viewList) {
        this.viewList = viewList;
    }//通过构造方法进行传递

    @Override
    public int getCount() {
        return viewList.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object)
    {
        return view==object;
    }

    @NonNull
    @Override
    public Object instantiateItem
            (@NonNull ViewGroup container, int position)
    {
        View view=viewList.get(position);//获取生成view
        container.addView(view);//添加到容器中
        return view;
    }//生成item


    @Override
    public void destroyItem
            (@NonNull ViewGroup container, int position, @NonNull Object object)
    {
    View   view = viewList.get(position);
    container.removeView(view);

    }//销毁指定位置的view
}
