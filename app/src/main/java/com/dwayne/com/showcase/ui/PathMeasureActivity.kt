package com.dwayne.com.showcase.ui

import android.widget.TextView
import butterknife.BindView
import com.dwayne.com.showcase.BaseActivity
import com.dwayne.com.showcase.R
import com.dwayne.com.showcase.view.WaveView


/**
 *
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/10/11 18:19
 * @change
 * @chang time
 * @class describe
 */

class PathMeasureActivity : BaseActivity() {

    @BindView(R.id.wave_view)
    lateinit var waveView: WaveView

    override fun intiLayout(): Int {
        return R.layout.activity_path_measure

    }

    override fun initView() {
        initToolBar("PathMeasure演示", true)
//        waveView.startAnimation()
    }

    override fun initData() {
    }
}