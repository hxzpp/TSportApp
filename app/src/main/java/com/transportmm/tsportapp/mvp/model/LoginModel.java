package com.transportmm.tsportapp.mvp.model;

import android.app.Application;

import com.google.gson.Gson;
import com.transportmm.tsportapp.mvp.contract.LoginContract;
import com.transportmm.tsportapp.mvp.model.api.cache.CommonCache;
import com.transportmm.tsportapp.mvp.model.api.service.UserService;
import com.transportmm.tsportapp.mvp.model.entity.BaseResult;
import com.transportmm.tsportapp.mvp.model.entity.User;
import com.xinhuamm.xinhuasdk.di.scope.FragmentScope;
import com.xinhuamm.xinhuasdk.integration.IRepositoryManager;
import com.xinhuamm.xinhuasdk.mvp.BaseModel;

import javax.inject.Inject;

import io.reactivex.Observable;
import io.reactivex.ObservableSource;
import io.reactivex.annotations.NonNull;
import io.reactivex.functions.Function;
import io.rx_cache2.DynamicKey;
import io.rx_cache2.EvictDynamicKey;
import io.rx_cache2.Reply;

/**
 * 通过Template生成对应页面的MVP和Dagger代码,请注意输入框中输入的名字必须相同
 * 由于每个项目包结构都不一定相同,所以每生成一个文件需要自己导入import包名,可以在设置中设置自动导入包名
 * 请在对应包下按以下顺序生成对应代码,Contract->Model->Presenter->Activity->Module->Component
 * 因为生成Activity时,Module和Component还没生成,但是Activity中有它们的引用,所以会报错,但是不用理会
 * 继续将Module和Component生成完后,编译一下项目再回到Activity,按提示修改一个方法名即可
 * 如果想生成Fragment的相关文件,则将上面构建顺序中的Activity换为Fragment,并将Component中inject方法的参数改为此Fragment
 */

/**
 * Created by bill on 17/7/22.
 */

@FragmentScope
public class LoginModel extends BaseModel implements LoginContract.Model {
    private Gson mGson;
    private Application mApplication;

    @Inject
    public LoginModel(IRepositoryManager repositoryManager, Gson gson, Application application) {
        super(repositoryManager);
        this.mGson = gson;
        this.mApplication = application;
    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mGson = null;
        this.mApplication = null;
    }

    @Override
    public Observable<BaseResult<User>> login(String account, String pwd) {
        Observable<BaseResult<User>> users = mRepositoryManager.obtainRetrofitService(UserService.class)
                .login(account, pwd);
        //使用rxcache缓存,上拉刷新则不读取缓存,加载更多读取缓存
        return mRepositoryManager.obtainCacheService(CommonCache.class)
                .login(users
                        , new DynamicKey(account)
                        , new EvictDynamicKey(true))
                .flatMap(new Function<Reply<BaseResult<User>>, ObservableSource<BaseResult<User>>>() {
                    @Override
                    public ObservableSource<BaseResult<User>> apply(@NonNull Reply<BaseResult<User>> listReply) throws Exception {
                        return Observable.just(listReply.getData());
                    }
                });
    }
}