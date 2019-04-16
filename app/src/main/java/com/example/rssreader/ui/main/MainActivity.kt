package com.example.rssreader.ui.main

import android.content.Context
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.core.view.isVisible
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.R
import com.example.rssreader.base.BaseActivity
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h
import com.example.rssreader.utils.ItemClickListener
import com.example.rssreader.utils.ItemContextMenuClickListener
import com.google.android.material.snackbar.Snackbar
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : BaseActivity(), ItemClickListener, ItemContextMenuClickListener {
    private var isOnline = true
    private var itemOffline = false
    private var isVnExpress = true
    private lateinit var viewModel: MainViewModel
    private var articleAdapter = ArticleAdapter(this, this)

    override fun setUpView() {
        setContentView(R.layout.activity_main)
        setUpToolbar()
        navigationItemClick()
        isVnExpress = intent.getBooleanExtra("VN_EXPRESS", true)
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar!!.setDisplayHomeAsUpEnabled(true)
        actionBar.setHomeAsUpIndicator(com.example.rssreader.R.drawable.ic_menu)
        actionBar.title = getString(R.string.home)
    }

    private fun navigationItemClick() {
        // set item as selected to persist highlight
        navigationView.menu.getItem(0).isChecked = true
        navigationView.setNavigationItemSelectedListener { menuItem ->
            textViewEmpty.visibility = View.GONE
            when (menuItem.itemId) {
                R.id.home -> {
                    itemOffline = false
                    checkInternetConnectionAndHandle()
                    if (isVnExpress) {
                        viewModel.getHomeArticles()
                    } else {
                        viewModel.getHomeArticles24h()
                    }
                    toolbar.title = getString(R.string.home)
                    // close drawer when item is tapped
                    drawerLayout.closeDrawers()
                }
                R.id.news -> {
                    itemOffline = false
                    checkInternetConnectionAndHandle()
                    if (isVnExpress) {
                        viewModel.getNewsArticles()
                    } else {
                        viewModel.getNewsArticles24h()
                    }
                    toolbar.title = getString(R.string.news)
                    drawerLayout.closeDrawers()
                }
                R.id.world -> {
                    itemOffline = false
                    checkInternetConnectionAndHandle()
                    if (isVnExpress) {
                        viewModel.getWorldArticles()
                    } else {
                        viewModel.getWorldArticles24h()
                    }
                    toolbar.title = getString(R.string.world)
                    drawerLayout.closeDrawers()
                }
                R.id.business -> {
                    itemOffline = false
                    checkInternetConnectionAndHandle()
                    if (isVnExpress) {
                        viewModel.getBusinessArticles()
                    } else {
                        viewModel.getBusinessArticles24h()
                    }
                    toolbar.title = getString(R.string.business)
                    drawerLayout.closeDrawers()
                }
                R.id.startup -> {
                    itemOffline = false
                    checkInternetConnectionAndHandle()
                    if (isVnExpress) {
                        viewModel.getStartUpArticles()
                    } else {
                        viewModel.getStartUpArticles24h()
                    }
                    toolbar.title = getString(R.string.startup)
                    drawerLayout.closeDrawers()
                }
                R.id.entertainment -> {
                    itemOffline = false
                    checkInternetConnectionAndHandle()
                    if (isVnExpress) {
                        viewModel.getEntertainmentArticles()
                    } else {
                        viewModel.getEntertainmentArticles24h()
                    }
                    toolbar.title = getString(R.string.entertainment)
                    drawerLayout.closeDrawers()
                }
                R.id.offline -> {
                    isOnline = false
                    itemOffline = true
                    checkInternetConnectionAndHandle()
                    imageViewOffline.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    if (isVnExpress) {
                        viewModel.getLocalArticles()
                    } else {
                        viewModel.getLocalArticles24h()
                    }
                    toolbar.title = getString(R.string.news_offline)
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    override fun bindView() {
        initData()
        subscribeUI()
    }

    private fun initData() {
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(MainViewModel::class.java)
        recyclerView.adapter = articleAdapter
        if (isVnExpress) {
            viewModel.getHomeArticles()
        } else {
            viewModel.getHomeArticles24h()
        }
        val dividerItemDecoration = DividerItemDecoration(recyclerView.context, LinearLayoutManager.VERTICAL)
        recyclerView.addItemDecoration(dividerItemDecoration)
    }

    private fun subscribeUI() {
        viewModel.liveData.observe(this, Observer {
            if (it.isNotEmpty()) {
                textViewEmpty.visibility = View.GONE
            } else {
                textViewEmpty.visibility = View.VISIBLE
            }
            articleAdapter.submitList(it)
        })
    }

    private fun showWebViewVnExpress(article: Article) {
        webViewVnExpress.visibility = View.VISIBLE
        if (isOnline) {
            if (!itemOffline) {
                webViewVnExpress.loadUrl(article.link)
            } else {
                webViewVnExpress.loadData(article.guid, "text/html", "UTF-8")
            }
        } else {
            webViewVnExpress.loadDataWithBaseURL(article.link, article.guid, "text/html", "UTF-8", null)
        }
    }

    private fun showWebView24h(article24h: Article24h) {
        webViewVnExpress.visibility = View.VISIBLE
        if (isOnline) {
            if (!itemOffline) {
                webViewVnExpress.loadUrl(article24h.link)
            } else {
                webViewVnExpress.loadData(readFile(this, "${article24h.id}.html"), "text/html", "UTF-8")
            }
        } else {
            webViewVnExpress.loadData(readFile(this, "${article24h.id}.html"), "text/html", "UTF-8")
        }
    }

    private fun readFile(context: Context, fileName: String): String {
        val fileInputStream = context.openFileInput(fileName)
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


    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(article: Article) {
        showWebViewVnExpress(article)
    }

    override fun onItemContextMenuClick(article: Article) {
        saveArticleVnExpress(article)
    }

    override fun onItem24hClicked(article24h: Article24h) {
        showWebView24h(article24h)
    }

    override fun onItemContextMenu24hClick(article24h: Article24h) {
        saveArticle24h(article24h)
    }

    private fun saveArticleVnExpress(article: Article) {
        Ion.with(applicationContext)
            .load(article.link).asString()
            .setCallback { e, result ->
                // save web content html
                article.guid = result
                // save to db
                viewModel.saveArticle(article)
            }
    }

    private fun saveArticle24h(article24h: Article24h) {
        Ion.with(applicationContext)
            .load(article24h.link).asString()
            .setCallback { e, result ->
                // save web content html
                article24h.guid = createFileSaveWeb(this, "${article24h.id}.html", result.substring(34))
                // save to db
                viewModel.saveArticle24h(article24h)
            }
    }

    // save web content html to file
    private fun createFileSaveWeb(context: Context, fileName: String, fileData: String): String {
        val file = File(filesDir, fileName)
        val fileOutputStream = FileOutputStream(file)
        val outputStreamWriter = OutputStreamWriter(fileOutputStream)
        val bufferedWriter = BufferedWriter(outputStreamWriter)
        bufferedWriter.write(fileData)
        bufferedWriter.flush()
        val path = context.filesDir
        Log.d("FILE", "$path/$fileName")
        return "$path/$fileName"
    }

    override fun onResume() {
        super.onResume()
        if (isOnline) {
            checkInternetConnectionAndHandle()
        }
    }

    private fun checkInternetConnectionAndHandle() {
        val conManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetwork: NetworkInfo? = null
        activeNetwork = conManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            isOnline = true
            imageViewOffline.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        } else {
            if (!itemOffline) {
                Snackbar.make(layoutMain, "No connection. You're offline", Snackbar.LENGTH_SHORT).show()
                recyclerView.visibility = View.GONE
                imageViewOffline.visibility = View.VISIBLE
            } else {
                imageViewOffline.visibility = View.GONE
                recyclerView.visibility = View.VISIBLE
            }
            isOnline = false
        }
    }

    override fun onBackPressed() {
        if (webViewVnExpress.isVisible) {
            webViewVnExpress.clearCache(true)
            webViewVnExpress.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}
