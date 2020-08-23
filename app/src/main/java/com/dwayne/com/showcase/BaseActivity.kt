package com.dwayne.com.showcase

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import butterknife.ButterKnife

/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/26 0:00
 * @change
 * @chang time
 * @class describe
 */
abstract class BaseActivity : AppCompatActivity() {
    private lateinit var mToolbar: Toolbar
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //设置布局
        setContentView(intiLayout())
        // 初始化View注入
        ButterKnife.bind(this)
        //初始化控件
        initView()
        //设置数据
        initData()
    }

    fun initToolBar(subTitle: String?, showNavigation: Boolean) {
        mToolbar = findViewById(R.id.toolbar)
        mToolbar.setTitle(R.string.app_name)
        mToolbar.setTitleTextColor(resources.getColor(R.color.white, null))
        mToolbar.subtitle = subTitle
        mToolbar.setSubtitleTextColor(resources.getColor(R.color.white, null))
        if (showNavigation) {
            setNavigation()
        }
    }

    private fun setNavigation() {
        mToolbar.setNavigationIcon(R.drawable.ic_arrow_back_black_24dp)
        mToolbar.setNavigationOnClickListener { finish() }
    }

    /**
     * 设置布局
     *
     * @return
     */
    abstract fun intiLayout(): Int

    /**
     * 初始化布局
     */
    abstract fun initView()

    /**
     * 设置数据
     */
    abstract fun initData()
}