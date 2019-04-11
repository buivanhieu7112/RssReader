package com.example.rssreader.ui.home

import android.content.Intent
import android.os.Handler
import android.widget.Toast
import com.example.rssreader.R
import com.example.rssreader.base.BaseActivity
import com.example.rssreader.ui.main.MainActivity
import com.example.rssreader.ui.main2.Main2Activity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    private var doubleBackToExitPressedOnce = false

    override fun setUpView() {
        setContentView(R.layout.activity_home)
    }

    override fun bindView() {
        handleItemClick()
    }

    private fun handleItemClick() {
        imageVnExpress.setOnClickListener {
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        image24h.setOnClickListener {
            val intent = Intent(this, Main2Activity::class.java)
            startActivity(intent)
        }
    }

    override fun onBackPressed() {
        if (doubleBackToExitPressedOnce) {
            super.onBackPressed()
            return
        }
        this.doubleBackToExitPressedOnce = true
        Toast.makeText(this, "Please click BACK again to exit", Toast.LENGTH_SHORT).show()
        Handler().postDelayed({ doubleBackToExitPressedOnce = false }, 2000)
    }
}
