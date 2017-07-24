package com.transportmm.tsportapp.mvp.presenter;

import android.app.Application;
import android.content.Context;

import com.transportmm.tsportapp.app.utils.RxUtils;
import com.transportmm.tsportapp.mvp.contract.LoginContract;
import com.transportmm.tsportapp.mvp.model.entity.BaseResult;
import com.transportmm.tsportapp.mvp.model.entity.User;
import com.xinhuamm.xinhuasdk.di.scope.FragmentScope;
import com.xinhuamm.xinhuasdk.integration.AppManager;
import com.xinhuamm.xinhuasdk.mvp.BasePresenter;
import com.xinhuamm.xinhuasdk.utils.HDialogHelper;
import com.xinhuamm.xinhuasdk.widget.imageloader.ImageLoader;

import javax.inject.Inject;

import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.schedulers.Schedulers;
import me.jessyan.rxerrorhandler.core.RxErrorHandler;
import me.jessyan.rxerrorhandler.handler.ErrorHandleSubscriber;
import me.jessyan.rxerrorhandler.handler.RetryWithDelay;


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
public class LoginPresenter extends BasePresenter<LoginContract.Model, LoginContract.View> {
    private RxErrorHandler mErrorHandler;
    private Application mApplication;
    private ImageLoader mImageLoader;
    private AppManager mAppManager;

    @Inject
    public LoginPresenter(LoginContract.Model model, LoginContract.View rootView
            , RxErrorHandler handler, Application application
            , ImageLoader imageLoader, AppManager appManager) {
        super(model, rootView);
        this.mErrorHandler = handler;
        this.mApplication = application;
        this.mImageLoader = imageLoader;
        this.mAppManager = appManager;
    }

    public void login(Context context) {

        HDialogHelper.getProgressDialog(context).show();

        mModel.login("15888033931","1016110")
                .subscribeOn(Schedulers.io())
                .retryWhen(new RetryWithDelay(3, 2))//遇到错误时重试,第一个参数为重试几次,第二个参数为重试的间隔
                .doOnSubscribe(disposable -> {
                        mRootView.showLoading();//显示下拉刷新的进度条
                }).subscribeOn(AndroidSchedulers.mainThread())
                .observeOn(AndroidSchedulers.mainThread())
                .doAfterTerminate(() -> {
                        mRootView.hideLoading();//隐藏下拉刷新的进度条
                })
                .compose(RxUtils.bindToLifecycle(mRootView))//使用Rxlifecycle,使Disposable和Activity一起销毁
                .subscribe(new ErrorHandleSubscriber<BaseResult<User>>(mErrorHandler) {
                    @Override
                    public void onNext(BaseResult<User> user) {

                    }
                });


    }

    @Override
    public void onDestroy() {
        super.onDestroy();
        this.mErrorHandler = null;
        this.mAppManager = null;
        this.mImageLoader = null;
        this.mApplication = null;
    }

}