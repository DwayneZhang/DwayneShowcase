package com.dwayne.com.showcase

import android.content.Intent
import android.view.View
import butterknife.OnClick
import com.dwayne.com.showcase.focustest.FocusTestActivity
import com.dwayne.com.showcase.textlink.TextLinkActivity

/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/25 23:10
 * @change
 * @chang time
 * @class 演示代码
 */
class MainActivity : BaseActivity() {
    override fun intiLayout(): Int {
        return R.layout.activity_main
    }

    override fun initView() {
        initToolBar("博客演示代码", false)
    }

    override fun initData() {}

    @OnClick(R.id.tv_text_link, R.id.tv_focus_test)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.tv_text_link -> startActivity(Intent(this, TextLinkActivity::class.java))
            R.id.tv_focus_test -> startActivity(Intent(this, FocusTestActivity::class.java))
        }
    }
}