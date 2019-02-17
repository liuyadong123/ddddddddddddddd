package com.example.dong.ddddddddddddddd.presenter;

import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.example.dong.ddddddddddddddd.R;
import com.example.dong.ddddddddddddddd.adapter.ViewPagerAdapter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class ImageActivity extends AppCompatActivity {
    private Unbinder bind;
    @BindView(R.id.viewpage)
    ViewPager viewpage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_image);
        EventBus.getDefault().register(this);

    }
    @Subscribe(sticky = true)
    public void getImage(String[] image){
        bind = ButterKnife.bind(this);
        ViewPagerAdapter viewPagerAdapter = new ViewPagerAdapter(image ,this);
        viewpage.setAdapter(viewPagerAdapter);
    }

}
