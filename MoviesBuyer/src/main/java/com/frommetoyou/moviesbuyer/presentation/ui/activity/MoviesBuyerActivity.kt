package com.frommetoyou.moviesbuyer.presentation.ui.activity

import android.content.Context
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.frommetoyou.moviesbuyer.R
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MoviesBuyerActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }

    companion object {
        fun getIntent(context: Context): Intent {
            return Intent(context, MoviesBuyerActivity::class.java).setFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        }
    }

    override fun onBackPressed() {}
}