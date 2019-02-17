package com.example.dong.ddddddddddddddd;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;


import com.example.dong.ddddddddddddddd.adapter.ShopAdapter;
import com.example.dong.ddddddddddddddd.bean.ShopBean;
import com.example.dong.ddddddddddddddd.bean.ShopXing;
import com.example.dong.ddddddddddddddd.contract.Contract;
import com.example.dong.ddddddddddddddd.presenter.Presenter;
import com.gyf.barlibrary.ImmersionBar;
import com.jcodecraeer.xrecyclerview.XRecyclerView;

import org.greenrobot.eventbus.EventBus;

import java.util.HashMap;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

public class MainActivity extends AppCompatActivity implements Contract.IView,ShopAdapter.onClickitem {

    @BindView(R.id.rv)
    XRecyclerView rv;
    @BindView(R.id.iv)
    ImageView iv;
    @BindView(R.id.tv)
    TextView tv;
    @BindView(R.id.ed)
    EditText ed;
    private Presenter presenter;
    private ShopAdapter shopAdapter;
    private List<ShopBean.shopData> list;
    private  int page=1;
    private  String count="5";
    private Unbinder bind;
    private String id;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        bind = ButterKnife.bind(this);

        initData();
        initView();

    }

    private void initView() {
        shopAdapter =new ShopAdapter(this);
        rv.setAdapter(shopAdapter);
        rv.setLayoutManager(new GridLayoutManager(this,2));
        rv.setPullRefreshEnabled(true);
        rv.setLoadingMoreEnabled(true);
        shopAdapter.setOnclickitem(this);
        rv.setLoadingListener(new XRecyclerView.LoadingListener() {
            @Override
            public void onRefresh() {
                page=1;
                initDataView();
            }

            @Override
            public void onLoadMore() {
                page++;
                initDataView();
            }
        });
        tv.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                page=1;
                initDataView();
            }
        });
    }

    private void initDataView() {
        String s = ed.getText().toString();
        HashMap<String,String> params=new HashMap<>();
        params.put("keyword",s);
        params.put("page",page+"");
        params.put("count",count);
        presenter.Shop(params);
    }

    private void initData() {

        presenter=new Presenter(this);

        
    }

    @Override
    public void ShopSuccess(ShopBean shopBean) {
        if (shopBean != null) {
            if (page == 1) {
                shopAdapter.setList(shopBean.result);
            } else {
                shopAdapter.addList(shopBean.result);
            }
            rv.loadMoreComplete();
            rv.refreshComplete();
        }
    }

    @Override
    public void XiangSuccess(ShopXing shopXing) {
        EventBus.getDefault().postSticky(shopXing);
        startActivity(new Intent(MainActivity.this,ShopActivity.class));
    }

    @Override
    public void ShopFailure(String msg) {

    }

    @Override
    public void getId(String id) {
        this.id = id;
        initShopYiang(id);
    }

    private void initShopYiang(String id) {
        presenter.Xiang(id);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        presenter.Ubind();
    }
}
