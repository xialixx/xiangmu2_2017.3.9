package com.example.administrator.myapplication_class_227.activity;

import android.content.Intent;
import android.support.design.widget.FloatingActionButton;
import android.util.DisplayMetrics;
import android.util.Log;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.JavascriptInterface;
import android.webkit.WebChromeClient;
import android.webkit.WebResourceRequest;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.BaseAdapter;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.administrator.myapplication_class_227.R;
import com.umeng.socialize.ShareAction;
import com.umeng.socialize.UMShareAPI;
import com.umeng.socialize.UMShareConfig;
import com.umeng.socialize.UMShareListener;
import com.umeng.socialize.bean.SHARE_MEDIA;
import com.umeng.socialize.media.UMImage;
import com.umeng.socialize.media.UMWeb;

import java.util.ArrayList;

/**
 * Created by Administrator on 2017/3/2.
 */
public class MyActivity3 extends BaseActivity{
    private WebView webView;
    private ProgressBar progressBar;
    private Intent intent;
    private FloatingActionButton actionButton;
    @Override
    public void addLayout() {
        setContentView(R.layout.layout_myactivity3_son);
    }

    @Override
    public void initView() {
         fanFa();
    }

    @Override
    public void afterInit() {

    }
    public void fanFa(){
        webView= (WebView) findViewById(R.id.webview);
        progressBar= (ProgressBar) findViewById(R.id.progressBar);
        actionButton= (FloatingActionButton) findViewById(R.id.floatingactionbutton);
        intent=getIntent();
        final String str=intent.getStringExtra("url");
        WebSettings webSettings = webView.getSettings();
        //websettings 设置一些功能
        //设置WebView属性
        webSettings.setJavaScriptEnabled(true); // 设置支持javascript脚本
        webSettings.setJavaScriptCanOpenWindowsAutomatically(true);
        webSettings.setUseWideViewPort(true);//关键点
        webSettings.setLayoutAlgorithm(WebSettings.LayoutAlgorithm.SINGLE_COLUMN);
        webSettings.setUseWideViewPort(true);
        webSettings.setAllowFileAccess(true); // 允许访问文件
        webSettings.setBuiltInZoomControls(true); // 设置显示缩放按钮
        webSettings.setSupportZoom(true); // 支持缩放
        webSettings.setLoadWithOverviewMode(true);
        class JsObject {
            @JavascriptInterface
            public String toString() { return "isImmediatePropagationStopped"; }
        }
        webView.addJavascriptInterface(new JsObject(), "isImmediatePropagationStopped");
        DisplayMetrics metrics = new DisplayMetrics();
        getWindowManager().getDefaultDisplay().getMetrics(metrics);
        int mDensity = metrics.densityDpi;
//        Log.d("maomao", "densityDpi = " + mDensity);
        if (mDensity == 240) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        } else if (mDensity == 160) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        } else if(mDensity == 120) {
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.CLOSE);
        }else if(mDensity == DisplayMetrics.DENSITY_XHIGH){
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }else if (mDensity == DisplayMetrics.DENSITY_TV){
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.FAR);
        }else{
            webSettings.setDefaultZoom(WebSettings.ZoomDensity.MEDIUM);
        }
        //加载需要显示的网页
        webView.loadUrl(str);
        //Web视图
        webView.setWebViewClient(new WebViewClient(){
            @Override
            public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                view.loadUrl(str);
                return true;
            }
        });
        //加载progressBar
        webView.setWebChromeClient(new WebChromeClient(){
            @Override
            public void onProgressChanged(WebView view, int newProgress) {
                if (newProgress == 100) {
                    progressBar.setVisibility(webView.GONE);
                } else {
                    if (webView.INVISIBLE == progressBar.getVisibility()) {
                        progressBar.setVisibility(webView.VISIBLE);
                    }
                    progressBar.setProgress(newProgress);
                }
                super.onProgressChanged(view, newProgress);
            }
        });
        actionButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(MyActivity3.this,"你点击了这个圆",Toast.LENGTH_SHORT).show();
                shareWeb("",str,"分享了","");
            }
        });
    }
    @Override
    public void onBackPressed() {
        if(webView.canGoBack()){
            webView.goBack();
        }else {
            super.onBackPressed();
//            Intent intent=new Intent(oneActivity.this,MainActivity.class);
//            startActivity(intent);
//            finish();
        }
    }
    //分享的链接
    private void shareWeb(String thumb_img,String web,String description,String title){
//            UMImage image = new UMImage(MainActivity.this,imageurl);//网络图片
        UMImage thumb = new UMImage(MyActivity3.this,thumb_img);
        UMWeb webb = new UMWeb(web);
//        webb.setThumb(thumb);
        webb.setDescription(description);
//        webb.setTitle(title);
        new ShareAction(MyActivity3.this).withMedia(webb)
                .setDisplayList(SHARE_MEDIA.WEIXIN,SHARE_MEDIA.QZONE,SHARE_MEDIA.QQ,SHARE_MEDIA.SINA)
                .setCallback(new UMShareListener() {
                    @Override
                    public void onStart(SHARE_MEDIA share_media) {
                        Toast.makeText(MyActivity3.this,"开始",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onResult(SHARE_MEDIA share_media) {
                        Toast.makeText(MyActivity3.this,"结束",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onError(SHARE_MEDIA share_media, Throwable throwable) {
                        Toast.makeText(MyActivity3.this,"错误",Toast.LENGTH_SHORT).show();
                    }

                    @Override
                    public void onCancel(SHARE_MEDIA share_media) {
                        Toast.makeText(MyActivity3.this,"退出",Toast.LENGTH_SHORT).show();
                    }
                }).open();
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        UMShareAPI.get(this).onActivityResult(requestCode,resultCode,data);
    }

    private void protecte(){
        Log.e("msg","配置前");
        UMShareConfig config = new UMShareConfig();
        config.isNeedAuthOnGetUserInfo(true);
        config.isOpenShareEditActivity(true);
        config.setSinaAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.setFacebookAuthType(UMShareConfig.AUTH_TYPE_SSO);
        config.setShareToLinkedInFriendScope(UMShareConfig.LINKED_IN_FRIEND_SCOPE_ANYONE);
//        Toast.makeText(MainActivity.this,"开始了",Toast.LENGTH_SHORT).show();
        Log.e("msg","配置后");
    }
}