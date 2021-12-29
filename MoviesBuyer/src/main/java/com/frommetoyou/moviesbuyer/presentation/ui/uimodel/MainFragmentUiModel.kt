package com.frommetoyou.moviesbuyer.presentation.ui.uimodel

import com.frommetoyou.moviesbuyer.data.model.comics.Comic
import com.frommetoyou.moviesbuyer.data.util.Event

data class MainFragmentUiModel(
    val onShowMessageEvent: Event<String>? = null,
    val onUpdateComicList: Event<List<Comic>>? = null,
)