package com.example.dong.ddddddddddddddd.contract;

import com.example.dong.ddddddddddddddd.bean.ShopBean;
import com.example.dong.ddddddddddddddd.bean.ShopXing;
import com.example.dong.ddddddddddddddd.net.RequestCallback;

import java.util.HashMap;

public interface Contract {
    abstract  class  ShopPresenter{
        public  abstract  void Shop(HashMap<String,String> params);
        public  abstract  void Xiang(String id);
    }
  interface IModle{
        void ShopModle(HashMap<String,String> params, RequestCallback callback);
        void XiangModle(String id,RequestCallback callback);
  }
  interface  IView{
        void  ShopSuccess(ShopBean shopBean);
        void XiangSuccess(ShopXing shopXing);
        void ShopFailure(String msg);
  }
}
