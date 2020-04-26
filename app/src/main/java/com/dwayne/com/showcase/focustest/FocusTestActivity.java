package com.dwayne.com.showcase.focustest;

import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dwayne.com.showcase.BaseActivity;
import com.dwayne.com.showcase.R;

import butterknife.BindView;


/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/26 21:07
 * @change
 * @chang time
 * @class focusableInTouchMode="true" 与onClickListener冲突
 */

public class FocusTestActivity extends BaseActivity {

    @BindView(R.id.tv_left)
    TextView tvLeft;
    @BindView(R.id.tvRight)
    TextView tvRight;

    @Override
    public int intiLayout() {
        return R.layout.activity_focus_test;
    }

    @Override
    public void initView() {
        initToolBar("FocusableInTouchMode冲突测试", true);
        tvLeft.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(FocusTestActivity.this, "获取焦点", Toast.LENGTH_SHORT).show();
            }
        });
        tvLeft.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FocusTestActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        tvRight.setOnFocusChangeListener(new View.OnFocusChangeListener() {
            @Override
            public void onFocusChange(View v, boolean hasFocus) {
                Toast.makeText(FocusTestActivity.this, "获取焦点", Toast.LENGTH_SHORT).show();
            }
        });
        tvRight.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(FocusTestActivity.this, "点击事件", Toast.LENGTH_SHORT).show();
            }
        });
        
    }

    @Override
    public void initData() {

    }
}
