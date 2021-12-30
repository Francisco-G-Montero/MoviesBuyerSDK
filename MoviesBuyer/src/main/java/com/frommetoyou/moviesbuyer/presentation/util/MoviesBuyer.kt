package com.frommetoyou.moviesbuyer.presentation.util

import android.content.Context
import android.content.Intent
import androidx.activity.result.ActivityResultLauncher
import com.frommetoyou.moviesbuyer.presentation.ui.activity.MoviesBuyerActivity

class MoviesBuyer {
    companion object{
        fun startPurchase(context: Context, resultLauncher: ActivityResultLauncher<Intent>){
            resultLauncher.launch(MoviesBuyerActivity.getIntent(context))
        }
    }
}