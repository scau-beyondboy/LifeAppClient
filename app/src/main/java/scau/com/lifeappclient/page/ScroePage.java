package scau.com.lifeappclient.page;

import android.os.Build;
import android.util.Log;
import android.view.View;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.InsertPageLayout;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import java.lang.reflect.Method;

import me.wangyuwei.loadingview.LoadingView;
import scau.com.lifeappclient.R;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2017/3/20.
 */
@InsertPageLayout(value = R.layout.club_website_page,parent = R.id.container)
public class ScroePage extends ToolBarPage {
    private static final String TAG = ScroePage.class.getName();
    @InjectView(R.id.webview)
    private WebView mWebView;
    @InjectView(R.id.progress)
    private LoadingView mProgress;
    private String url;

    public ScroePage(PageActivity pageActivity) {
        super(pageActivity);
        setTitleText("查询成绩");
        mProgress.setVisibility(View.GONE);
    }

    public ScroePage onShown(String data) {
        super.onShown();
        url = data;
        init();
        return this;
    }

    private void init() {
        try {
            if (Build.VERSION.SDK_INT >= 16) {
                Class<?> clazz = mWebView.getSettings().getClass();
                Method method = clazz.getMethod(
                        "setAllowUniversalAccessFromFileURLs", boolean.class);
                if (method != null) {
                    method.invoke(mWebView.getSettings(), true);
                }
            }
            mWebView.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, String url) {
                    view.loadUrl(url);
                    return true;
                }

                @Override
                public void onPageFinished(WebView view, String url) {
                    if (mProgress.isShown()) {
                        mProgress.stop();
                    }
                    Log.d(TAG, "onPageFinished: >>>>>>>>>>>>>"+mProgress.isShown());
                }

                @Override
                public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                    if (mProgress.isShown()) {
                        mProgress.stop();
                    }
                    ToaskUtils.showToast("加载页面错误");
                }
            });
            mWebView.getSettings().setJavaScriptEnabled(true);
            //设置 缓存模式
            mWebView.getSettings().setCacheMode(WebSettings.LOAD_DEFAULT);
            // 开启 DOM storage API 功能
            mWebView.getSettings().setDomStorageEnabled(true);
            mWebView.getSettings().setAllowFileAccess(true);
           // mProgress.start();
            mWebView.loadUrl(url);
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
