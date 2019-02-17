package com.example.dong.ddddddddddddddd.net;

import android.support.v7.widget.RecyclerView;

import com.example.dong.ddddddddddddddd.api.Api;

import java.io.IOException;
import java.util.HashMap;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.ResponseBody;
import okhttp3.logging.HttpLoggingInterceptor;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class RetrofitUtils {
    private static  RetrofitUtils retrofitUtils;
    private final Retrofit retrofit;

    private  RetrofitUtils(){
        HttpLoggingInterceptor httpLoggingInterceptor =new HttpLoggingInterceptor();
        httpLoggingInterceptor.setLevel(HttpLoggingInterceptor.Level.BODY);
        OkHttpClient okHttpClient =new OkHttpClient.Builder()
                .addInterceptor(httpLoggingInterceptor)
                .writeTimeout(5,TimeUnit.SECONDS)
                .readTimeout(5,TimeUnit.SECONDS)
                .connectTimeout(5,TimeUnit.SECONDS)
                .build();
        retrofit = new Retrofit.Builder()
                .baseUrl(Api.Base_user)
                .addConverterFactory(GsonConverterFactory.create())
                .client(okHttpClient)
                .build();
    }

     public  static  RetrofitUtils getintenter(){
         if (retrofitUtils==null){
             synchronized (RetrofitUtils.class){
                 if (retrofitUtils==null){
                     retrofitUtils=new RetrofitUtils();
                 }
             }
         }
         return  retrofitUtils;
     }
     public  void RePost(String url, HashMap<String,String> params, final RetrofitCallback callback){
         RetrofiltView retrofiltView =retrofit.create(RetrofiltView.class);
         retrofiltView.DoPost(url, params).enqueue(new Callback<ResponseBody>() {
             @Override
             public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                 try {
                     if (response.code()==200){
                         String string = response.body().string();
                         if (callback!=null){
                             callback.ReSuccess(string);
                         }
                     }

                 } catch (IOException e) {
                     e.printStackTrace();
                 }
             }

             @Override
             public void onFailure(Call<ResponseBody> call, Throwable t) {
                 if (callback!=null){
                     callback.ReFuailr("请求网路失败");
                 }

             }
         });
     }
     public  void ReGet(String url, final RetrofitCallback callback){
         RetrofiltView retrofiltView =retrofit.create(RetrofiltView.class);
          retrofiltView.DoGet(url).enqueue(new Callback<ResponseBody>() {
              @Override
              public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                  try {
                      if (response.code()==200){
                          String string = response.body().string();
                          if (callback!=null){
                              callback.ReSuccess(string);
                          }
                      }

                  } catch (IOException e) {
                      e.printStackTrace();
                  }
              }

              @Override
              public void onFailure(Call<ResponseBody> call, Throwable t) {
                  if (callback!=null){
                      callback.ReFuailr("请求网路失败");
                  }

              }
          });
     }
    public void doUpdateNickname(String userId,String sessionId, final RetrofitCallback callback) {
        RetrofiltView retrofiltView = retrofit.create(RetrofiltView.class);
        retrofiltView.updateNickname(userId,sessionId,"").enqueue(new Callback<ResponseBody>() {
            @Override
            public void onResponse(Call<ResponseBody> call, Response<ResponseBody> response) {
                try {
                    if (response.code() == 200) {
                        String string = response.body().string();
                        if (callback != null) {
                            callback.ReSuccess(string);
                        }
                    }

                } catch (IOException e) {
                    e.printStackTrace();
                }
            }

            @Override
            public void onFailure(Call<ResponseBody> call, Throwable t) {
                if (callback != null) {
                    callback.ReFuailr("请求网路失败");
                }

            }
        });
    }

}
