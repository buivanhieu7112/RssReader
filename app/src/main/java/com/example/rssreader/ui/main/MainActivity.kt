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
import com.example.rssreader.data.source.model.Article
import com.example.rssreader.utils.Constant
import com.example.rssreader.utils.Constant.ENCODING
import com.example.rssreader.utils.Constant.KEY_WEB_KIND
import com.example.rssreader.utils.Constant.MIME_TYPE
import com.example.rssreader.utils.ItemClickListener
import com.example.rssreader.utils.ItemContextMenuClickListener
import com.example.rssreader.utils.Kind
import com.google.android.material.snackbar.Snackbar
import com.koushikdutta.ion.Ion
import kotlinx.android.synthetic.main.activity_main.*
import java.io.*

class MainActivity : BaseActivity(), ItemClickListener, ItemContextMenuClickListener {
    private var isOnline = true
    private var isItemOffline = false
    private var kind: Kind = Kind.KIND_VN_EXPRESS
    private lateinit var viewModel: MainViewModel
    private var articleAdapter = ArticleAdapter(this, this)

    override fun setUpView() {
        setContentView(R.layout.activity_main)
        setUpToolbar()
        navigationItemClick()
        kind = Kind.from(intent.getIntExtra(KEY_WEB_KIND, 0))
    }

    private fun setUpToolbar() {
        setSupportActionBar(toolbar)
        val actionBar = supportActionBar
        actionBar?.apply {
            setDisplayHomeAsUpEnabled(true)
            setHomeAsUpIndicator(com.example.rssreader.R.drawable.ic_menu)
            title = getString(R.string.home)
        }
    }

    private fun handleItemNavigation(itemOffline: Boolean, urlVnExpress: String?, url24h: String?, title: Int) {
        isItemOffline = itemOffline
        checkInternetConnectionAndHandle()
        if (!itemOffline) {
            when (kind) {
                Kind.KIND_VN_EXPRESS -> viewModel.getNewsArticles(urlVnExpress, Kind.KIND_VN_EXPRESS)
                Kind.KIND_24H -> viewModel.getNewsArticles(url24h, Kind.KIND_24H)
            }
        } else {
            isOnline = false
            imageViewOffline.visibility = View.GONE
            recyclerView.visibility = View.VISIBLE
            when (kind) {
                Kind.KIND_VN_EXPRESS -> viewModel.getLocalArticles(Kind.KIND_VN_EXPRESS)
                Kind.KIND_24H -> viewModel.getLocalArticles(Kind.KIND_24H)
            }
        }
        toolbar.title = getString(title)
        drawerLayout.closeDrawers()
    }

    private fun navigationItemClick() {
        navigationView.menu.getItem(0).isChecked = true
        navigationView.setNavigationItemSelectedListener { menuItem ->
            textViewEmpty.visibility = View.GONE
            when (menuItem.itemId) {
                R.id.home -> {
                    handleItemNavigation(false, Constant.HomeVnExpress, Constant.Home24h, R.string.home)
                }
                R.id.news -> {
                    handleItemNavigation(false, Constant.NewsVnExpress, Constant.News24h, R.string.news)
                }
                R.id.world -> {
                    handleItemNavigation(false, Constant.WorldVnExpress, Constant.World24h, R.string.world)
                }
                R.id.business -> {
                    handleItemNavigation(false, Constant.BusinessVnExpress, Constant.Business24h, R.string.business)
                }
                R.id.startup -> {
                    handleItemNavigation(false, Constant.StartupVnExpress, Constant.Startup24h, R.string.startup)
                }
                R.id.entertainment -> {
                    handleItemNavigation(
                        false,
                        Constant.EntertainmentVnExpress,
                        Constant.Entertainment24h,
                        R.string.entertainment
                    )
                }
                R.id.offline -> {
                    handleItemNavigation(true, null, null, R.string.news_offline)
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
        when (kind) {
            Kind.KIND_VN_EXPRESS -> viewModel.getNewsArticles(Constant.HomeVnExpress, Kind.KIND_VN_EXPRESS)
            Kind.KIND_24H -> viewModel.getNewsArticles(Constant.Home24h, Kind.KIND_24H)
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

    private fun showWebView(article: Article) {
        webViewLayout.visibility = View.VISIBLE
        if (isOnline) {
            if (!isItemOffline) {
                webViewLayout.loadUrl(article.link)
            } else {
                when (kind) {
                    Kind.KIND_VN_EXPRESS -> webViewLayout.loadData(article.guid, MIME_TYPE, ENCODING)
                    Kind.KIND_24H -> webViewLayout.loadData(readFile(this, "${article.id}.html"), MIME_TYPE, ENCODING)
                }
            }
        } else {
            when (kind) {
                Kind.KIND_VN_EXPRESS -> webViewLayout.loadData(article.guid, MIME_TYPE, ENCODING)
                Kind.KIND_24H -> webViewLayout.loadData(readFile(this, "${article.id}.html"), MIME_TYPE, ENCODING)
            }
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
            ex.printStackTrace()
        } finally {
            fileInputStream?.close()
            return retBuf.toString()
        }
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item?.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }

    override fun onItemClicked(article: Article) {
        showWebView(article)
    }

    override fun onItemContextMenuClick(article: Article) {
        saveArticle(article)
    }

    private fun saveArticle(article: Article) {
        Ion.with(applicationContext)
            .load(article.link).asString()
            .setCallback { e, result ->
                // save web content html
                when (kind) {
                    Kind.KIND_VN_EXPRESS -> {
                        article.guid = result
                        article.kind = Kind.KIND_VN_EXPRESS.value
                    }
                    Kind.KIND_24H -> {
                        article.guid = createFileSaveWeb(this, "${article.id}.html", result.substring(34))
                        article.kind = Kind.KIND_24H.value
                    }
                }
                // save to db
                viewModel.saveArticle(article)
            }
    }

    // save web content html to file
    private fun createFileSaveWeb(context: Context, fileName: String, fileData: String): String {
        var pathFile = ""
        val file = File(filesDir, fileName)
        val fileOutputStream = FileOutputStream(file)
        try {
            val outputStreamWriter = OutputStreamWriter(fileOutputStream)
            val bufferedWriter = BufferedWriter(outputStreamWriter)
            bufferedWriter.write(fileData)
            bufferedWriter.flush()
            val path = context.filesDir
            Log.d("FILE", "$path/$fileName")
            pathFile = "$path/$fileName"
        } catch (e: IOException) {
            e.printStackTrace()
        } finally {
            fileOutputStream.close()
        }
        return pathFile
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
            if (!isItemOffline) {
                Snackbar.make(layoutMain, R.string.no_connection, Snackbar.LENGTH_SHORT).show()
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
        if (webViewLayout.isVisible) {
            webViewLayout.clearCache(true)
            webViewLayout.visibility = View.GONE
        } else {
            super.onBackPressed()
        }
    }
}
