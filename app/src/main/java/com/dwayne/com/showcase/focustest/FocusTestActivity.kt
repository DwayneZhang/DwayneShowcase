package com.dwayne.com.showcase.focustest

import android.view.View
import android.view.View.OnFocusChangeListener
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.dwayne.com.showcase.BaseActivity
import com.dwayne.com.showcase.R

/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/26 21:07
 * @change
 * @chang time
 * @class focusableInTouchMode="true" 与onClickListener冲突
 */
class FocusTestActivity : BaseActivity() {
    @BindView(R.id.tv_left)
    lateinit var tvLeft: TextView

    @BindView(R.id.tvRight)
    lateinit var tvRight: TextView

    @BindView(R.id.bt_clear)
    lateinit var btClear: Button
    override fun intiLayout(): Int {
        return R.layout.activity_focus_test
    }

    override fun initView() {
        initToolBar("FocusableInTouchMode冲突测试", true)
        tvLeft.onFocusChangeListener = OnFocusChangeListener { _: View?, _: Boolean -> Toast.makeText(this@FocusTestActivity, "获取焦点", Toast.LENGTH_SHORT).show() }
        tvLeft.setOnClickListener {
            Toast.makeText(this@FocusTestActivity, "点击事件",
                    Toast.LENGTH_SHORT).show()
        }
        tvRight.onFocusChangeListener = OnFocusChangeListener { _: View?, _: Boolean -> Toast.makeText(this@FocusTestActivity, "获取焦点", Toast.LENGTH_SHORT).show() }
        tvRight.setOnClickListener {
            Toast.makeText(this@FocusTestActivity, "点击事件",
                    Toast.LENGTH_SHORT).show()
        }
        btClear.setOnClickListener {
            tvLeft.clearFocus()
            tvRight.clearFocus()
        }
    }

    override fun initData() {}
}