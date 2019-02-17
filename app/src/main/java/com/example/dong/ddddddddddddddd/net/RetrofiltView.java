package com.example.dong.ddddddddddddddd.net;

import java.util.HashMap;

import okhttp3.ResponseBody;
import retrofit2.Call;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.Header;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Url;

public interface RetrofiltView {
    @POST
    @FormUrlEncoded
    Call<ResponseBody> DoPost (@Url String url, @FieldMap HashMap<String,String> params);

    @GET
    Call<ResponseBody> DoGet(@Url String url);


    @PUT
    Call<ResponseBody> updateNickname(@Header("userid") String id, @Header("sessionId") String sid,
                                      @Field("nickname") String name);
}
