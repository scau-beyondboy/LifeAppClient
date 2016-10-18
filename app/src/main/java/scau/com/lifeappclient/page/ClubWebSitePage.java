package scau.com.lifeappclient.page;

import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;

import net.neevek.android.lib.paginize.Page;
import net.neevek.android.lib.paginize.PageActivity;
import net.neevek.android.lib.paginize.annotation.InjectView;
import net.neevek.android.lib.paginize.annotation.PageLayout;

import me.wangyuwei.loadingview.LoadingView;
import scau.com.lifeappclient.R;
import scau.com.lifeappclient.utils.ToaskUtils;

/**
 * Created by beyondboy on 2016/10/18.
 */
@PageLayout(R.layout.club_website_page)
public class ClubWebSitePage extends Page {
    @InjectView(R.id.webview)
    private WebView mWebView;
    @InjectView(R.id.progress)
    private LoadingView mProgress;
    private  String url;
    public ClubWebSitePage(PageActivity pageActivity) {
        super(pageActivity);
    }

    public ClubWebSitePage onShown(String data) {
        super.onShown();
        url=data;
        init();
        return this;
    }

    private void init(){
        mWebView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view,String url) {
                view.loadUrl(url);
                return true;
            }

            @Override
            public void onPageFinished(WebView view, String url) {
                if(mProgress.isShown()){
                    mProgress.stop();
                }
            }

            @Override
            public void onReceivedError(WebView view, int errorCode, String description, String failingUrl) {
                if(mProgress.isShown()){
                    mProgress.stop();
                }
                ToaskUtils.showToast("加载页面错误");
            }
        });
        mWebView.getSettings().setJavaScriptEnabled(true);
        mWebView.loadUrl(url);
        mProgress.start();
    }
}
