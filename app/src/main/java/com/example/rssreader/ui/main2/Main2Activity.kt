package com.example.rssreader.ui.main2

import android.content.Context
import android.content.Intent
import android.net.ConnectivityManager
import android.net.NetworkInfo
import android.util.Log
import android.view.MenuItem
import android.view.View
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.R
import com.example.rssreader.base.BaseActivity
import com.example.rssreader.data.source.model.VnExpress.Article
import com.example.rssreader.data.source.model._24h.Article24h
import com.example.rssreader.ui.WebView24h
import com.example.rssreader.utils.ItemClickListener
import com.example.rssreader.utils.ItemContextMenuClickListener
import com.google.android.material.snackbar.Snackbar
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import java.io.BufferedWriter
import java.io.File
import java.io.FileOutputStream
import java.io.OutputStreamWriter


class Main2Activity : BaseActivity(), ItemClickListener, ItemContextMenuClickListener {
    private var isOnline = true
    private var itemOffline = false
    private lateinit var viewModel: Main2ViewModel
    private var articleAdapter = Article24hAdapter(this, this)

    override fun setUpView() {
        setContentView(R.layout.activity_main)
        setUpToolbar()
        navigationItemClick()
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
                    checkInternetConnection()
                    viewModel.getHomeArticles()
                    toolbar.title = getString(R.string.home)
                    // close drawer when item is tapped
                    drawerLayout.closeDrawers()
                }
                R.id.news -> {
                    itemOffline = false
                    checkInternetConnection()
                    viewModel.getNewsArticles()
                    toolbar.title = getString(R.string.news)
                    drawerLayout.closeDrawers()
                }
                R.id.world -> {
                    itemOffline = false
                    checkInternetConnection()
                    viewModel.getWorldArticles()
                    toolbar.title = getString(R.string.world)
                    drawerLayout.closeDrawers()
                }
                R.id.business -> {
                    itemOffline = false
                    checkInternetConnection()
                    viewModel.getBusinessArticles()
                    toolbar.title = getString(R.string.business)
                    drawerLayout.closeDrawers()
                }
                R.id.startup -> {
                    itemOffline = false
                    checkInternetConnection()
                    viewModel.getStartUpArticles()
                    toolbar.title = getString(R.string.startup)
                    drawerLayout.closeDrawers()
                }
                R.id.entertainment -> {
                    itemOffline = false
                    checkInternetConnection()
                    viewModel.getEntertainmentArticles()
                    toolbar.title = getString(R.string.entertainment)
                    drawerLayout.closeDrawers()
                }
                R.id.offline -> {
                    itemOffline = true
                    checkInternetConnection()
                    isOnline = false
                    imageViewOffline.visibility = View.GONE
                    recyclerView.visibility = View.VISIBLE
                    viewModel.getLocalArticles()
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
        viewModel = ViewModelProviders.of(this, viewModelFactory).get(Main2ViewModel::class.java)
        viewModel.getHomeArticles()
        recyclerView.adapter = articleAdapter
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

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(article: Article) {
//        val intent = Intent(this, WebView24h::class.java)
//        intent.putExtra("ONLINE", isOnline)
//        intent.putExtra("ITEM_OFFLINE", itemOffline)
//        intent.putExtra("KEY_ARTICLE", article.link)
//        intent.putExtra("KEY_ARTICLE_OFFLINE", article.guid)
//        intent.putExtra("KEY_ARTICLE_ID", article.id)
//        startActivity(intent)
    }

    override fun onItemContextMenuClick(article: Article) {
//        saveArticle(article)
    }

    override fun onItem24hClicked(article24h: Article24h) {
        val intent = Intent(this, WebView24h::class.java)
        intent.putExtra("ONLINE", isOnline)
        intent.putExtra("ITEM_OFFLINE", itemOffline)
        intent.putExtra("KEY_ARTICLE", article24h.link)
        intent.putExtra("KEY_ARTICLE_OFFLINE", article24h.guid)
        intent.putExtra("KEY_ARTICLE_ID", article24h.id)
        startActivity(intent)
    }

    override fun onItemContextMenu24hClick(article24h: Article24h) {
        saveArticle(article24h)
    }

    private fun saveArticle(article: Article24h) {
        Ion.with(applicationContext)
            .load(article.link).asString()
            .setCallback { e, result ->
                // save web content html
                article.guid = createFileSaveWeb(this, "${article.id}.html", result.substring(34))
                // save to db
                viewModel.saveArticle(article)
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
            checkInternetConnection()
        }
    }

    private fun checkInternetConnection() {
        val conManager: ConnectivityManager = getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        var activeNetwork: NetworkInfo? = null
        activeNetwork = conManager.activeNetworkInfo
        if (activeNetwork != null && activeNetwork.isConnected) {
            isOnline = true
            imageViewOffline.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
        } else {
            isOnline = false
            if (!itemOffline) {
                Snackbar.make(layoutMain, "No connection. You're offline", Snackbar.LENGTH_SHORT).show()
                recyclerView.visibility = View.GONE
                imageViewOffline.visibility = View.VISIBLE
            } else {
                recyclerView.visibility = View.VISIBLE
                imageViewOffline.visibility = View.GONE
            }
        }
    }
}
