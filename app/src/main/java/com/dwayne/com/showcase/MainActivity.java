package com.dwayne.com.showcase;

import android.content.Intent;
import android.view.View;

import com.dwayne.com.showcase.focustest.FocusTestActivity;
import com.dwayne.com.showcase.textlink.TextLinkActivity;

import butterknife.OnClick;

/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/25 23:10
 * @change
 * @chang time
 * @class 演示代码
 */


public class MainActivity extends BaseActivity {

    @Override
    public int intiLayout() {
        return R.layout.activity_main;
    }

    @Override
    public void initView() {
        initToolBar("博客演示代码", false);
    }

    @Override
    public void initData() {

    }

    @OnClick({R.id.tv_text_link, R.id.tv_focus_test})
    public void onViewClicked(View view) {
        switch(view.getId()) {
            case R.id.tv_text_link:
                startActivity(new Intent(this, TextLinkActivity.class));
                break;
            case R.id.tv_focus_test:
                startActivity(new Intent(this, FocusTestActivity.class));
                break;
        }
    }
}
