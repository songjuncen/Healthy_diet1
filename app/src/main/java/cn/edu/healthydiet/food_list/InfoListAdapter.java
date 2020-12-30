package cn.edu.healthydiet.food_list;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;//这是listview和gridview的基类
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

import cn.edu.healthydiet.R;
import cn.edu.healthydiet.bean.FoodBean;

public class InfoListAdapter  extends BaseAdapter {
    Context context;
   List<FoodBean>mDatas;

    public InfoListAdapter(Context context, List<FoodBean> mDatas) {
        this.context = context;
        this.mDatas = mDatas;
    }

    //构造方法，决定了Listview列表展示的行数
    @Override
    public int getCount() {
        return mDatas.size();
    }//集合的长度就是展示的行数


//返回指定位置对应的数据
    @Override
    public Object getItem(int position) {
        return mDatas.get(position);
    }

//返回指定位置对应id
    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        ViewHolder holder = null;
        if (convertView == null) {//在内存中判断是否有已经滑动出去的这条view,如果有就用内存中已经滑动出去的view节约内存资源，每次最多创建8条view,因为只能展示8条

            convertView = LayoutInflater.from(context).inflate(R.layout.item_infolist_lv,null); ////它为空，就是第一次进来，没有复用，将布局转换成view对象的方法
            holder=new ViewHolder(convertView);
            convertView.setTag(holder);
        }else {//convertview不为空，就复用，返回最后一行的view的类型。
            holder = (ViewHolder) convertView.getTag();
        }
//        加载控件显示的内容
//        获取集合指定位置的数据
        FoodBean foodBean = mDatas.get(position);
        holder.titleTv.setText(foodBean.getTitle());
        holder.notTv.setText("不可匹配:"+foodBean.getNotmatch());
        holder.iv.setImageResource(foodBean.getPicId());//这三句话显示每一个item显示的内容
        return convertView;
    }

    //将已经划出屏幕的行数进行复用，已经划出的每一行就是一个新的view,将这个view所有控件都放到下面这个类中封装

    class ViewHolder{
        ImageView iv;
        TextView titleTv,notTv;
        public ViewHolder(View view){
            iv = view.findViewById(R.id.item_info_iv);
            titleTv = view.findViewById(R.id.item_info_tv_title);
            notTv = view.findViewById(R.id.item_info_tv_notmatch);
        }
    }//构造函数进行传递


}//
