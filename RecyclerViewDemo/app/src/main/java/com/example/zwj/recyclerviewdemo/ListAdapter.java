package com.example.zwj.recyclerviewdemo;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.List;

/**
 * Created by zwj on 16/4/27.
 */
public class ListAdapter extends RecyclerView.Adapter<ListAdapter.ListViewHolder> {

    private List<DataBean> mDatas;
    private Context mContext;

    public ListAdapter(Context context, List<DataBean> datas) {
        this.mContext = context;
        this.mDatas = datas;
    }

    @Override
    public ListViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        //当viewholder创建时的回调
        View view = View.inflate(mContext, R.layout.item_list, null);
        return new ListViewHolder(view);
    }

    @Override
    public void onBindViewHolder(ListViewHolder holder, int position) {
        //当viewholder和数据绑定时的回调
        //需要有数据
        DataBean bean = mDatas.get(position);
        holder.setData(bean);

    }

    @Override
    public int getItemCount() {
        //返回list数据个数
        if (mDatas != null) {
            return mDatas.size();
        }
        return 0;
    }


    public class ListViewHolder extends RecyclerView.ViewHolder {

        private ImageView ivIcon;
        private TextView tvName;

        public ListViewHolder(View itemView) {
            super(itemView);
            tvName = (TextView) itemView.findViewById(R.id.item_list_tv_name);
            ivIcon = (ImageView) itemView.findViewById(R.id.item_list_iv_icon);
        }

        public void setData(DataBean data) {
            //设置数据的方法
            ivIcon.setImageResource(data.icon);
            tvName.setText(data.name);
        }
    }
}
