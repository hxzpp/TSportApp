package com.transportmm.tsportapp;

import android.content.Intent;
import android.os.Bundle;
import android.os.PersistableBundle;
import android.os.SystemClock;
import android.support.annotation.NonNull;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.NavigationView;
import android.support.design.widget.Snackbar;
import android.support.v4.view.GravityCompat;
import android.support.v4.widget.DrawerLayout;
import android.support.v7.app.ActionBarDrawerToggle;
import android.support.v7.widget.Toolbar;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Toast;

import com.transportmm.tsportapp.mvp.ui.account.activity.LoginActivity;
import com.transportmm.tsportapp.mvp.ui.search.activity.SearchActivity;
import com.xinhuamm.xinhuasdk.base.activity.HBaseActivity;
import com.xinhuamm.xinhuasdk.di.component.AppComponent;

import butterknife.BindView;

public class MainActivity extends HBaseActivity implements NavigationView.OnNavigationItemSelectedListener {

    //是否关闭侧滑导航
    private static final boolean DRAWER_MODE_CLOSED = false;
    @BindView(R.id.drawer_layout)
    DrawerLayout mDrawerLayout;
    @BindView(R.id.nav_left_view)
    NavigationView mNavigation;
    @BindView(R.id.toolbar)
    Toolbar mToolBar;

    private long mBackPressedTime;

    @Override
    protected int getContentView() {
        return R.layout.activity_main;
    }

    @Override
    protected void initWidget(Bundle savedInstanceState) {
        super.initWidget(savedInstanceState);
        setBackEnable(false);
        setSupportActionBar(mToolBar);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

    }

    @Override
    protected void initData(Bundle savedInstanceState) {
        super.initData(savedInstanceState);

        mNavigation.setNavigationItemSelectedListener(this);
        mDrawerLayout.addDrawerListener(new DrawerLayout.DrawerListener() {

            @Override
            public void onDrawerClosed(View v) {

            }

            @Override
            public void onDrawerOpened(View v) {

            }

            @Override
            public void onDrawerSlide(View drawerView, float slideOffset) {

                // 主体窗口
                View mainFrame = mDrawerLayout.getChildAt(0);

                // 这个就是隐藏起来的边侧滑菜单栏
                View leftMenu = drawerView;

//                ViewHelper.setTranslationX(mainFrame, leftMenu.getMeasuredWidth() * slideOffset);
            }

            @Override
            public void onDrawerStateChanged(int arg0) {

            }
        });

        if (DRAWER_MODE_CLOSED)
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED);
        else {
            mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED);
            ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(
                    this, mDrawerLayout, mToolBar, R.string.navigation_drawer_open, R.string.navigation_drawer_close);
            mDrawerLayout.addDrawerListener(toggle);
            toggle.syncState();
        }

        //以下代码可以处理内存被杀时，fragment恢复的问题，不至于多建几个fragment
        mFragment = findFragment(MainFragment.class.getName());
        if (mFragment == null) {
            addFragment(R.id.fragment_content, new MainFragment(), MainFragment.class.getName());
        }
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.e("hx", "onStart");
    }

    @Override
    protected void onResume() {
        super.onResume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        Log.e("hx", "onPause");
    }

    @Override
    protected void onStop() {
        super.onStop();
        Log.e("hx", "onStop");
    }


    @Override
    protected void onRestart() {
        super.onRestart();
        Log.e("hx", "onRestart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        Log.e("hx", "onRestoreInstanceState");
    }

    @Override
    public void onSaveInstanceState(Bundle outState, PersistableBundle outPersistentState) {
        super.onSaveInstanceState(outState, outPersistentState);
        Log.e("hx", "onSaveInstanceState");
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // Handle navigation view item clicks here.
        int id = item.getItemId();

        if (id == R.id.nav_camera) {
            // Handle the camera action
        } else if (id == R.id.nav_gallery) {
            startActivity(new Intent(this, LoginActivity.class));
        } else if (id == R.id.nav_slideshow) {

        } else if (id == R.id.nav_manage) {

        } else if (id == R.id.nav_share) {

        } else if (id == R.id.nav_send) {

            Intent intent = new Intent(this, SearchActivity.class);
            startActivity(intent);

        }

        mDrawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.main, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean isBackPressed() {
        if (mDrawerLayout.isDrawerOpen(GravityCompat.START)) {
            mDrawerLayout.closeDrawer(GravityCompat.START);
            return false;
        } else {
            long curTime = SystemClock.uptimeMillis();
            if ((curTime - mBackPressedTime) < (3 * 1000)) {
                return isBackPressed();
            } else {
                mBackPressedTime = curTime;
                Toast.makeText(this, R.string.tip_double_click_exit, Toast.LENGTH_LONG).show();
                return false;
            }
        }
    }

    @Override
    public void setupActivityComponent(AppComponent appComponent) {

    }

//    private void switchFragment(Class<?> mcls, int position) {
//
//        if (position != currentFragmentIndex) {
//
//            FragmentManager fm = getSupportFragmentManager();
//            FragmentTransaction ft = fm.beginTransaction();
//
//            //判断当前的Fragment是否为空，不为空则隐藏
//            if (null != fm.findFragmentByTag(TAG + currentFragmentIndex)) {
//                ft.hide(fm.findFragmentByTag(TAG + currentFragmentIndex));
//            }
//
//            //先根据Tag从FragmentTransaction事物获取之前添加的Fragment
//            Fragment targetFragment = fm.findFragmentByTag(TAG + position);
//
//            if (null == targetFragment) {
//                //如fragment为空，则之前未添加此Fragment。便从集合中取出
//                targetFragment = Fragment.instantiate(this,
//                        mcls.getName(), null);
//                Bundle args = new Bundle();
//                CarouselNews carouselNews = mAdapter.getItemAtPosition(position);
//                args.putSerializable("carous",carouselNews);
//                targetFragment.setArguments(args);
//            }
//            //判断此Fragment是否已经添加到FragmentTransaction事物中
//            if (!targetFragment.isAdded()) {
//                ft.add(R.id.home_content, targetFragment, TAG + position);
//            } else {
//                ft.show(targetFragment);
//            }
//
//            ft.commit();
//
//            currentFragmentIndex = position;
//        }
//
//    }
}
