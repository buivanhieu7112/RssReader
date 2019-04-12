package com.example.rssreader.ui

import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import com.example.rssreader.R
import kotlinx.android.synthetic.main.web_view_24h.*
import java.io.BufferedReader
import java.io.FileInputStream
import java.io.IOException
import java.io.InputStreamReader

class WebView24h : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.web_view_24h)
        showWebView()

    }

    @SuppressLint("SetJavaScriptEnabled")
    private fun showWebView() {
        webView24h.settings.javaScriptEnabled = true
        val isOnline = intent.getBooleanExtra("ONLINE", false)
        val isItemOffline = intent.getBooleanExtra("ITEM_OFFLINE", false)
        val articleOnline = intent.getStringExtra("KEY_ARTICLE")
        val articleOffline = intent.getStringExtra("KEY_ARTICLE_OFFLINE")
        val id = intent.getLongExtra("KEY_ARTICLE_ID",0)
        if (isOnline) {
            if (!isItemOffline) {
                webView24h.loadUrl(articleOnline)
                Log.d("WEB_ONLINE_ITEM", articleOffline + isOnline)
            } else {
                webView24h.loadData(readFile(this,"$id.html"),"text/html","UTF_8")
            }

        } else {
            webView24h.loadData(readFile(this,"$id.html"),"text/html","UTF_8")
            Log.d("WEB_OFFLINE", articleOffline + isOnline)
        }
    }

    private fun readFile(context: Context, fileName: String): String {
        val fileInputStream = context.openFileInput(fileName)
        return readFromFileInputStream(fileInputStream)
    }

    private fun readFromFileInputStream(fileInputStream: FileInputStream?): String {
        val retBuf = StringBuffer()

        try {
            if (fileInputStream != null) {
                val inputStreamReader = InputStreamReader(fileInputStream)
                val bufferedReader = BufferedReader(inputStreamReader)

                var lineData = bufferedReader.readLine()
                while (lineData != null) {
                    retBuf.append(lineData)
                    lineData = bufferedReader.readLine()
                }
            }
        } catch (ex: IOException) {
        } finally {
            fileInputStream!!.close()
            return retBuf.toString()
        }
    }
}
