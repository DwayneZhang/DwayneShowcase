package com.dwayne.com.showcase

import android.content.Intent
import android.view.View
import android.widget.TextView
import butterknife.OnClick
import com.dwayne.com.showcase.ui.FocusTestActivity
import com.dwayne.com.showcase.ui.PathMeasureActivity
import com.dwayne.com.showcase.ui.TextLinkActivity

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
//        findViewById<TextView>(R.id.tv_path_measure).setOnClickListener{
//            startActivity(Intent(this, PathMeasureActivity::class.java))
//        }
    }

    override fun initData() {}

    @OnClick(R.id.tv_text_link, R.id.tv_focus_test, R.id.tv_path_measure)
    fun onViewClicked(view: View) {
        when (view.id) {
            R.id.tv_text_link -> startActivity(Intent(this, TextLinkActivity::class.java))
            R.id.tv_focus_test -> startActivity(Intent(this, FocusTestActivity::class.java))
            R.id.tv_path_measure -> startActivity(Intent(this, PathMeasureActivity::class.java))
        }
    }
}