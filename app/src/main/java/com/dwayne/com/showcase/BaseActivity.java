package com.dwayne.com.showcase;

import android.os.Bundle;
import androidx.annotation.LayoutRes;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import butterknife.ButterKnife;


/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/26 0:00
 * @change
 * @chang time
 * @class describe
 */

public abstract class BaseActivity extends AppCompatActivity {

    private Toolbar mToolbar;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //设置布局
        setContentView(intiLayout());
        // 初始化View注入
        ButterKnife.bind(this);
        //初始化控件
        initView();
        //设置数据
        initData();
    }

    public void initToolBar(String subTitle, boolean showNavigation) {
        mToolbar = findViewById(R.id.toolbar);
        mToolbar.setTitle(R.string.app_name);
        mToolbar.setTitleTextColor(getResources().getColor(R.color.white));
        mToolbar.setSubtitle(subTitle);
        mToolbar.setSubtitleTextColor(getResources().getColor(R.color.white));
        if(showNavigation) {
            setNavigation();
        }
    }

    private void setNavigation() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp);
        mToolbar.setNavigationOnClickListener(v -> finish());
    }

    @Override
    public void setContentView(@LayoutRes int layoutResID) {
        super.setContentView(layoutResID);
    }

    /**
     * 设置布局
     *
     * @return
     */
    public abstract int intiLayout();

    /**
     * 初始化布局
     */
    public abstract void initView();

    /**
     * 设置数据
     */
    public abstract void initData();

}
