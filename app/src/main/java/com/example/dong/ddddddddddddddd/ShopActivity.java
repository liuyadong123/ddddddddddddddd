package com.example.dong.ddddddddddddddd;

import android.content.Intent;
import android.net.Uri;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

import com.example.dong.ddddddddddddddd.bean.ShopXing;
import com.example.dong.ddddddddddddddd.presenter.ImageActivity;
import com.facebook.drawee.backends.pipeline.Fresco;
import com.facebook.drawee.interfaces.DraweeController;
import com.facebook.drawee.view.SimpleDraweeView;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

public class ShopActivity extends AppCompatActivity {
    private Unbinder bind;

    @BindView(R.id.image)
    SimpleDraweeView image;
    @BindView(R.id.tv_name)
    TextView tv_name;
    private String[] split;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_shop);

        EventBus.getDefault().register(this);
    }
    @Subscribe(sticky = true)
    public void getShopData(ShopXing shopXing){
        bind = ButterKnife.bind(this);
        split = shopXing.result.picture.split(",");
        Uri uri =Uri.parse(split[0]);
        tv_name.setText(shopXing.result.commodityName);

        DraweeController draweeController =Fresco.newDraweeControllerBuilder()
                .setUri(uri)
                .setAutoPlayAnimations(true)
                .build();
         image.setController(draweeController);


    }
    @OnClick(R.id.image)
    public void onClickImage(View view){
        EventBus.getDefault().postSticky(split);
        startActivity(new Intent(ShopActivity.this,ImageActivity.class));
    }
}
