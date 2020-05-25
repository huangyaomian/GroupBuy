package com.hym.groupbuy.adapter;

import android.content.Context;
import android.graphics.Paint;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.facebook.drawee.view.SimpleDraweeView;
import com.hym.groupbuy.R;
import com.hym.groupbuy.bean.GoodsInfoBean;
import com.hym.groupbuy.fragment.HomeFragment;

import java.util.List;
import java.util.Map;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xuexiang.xui.utils.ResUtils.getResources;

public class HomeGoodsAdapter extends RecyclerView.Adapter<HomeGoodsAdapter.MyviewHolder> implements View.OnClickListener {



    private List<GoodsInfoBean.GoodlistBean> goodsList;
    private Context mContext;

    // 利用接口 -> 给RecyclerView设置点击事件
    private ItemClickListener mItemClickListener ;
    public interface ItemClickListener{
        public void onItemClick(int position) ;
    }
    public void setOnItemClickListener(ItemClickListener itemClickListener){
        this.mItemClickListener = itemClickListener ;

    }


    public HomeGoodsAdapter(List<GoodsInfoBean.GoodlistBean> goodsList, Context mContext) {
        this.goodsList = goodsList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from (mContext).inflate (R.layout.item_goods_home,parent,false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        GoodsInfoBean.GoodlistBean goodsBean = goodsList.get(position);
//        Log.d("onBindViewHolder",goodsBean.getTitle());
        holder.mItmeGoodsTitle.setText(goodsBean.getTitle());
        holder.mItemGoodsShortTitle.setText(goodsList.get(position).getTitle());
        holder.mItemGoodsPrice.setText("￥" +goodsList.get(position).getPrice());
        holder.mItemGoodsValue.setText("￥" +goodsList.get(position).getValue());
        holder.mItemGoodsValue.setPaintFlags(holder.mItemGoodsValue.getPaintFlags() | Paint.STRIKE_THRU_TEXT_FLAG);
        holder.mItemGoodsBought.setText("已售:" + goodsList.get(position).getBought());
        String[] picUrl = getResources().getStringArray(R.array.pic_url);
        Random random = new Random();
        int i = random.nextInt(16);
        Uri uri = Uri.parse(picUrl[i]);
        holder.mItmeGoodsIv.setImageURI(uri);

        // 点击事件一般都写在绑定数据这里，当然写到上边的创建布局时候也是可以的
        if (mItemClickListener != null){
            holder.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    // 这里利用回调来给RecyclerView设置点击事件
                    mItemClickListener.onItemClick(position);
                }
            });
        }


    }

    @Override
    public int getItemCount() {
        return goodsList != null ? goodsList.size() : 0;
    }



    @Override
    public void onClick(View view) {

    }



    static class MyviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.item_goods_iv)
        SimpleDraweeView mItmeGoodsIv;
        @BindView(R.id.item_goods_title)
        TextView mItmeGoodsTitle;
        @BindView(R.id.item_goods_short_title)
        TextView mItemGoodsShortTitle;
        @BindView(R.id.item_goods_price)
        TextView mItemGoodsPrice;
        @BindView(R.id.item_goods_value)
        TextView mItemGoodsValue;
        @BindView(R.id.item_goods_bought)
        TextView mItemGoodsBought;
        public MyviewHolder(View itemView) {
            super(itemView);
            mItmeGoodsTitle = (TextView) itemView.findViewById(R.id.item_goods_title);
            ButterKnife.bind(this, itemView);
        }




    }





}
