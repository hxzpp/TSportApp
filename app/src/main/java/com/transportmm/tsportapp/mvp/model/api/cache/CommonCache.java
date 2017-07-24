package com.transportmm.tsportapp.mvp.model.api.cache;

import com.transportmm.tsportapp.mvp.model.entity.BaseResult;
import com.transportmm.tsportapp.mvp.model.entity.User;

import java.util.concurrent.TimeUnit;

import io.reactivex.Observable;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictProvider;
import io.rx_cache2.LifeCache;
import io.rx_cache2.Reply;

public interface CommonCache {

    @LifeCache(duration = 2, timeUnit = TimeUnit.MINUTES)
    Observable<Reply<BaseResult<User>>> login(Observable<BaseResult<User>> users, DynamicKey idLastUserQueried, EvictProvider evictProvider);

}
