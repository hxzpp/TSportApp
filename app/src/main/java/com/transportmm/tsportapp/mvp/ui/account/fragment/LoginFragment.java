package com.transportmm.tsportapp.mvp.ui.account.fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.transportmm.tsportapp.R;
import com.transportmm.tsportapp.di.component.DaggerLoginComponent;
import com.transportmm.tsportapp.di.module.LoginModule;
import com.transportmm.tsportapp.mvp.contract.LoginContract;
import com.transportmm.tsportapp.mvp.presenter.LoginPresenter;
import com.xinhuamm.xinhuasdk.base.fragment.HBaseFragment;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;
import com.xinhuamm.xinhuasdk.utils.UiUtils;

import butterknife.BindView;
import butterknife.OnClick;

import static com.xinhuamm.xinhuasdk.utils.Preconditions.checkNotNull;

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

public class LoginFragment extends HBaseFragment<LoginPresenter> implements LoginContract.View, View.OnFocusChangeListener {

    @BindView(R.id.ll_login_username)
    LinearLayout mLlLoginUsername;
    @BindView(R.id.et_login_username)
    EditText mEtLoginUsername;
    @BindView(R.id.iv_login_username_del)
    ImageView mIvLoginUsernameDel;

    @BindView(R.id.ll_login_pwd)
    LinearLayout mLlLoginPwd;
    @BindView(R.id.et_login_pwd)
    EditText mEtLoginPwd;
    @BindView(R.id.iv_login_pwd_del)
    ImageView mIvLoginPwdDel;

    @BindView(R.id.btn_login_submit)
    Button mBtLoginSubmit;

    public static LoginFragment newInstance() {
        LoginFragment fragment = new LoginFragment();
        return fragment;
    }

    @Override
    public void setupFragmentComponent(AppComponent appComponent) {
        DaggerLoginComponent
                .builder()
                .appComponent(appComponent)
                .loginModule(new LoginModule(this))//请将LoginModule()第一个首字母改为小写
                .build()
                .inject(this);
    }

    @Override
    protected int getLayoutId() {
        return R.layout.fragment_login;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);

        mEtLoginUsername.setOnFocusChangeListener(this);
        mEtLoginUsername.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                int pwdLength = mEtLoginPwd.length();
                if (length > 0) {
                    mIvLoginUsernameDel.setVisibility(View.VISIBLE);
                    mBtLoginSubmit.setEnabled(pwdLength > 0);
                } else {
                    mIvLoginUsernameDel.setVisibility(View.INVISIBLE);
                    mBtLoginSubmit.setEnabled(false);
                }
            }
        });

        mEtLoginPwd.setOnFocusChangeListener(this);
        mEtLoginPwd.addTextChangedListener(new TextWatcher() {

            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {

            }

            @Override
            public void afterTextChanged(Editable editable) {
                int length = editable.length();
                int nameLength = mEtLoginUsername.length();
                if (length > 0) {
                    mIvLoginPwdDel.setVisibility(View.VISIBLE);
                    mBtLoginSubmit.setEnabled(nameLength > 0);
                } else {
                    mIvLoginPwdDel.setVisibility(View.INVISIBLE);
                    mBtLoginSubmit.setEnabled(false);
                }
            }
        });
    }

    @Override
    public void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);
    }

    /**
     * 此方法是让外部调用使fragment做一些操作的,比如说外部的activity想让fragment对象执行一些方法,
     * 建议在有多个需要让外界调用的方法时,统一传Message,通过what字段,来区分不同的方法,在setData
     * 方法中就可以switch做不同的操作,这样就可以用统一的入口方法做不同的事
     * <p>
     * 使用此方法时请注意调用时fragment的生命周期,如果调用此setData方法时onCreate还没执行
     * setData里却调用了presenter的方法时,是会报空的,因为dagger注入是在onCreated方法中执行的,然后才创建的presenter
     * 如果要做一些初始化操作,可以不必让外部调setData,在initData中初始化就可以了
     *
     * @param data
     */

    @Override
    public void setData(Object data) {

    }

    @OnClick({R.id.iv_login_pwd_del,R.id.iv_login_username_del,R.id.btn_login_submit})
    public void onClick(View v) {
        int id = v.getId();
        switch (id) {
            case R.id.btn_login_submit:
                //用户登录
                mPresenter.login(mContext);
                break;
            case R.id.iv_login_username_del:
                mEtLoginUsername.setText(null);
                break;
            case R.id.iv_login_pwd_del:
                mEtLoginPwd.setText(null);
                break;
            default:
                break;
        }
    }

    @Override
    public void showLoading() {

    }

    @Override
    public void hideLoading() {

    }

    @Override
    public void showMessage(@NonNull String message) {
        checkNotNull(message);
        UiUtils.snackbarText(message);
    }

    @Override
    public void launchActivity(@NonNull Intent intent) {
        checkNotNull(intent);
        UiUtils.startActivity(intent);
    }

    @Override
    public void killMyself() {

    }

    @Override
    public void onFocusChange(View view, boolean b) {
        int id = view.getId();

        if (id == R.id.et_login_username) {
            if (b) {
                mLlLoginUsername.setActivated(true);
                mLlLoginPwd.setActivated(false);
            }
        } else {
            if (b) {
                mLlLoginPwd.setActivated(true);
                mLlLoginUsername.setActivated(false);
            }
        }
    }
}
