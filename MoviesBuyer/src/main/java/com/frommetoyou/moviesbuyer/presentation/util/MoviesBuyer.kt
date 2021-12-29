package com.frommetoyou.moviesbuyer.presentation.util

import android.content.Context
import com.frommetoyou.moviesbuyer.presentation.ui.activity.MainActivity

class MoviesBuyer {
    fun startPurchase(context: Context){
        context.startActivity(MainActivity.getIntent(context))
    }
}