package com.example.rssreader.ui.home

import android.content.Intent
import android.widget.Toast
import com.example.rssreader.R
import com.example.rssreader.base.BaseActivity
import com.example.rssreader.ui.main.MainActivity
import kotlinx.android.synthetic.main.activity_home.*

class HomeActivity : BaseActivity() {
    override fun setUpView() {
        setContentView(R.layout.activity_home)
    }

    override fun bindView() {
        handleItemClick()
    }

    private fun handleItemClick() {
        imageVnExpress.setOnClickListener {
            Toast.makeText(this, "vnexpress", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
        image24h.setOnClickListener {
            Toast.makeText(this, "24h", Toast.LENGTH_SHORT).show()
            val intent = Intent(this, MainActivity::class.java)
            startActivity(intent)
        }
    }
}
