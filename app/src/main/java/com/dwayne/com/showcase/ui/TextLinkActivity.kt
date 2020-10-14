package com.dwayne.com.showcase.ui

import android.content.Context
import android.text.Html
import android.text.SpannableStringBuilder
import android.text.Spanned
import android.text.TextUtils
import android.text.method.LinkMovementMethod
import android.text.style.ClickableSpan
import android.text.style.URLSpan
import android.view.View
import android.widget.TextView
import android.widget.Toast
import butterknife.BindView
import com.dwayne.com.showcase.BaseActivity
import com.dwayne.com.showcase.R

/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/25 23:14
 * @change
 * @chang time
 * @class TextView多个超链接实现跳转
 */
class TextLinkActivity : BaseActivity() {

    @BindView(R.id.tv_link)
    lateinit var tvLink: TextView
    var content = "文本超链接<a href='https://www.google.com/'>Google谷歌</a>及<a " +
            "href='https://www.baidu.com/'>Baidu百度</a>"

    override fun intiLayout(): Int {
        return R.layout.activity_text_link
    }

    override fun initView() {
        tvLink = findViewById(R.id.tv_link)
        //使超链接可点击
        tvLink.movementMethod = LinkMovementMethod.getInstance()
        tvLink.text = setTextLink(this, content)
        initToolBar("TextView多个超链接实现跳转", true)
    }

    override fun initData() {}

    private fun setTextLink(context: Context?,
                            answerstring: String?): SpannableStringBuilder {
        if (!TextUtils.isEmpty(answerstring)) {

            //fromHtml(String source)在Android N中已经弃用，推荐使用fromHtml(String source, int
            // flags)，flags 参数说明，
            // Html.FROM_HTML_MODE_COMPACT：html块元素之间使用一个换行符分隔
            // Html.FROM_HTML_MODE_LEGACY：html块元素之间使用两个换行符分隔
            val htmlString = Html.fromHtml(answerstring, Html.FROM_HTML_MODE_COMPACT)
            if (htmlString is SpannableStringBuilder) {
                //取得与a标签相关的span
                val objs: Array<out URLSpan>? = htmlString.getSpans<URLSpan>(0,
                        htmlString.length, URLSpan::class.java)
                if (null != objs && objs.isNotEmpty()) {
                    for (obj in objs) {
                        val start = htmlString.getSpanStart(obj)
                        val end = htmlString.getSpanEnd(obj)
                        if (obj is URLSpan) {
                            //先移除这个span，再新添加一个自己实现的span。
                            val url = obj.url
                            htmlString.removeSpan(obj)
                            htmlString.setSpan(object : ClickableSpan() {
                                override fun onClick(widget: View) {
                                    //这里可以实现自己的跳转逻辑
                                    Toast.makeText(this@TextLinkActivity, url,
                                            Toast.LENGTH_LONG).show()
                                }
                            }, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE)
                        }
                    }
                }
                return htmlString
            }
        }
        return SpannableStringBuilder(answerstring)
    }
}