package com.frommetoyou.moviesbuyer.presentation.ui.adapters

import com.frommetoyou.moviesbuyer.data.model.comics.Comic


interface OnClickListener {
    fun onItemClick(comic: Comic)
}