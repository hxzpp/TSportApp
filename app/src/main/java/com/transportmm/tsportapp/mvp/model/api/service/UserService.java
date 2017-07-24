package com.transportmm.tsportapp.mvp.model.api.service;

import com.transportmm.tsportapp.mvp.model.entity.BaseResult;
import com.transportmm.tsportapp.mvp.model.entity.User;

import io.reactivex.Observable;
import retrofit2.http.Field;
import retrofit2.http.POST;

/**
 * 存放关于用户的一些api
 */
public interface UserService {

    @POST("/user/login")
    Observable<BaseResult<User>> login(@Field("account") String account, @Field("pwd") String pwd);


}
