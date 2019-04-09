package com.example.rssreader.ui.main2

import android.view.MenuItem
import androidx.core.view.GravityCompat
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import androidx.recyclerview.widget.DividerItemDecoration
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.rssreader.R
import com.example.rssreader.base.BaseActivity
import com.example.rssreader.ui.main.ArticleAdapter
import kotlinx.android.synthetic.main.activity_main.*

class Main2Activity : BaseActivity() {
    private lateinit var viewModel: Main2ViewModel
    private var articleAdapter = ArticleAdapter()

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
        navigationView.menu.getItem(0).isChecked = true
        navigationView.setNavigationItemSelectedListener { menuItem ->
            when (menuItem.itemId) {
                R.id.home -> {
                    // set item as selected to persist highlight
                    viewModel.getHomeArticles()
                    toolbar.title = getString(R.string.home)
                    // close drawer when item is tapped
                    drawerLayout.closeDrawers()
                }
                R.id.news -> {
                    viewModel.getNewsArticles()
                    toolbar.title = getString(R.string.news)
                    drawerLayout.closeDrawers()
                }
                R.id.world -> {
                    viewModel.getWorldArticles()
                    toolbar.title = getString(R.string.world)
                    drawerLayout.closeDrawers()
                }
                R.id.business -> {
                    viewModel.getBusinessArticles()
                    toolbar.title = getString(R.string.business)
                    drawerLayout.closeDrawers()
                }
                R.id.startup -> {
                    viewModel.getStartUpArticles()
                    toolbar.title = getString(R.string.startup)
                    drawerLayout.closeDrawers()
                }
                R.id.entertainment -> {
                    viewModel.getEntertainmentArticles()
                    toolbar.title = getString(R.string.entertainment)
                    drawerLayout.closeDrawers()
                }
                R.id.logout -> {
                    finish()
                }
            }
            return@setNavigationItemSelectedListener true
        }
    }

    private fun addMenuNavigation()
    {
       navigationView.menu.clear()
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
        viewModel.liveData.observe(this, Observer { articleAdapter.submitList(it) })
    }

    override fun onOptionsItemSelected(item: MenuItem?): Boolean {
        when (item!!.itemId) {
            android.R.id.home -> drawerLayout.openDrawer(GravityCompat.START)
        }
        return super.onOptionsItemSelected(item)
    }
}
