package com.frommetoyou.moviesbuyer.presentation.extensions

import com.frommetoyou.moviesbuyer.data.model.comics.Thumbnail

fun Thumbnail.getImgPath(): String{
    return "${this.path.replace("http","https")}.${this.extension}"
}