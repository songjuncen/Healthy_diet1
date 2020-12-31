package cn.edu.healthydiet.food_grid;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.healthydiet.R;
import cn.edu.healthydiet.bean.FoodBean;

public class FoodGridAdapter extends BaseAdapter {
   Context context;
   List<FoodBean>mDatas;

    public FoodGridAdapter(Context context, List<FoodBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    @Override
    public int getCount() {

        return mDatas.size();
    }//返回条目的数量，也就是集合的长度

    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }//返回指定位置数据

    @Override
    public long getItemId(int position) {
        return position;
    }//返回指定位置

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        //1.声明ViewHolder
        ViewHolder holder=null;
        if (convertView==null){//2.判断是否有复用的view,如果没有就创建
            convertView= LayoutInflater.from(context).inflate(R.layout.item_foodgrid,null);
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else{
            holder= (ViewHolder) convertView.getTag();
        }
        //获取指定位置的数据
        FoodBean foodBean=mDatas.get(position);
        holder.iv.setImageResource(foodBean.getPicId());//设置imageview的显示资源
        holder.tv.setText(foodBean.getTitle());//设置title_tv设置文本显示
        return convertView;
    }

    //把所有控件都封装到一个类当中
    class  ViewHolder{
        ImageView iv;//图像
        TextView tv;//文字
        public ViewHolder(View view){
            iv=view.findViewById(R.id.item_grid_iv);
            tv=view.findViewById(R.id.item_grid_tv);

        }
    }
}
