package com.example.dong.ddddddddddddddd.modle;

import android.net.Uri;

import com.example.dong.ddddddddddddddd.api.Api;
import com.example.dong.ddddddddddddddd.contract.Contract;
import com.example.dong.ddddddddddddddd.net.RequestCallback;
import com.example.dong.ddddddddddddddd.net.RetrofitCallback;
import com.example.dong.ddddddddddddddd.net.RetrofitUtils;

import java.util.HashMap;

public class Modle  implements Contract.IModle {

    @Override
    public void ShopModle(HashMap<String, String> params, final RequestCallback callback) {
        String keyword = params.get("keyword");
        String page = params.get("page");
        String count = params.get("count");
        RetrofitUtils.getintenter().ReGet(Api.Shop_user + "?keyword=" + keyword + "&page=" + page + "&count=" + count, new RetrofitCallback() {
            @Override
            public void ReSuccess(String result) {
                callback.OnSuccess(result);
            }

            @Override
            public void ReFuailr(String msg) {
            callback.OnFailure(msg);
            }
        });
    }

    @Override
    public void XiangModle(String id, final RequestCallback callback) {
        RetrofitUtils.getintenter().ReGet(Api.Xiang_user + "?commodityId=" + id, new RetrofitCallback() {
            @Override
            public void ReSuccess(String result) {
                callback.OnSuccess(result);
            }

            @Override
            public void ReFuailr(String msg) {
                callback.OnFailure(msg);
            }
        });
    }
}
