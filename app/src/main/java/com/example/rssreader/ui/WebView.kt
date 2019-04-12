package com.example.rssreader.ui

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rssreader.R
import kotlinx.android.synthetic.main.web_view.*

class WebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        showWebView()
    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showWebView() {
        webView.settings.javaScriptEnabled = true
        var extras = intent.getBundleExtra("BUNDLE")
        var isOnline = extras.getBoolean("STATUS")
        var articleOnline = extras!!.getString("KEY_ARTICLE")
        var articleOffline = extras.getString("KEY_ARTICLE_OFFLINE")
        var isItemOffline = extras.getBoolean("ITEM_OFFLINE")
        if (isOnline) {

            if (!isItemOffline) {
                webView.loadUrl(articleOnline)
                Log.d("WEB",isOnline.toString() + articleOffline)
            } else {
                webView.loadData(articleOffline, "text/html", "UTF-8")
            }
        } else {
//            webView.loadData(articleOffline, "text/html", "UTF-8")
            webView.loadDataWithBaseURL(articleOnline, articleOffline, "text/html", "UTF-8", null)
            Log.d("WEB1",isOnline.toString() +  articleOffline )
        }
    }
}
