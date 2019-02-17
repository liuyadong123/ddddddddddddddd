package com.example.dong.ddddddddddddddd.presenter;

import com.example.dong.ddddddddddddddd.bean.ShopBean;
import com.example.dong.ddddddddddddddd.bean.ShopXing;
import com.example.dong.ddddddddddddddd.contract.Contract;
import com.example.dong.ddddddddddddddd.modle.Modle;
import com.example.dong.ddddddddddddddd.net.RequestCallback;
import com.google.gson.Gson;

import java.lang.ref.WeakReference;
import java.util.HashMap;

public class Presenter extends Contract.ShopPresenter {
    private Modle modle;
    private WeakReference<Contract.IView> weakReference;
    private Contract.IView iView;
    public Presenter(Contract.IView iView){
        weakReference=new WeakReference<>(iView);
        this.iView=weakReference.get();
        modle =new Modle();
    }
    @Override
    public void Shop(HashMap<String, String> params) {
        modle.ShopModle(params, new RequestCallback() {
            @Override
            public void OnSuccess(String result) {
                Gson gson =new Gson();
                ShopBean shopBean = gson.fromJson(result, ShopBean.class);
                iView.ShopSuccess(shopBean);

            }

            @Override
            public void OnFailure(String msg) {
                   iView.ShopFailure(msg);
            }
        });

    }

    @Override
    public void Xiang(String id) {
        modle.XiangModle(id, new RequestCallback() {
            @Override
            public void OnSuccess(String result) {
                ShopXing shopXing = new Gson().fromJson(result, ShopXing.class);
                iView.XiangSuccess(shopXing);

            }

            @Override
            public void OnFailure(String msg) {
                iView.ShopFailure(msg);
            }
        });
    }

    public  void Ubind(){
        weakReference.clear();
        weakReference=null;
        iView=null;
    }
}
