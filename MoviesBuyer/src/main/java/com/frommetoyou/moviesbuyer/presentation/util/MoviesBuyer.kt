package com.frommetoyou.moviesbuyer.presentation.util

import android.content.Context
import com.frommetoyou.moviesbuyer.presentation.ui.activity.MoviesBuyerActivity

class MoviesBuyer {
    companion object{
        fun startPurchase(context: Context){
            context.startActivity(MoviesBuyerActivity.getIntent(context))
        }
    }
}