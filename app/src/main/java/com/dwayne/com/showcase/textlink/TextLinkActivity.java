package com.dwayne.com.showcase.textlink;

import android.content.Context;
import android.text.Html;
import android.text.SpannableStringBuilder;
import android.text.Spanned;
import android.text.TextUtils;
import android.text.method.LinkMovementMethod;
import android.text.style.ClickableSpan;
import android.text.style.URLSpan;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.dwayne.com.showcase.BaseActivity;
import com.dwayne.com.showcase.R;

import butterknife.BindView;


/**
 * @author Dwayne
 * @email dev1024@foxmail.com
 * @time 20/4/25 23:14
 * @change
 * @chang time
 * @class TextView多个超链接实现跳转
 */

public class TextLinkActivity extends BaseActivity {

    @BindView(R.id.tv_link)
    TextView tvLink;

    String conent = "文本超链接<a href='https://www.google.com/'>Google谷歌</a>及<a " +
            "href='https://www.baidu.com/'>Baidu百度</a>";

    @Override
    public int intiLayout() {
        return R.layout.activity_text_link;
    }

    @Override
    public void initView() {
        tvLink = findViewById(R.id.tv_link);
        //使超链接可点击
        tvLink.setMovementMethod(LinkMovementMethod.getInstance());
        tvLink.setText(setTextLink(this, conent));

        initToolBar("TextView多个超链接实现跳转", true);
    }

    @Override
    public void initData() {

    }

    public SpannableStringBuilder setTextLink(final Context context,
                                              String answerstring) {
        if(!TextUtils.isEmpty(answerstring)) {

            //fromHtml(String source)在Android N中已经弃用，推荐使用fromHtml(String source, int
            // flags)，flags 参数说明，
            // Html.FROM_HTML_MODE_COMPACT：html块元素之间使用一个换行符分隔
            // Html.FROM_HTML_MODE_LEGACY：html块元素之间使用两个换行符分隔
            Spanned htmlString = Html.fromHtml(answerstring, Html.FROM_HTML_MODE_COMPACT);
            if(htmlString instanceof SpannableStringBuilder) {
                SpannableStringBuilder spannablestringbuilder =
                        (SpannableStringBuilder) htmlString;
                //取得与a标签相关的span
                Object[] objs = spannablestringbuilder.getSpans(0,
                        spannablestringbuilder.length(), URLSpan.class);
                if(null != objs && objs.length != 0) {
                    for(Object obj : objs) {
                        int start = spannablestringbuilder.getSpanStart(obj);
                        int end = spannablestringbuilder.getSpanEnd(obj);
                        if(obj instanceof URLSpan) {
                            //先移除这个span，再新添加一个自己实现的span。
                            URLSpan span = (URLSpan) obj;
                            final String url = span.getURL();
                            spannablestringbuilder.removeSpan(obj);
                            spannablestringbuilder.setSpan(new ClickableSpan() {
                                @Override
                                public void onClick(View widget) {
                                    //这里可以实现自己的跳转逻辑
                                    Toast.makeText(TextLinkActivity.this, url,
                                            Toast.LENGTH_LONG).show();
                                }
                            }, start, end, Spanned.SPAN_INCLUSIVE_EXCLUSIVE);
                        }
                    }
                }
                return spannablestringbuilder;
            }
        }
        return new SpannableStringBuilder(answerstring);
    }
}
