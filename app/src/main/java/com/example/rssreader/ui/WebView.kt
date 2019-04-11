package com.example.rssreader.ui

import android.annotation.SuppressLint
import android.os.Bundle
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
        val extras = intent.getBundleExtra("BUNDLE")
        val status = extras.getBoolean("STATUS")
        val articleOnline = extras!!.getString("KEY_ARTICLE")
        val articleOffline = extras.getString("KEY_ARTICLE_OFFLINE")
        if (status) {
            webView.loadUrl(articleOnline)
        } else {
            webView.loadData(articleOffline, "text/html", "UTF-8")
        }
    }
}
