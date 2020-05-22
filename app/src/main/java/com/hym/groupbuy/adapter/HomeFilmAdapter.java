package com.hym.groupbuy.adapter;

import android.content.Context;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.hym.groupbuy.R;
import com.hym.groupbuy.bean.HomeFilmBean;

import java.util.List;
import java.util.Random;

import butterknife.BindView;
import butterknife.ButterKnife;

import static com.xuexiang.xui.utils.ResUtils.getResources;

public class HomeFilmAdapter extends RecyclerView.Adapter<HomeFilmAdapter.MyviewHolder> {



    private List<HomeFilmBean.ResultBean> filmsList;
    private Context mContext;

    public HomeFilmAdapter(List<HomeFilmBean.ResultBean> filmList, Context mContext) {
        this.filmsList = filmList;
        this.mContext = mContext;
    }


    @NonNull
    @Override
    public MyviewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(mContext).inflate(R.layout.item_film_home, parent, false);
        MyviewHolder holder = new MyviewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull MyviewHolder holder, int position) {
        HomeFilmBean.ResultBean resultBean = filmsList.get(position);
        Log.d("onBindViewHolder", resultBean.getFilmName());
        holder.mItemFilmName.setText(resultBean.getFilmName());
        holder.mItemFilmReview.setText(resultBean.getGrade());
        String[] picUrl = getResources().getStringArray(R.array.pic_url);
        Random random = new Random();
        int i = random.nextInt(16);
        Uri uri = Uri.parse(picUrl[i]);
//        Uri uri = Uri.parse("https://storage.qoo-static.com/file/37/097a1f38b191f2005631bd6bd8e91cd6.jpeg");
        holder.mItemFilmIv.setImageURI(uri);

    }

    @Override
    public int getItemCount() {
//        return goodsList != null ? goodsList.size() : 0;
        return filmsList.size();

    }


    static class MyviewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.itme_film_iv)
        ImageView mItemFilmIv;
        @BindView(R.id.item_film_name)
        TextView mItemFilmName;
        @BindView(R.id.item_film_review)
        TextView mItemFilmReview;
        public MyviewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
        }
    }


}
