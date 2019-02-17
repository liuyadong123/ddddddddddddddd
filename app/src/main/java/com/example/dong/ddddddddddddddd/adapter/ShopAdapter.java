package com.example.dong.ddddddddddddddd.adapter;

import android.content.Context;
import android.net.Uri;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.example.dong.ddddddddddddddd.R;
import com.example.dong.ddddddddddddddd.bean.ShopBean;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.http.Url;

public class ShopAdapter extends RecyclerView.Adapter<ShopAdapter.UserVH> {
    private List<ShopBean.shopData> list;
    private Context context;

    public ShopAdapter(Context context) {
        this.context = context;
        list=new ArrayList<>();
    }
    public void setList(List<ShopBean.shopData> list) {
        this.list = list;
        notifyDataSetChanged();
    }
    public void addList(List<ShopBean.shopData> list) {
        list.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public ShopAdapter.UserVH onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View view=LayoutInflater.from(context).inflate(R.layout.shop_item,viewGroup,false);
        UserVH userVH =new UserVH(view);
        return userVH;
    }

    @Override
    public void onBindViewHolder(@NonNull ShopAdapter.UserVH userVH, final int i) {
        if (list.size()!=0){
            Uri uri =Uri.parse(list.get(i).masterPic);
            DraweeController controller = Fresco.newDraweeControllerBuilder()
                    .setUri(uri)
                    .setAutoPlayAnimations(true)
                    .build();
            userVH.image.setController(controller);
            userVH.name.setText(list.get(i).commodityName);
            userVH.price.setText(list.get(i).price+"");
            userVH.bzd.setText(list.get(i).commodityId+"");

            userVH.itemView.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    onclickitem.getId(list.get(i).commodityId);
                }
            });
        }

    }

    @Override
    public int getItemCount() {
        return list==null?0:list.size();
    }

    public class UserVH extends RecyclerView.ViewHolder {
        private SimpleDraweeView image;
        private TextView name;
        private TextView price;
        private  TextView bzd;
        public UserVH(@NonNull View itemView) {
            super(itemView);
            image=itemView.findViewById(R.id.image);
            name=itemView.findViewById(R.id.name);
            price=itemView.findViewById(R.id.price);
            bzd=itemView.findViewById(R.id.bzd);
        }
    }
    public interface onClickitem{
        void getId(String id);
    }

    private onClickitem onclickitem;

    public void setOnclickitem(onClickitem onclickitem) {
        this.onclickitem = onclickitem;
    }

}
