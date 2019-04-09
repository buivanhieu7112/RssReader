package com.example.rssreader.ui

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rssreader.R
import kotlinx.android.synthetic.main.web_view.*

class WebView : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view)
        showWebView()
    }
    @SuppressLint("SetJavaScriptEnabled")
    private fun showWebView(){
        webView.settings.javaScriptEnabled = true
        webView.loadUrl(intent.extras!!.getString("KEY_ARTICLE"))
    }
}
