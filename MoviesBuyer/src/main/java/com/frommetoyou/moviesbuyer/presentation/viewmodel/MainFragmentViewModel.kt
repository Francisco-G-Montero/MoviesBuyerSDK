package com.frommetoyou.moviesbuyer.presentation.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.viewModelScope
import com.frommetoyou.moviesbuyer.data.extensions.collectWithLoading
import com.frommetoyou.moviesbuyer.data.model.comics.Comic
import com.frommetoyou.moviesbuyer.data.util.CoroutinesDispatcherProvider
import com.frommetoyou.moviesbuyer.data.util.Event
import com.frommetoyou.moviesbuyer.domain.usecases.GetComicsUseCase
import com.frommetoyou.moviesbuyer.presentation.ui.uimodel.MainFragmentUiModel
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.InternalCoroutinesApi
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import javax.inject.Inject
import com.frommetoyou.moviesbuyer.data.util.Result
import kotlinx.coroutines.ExperimentalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@HiltViewModel
class MainFragmentViewModel @Inject constructor(
    private val coroutinesDispatchers: CoroutinesDispatcherProvider,
    private val getComicsUseCase: GetComicsUseCase
) : BaseViewModel() {
    private val _uiState = MutableLiveData<MainFragmentUiModel>()
    val uiState: LiveData<MainFragmentUiModel>
        get() = _uiState

    fun getMessage(s: String) = viewModelScope.launch {
        emitUiModel(onShowMessageEvent = Event(s))
    }

    fun getComics() = viewModelScope.launch {
        getComicsUseCase().collectWithLoading(_showLoading) { result ->
            when (result) {
                is Result.Success -> {
                    emitUiModel(onUpdateComicList = Event(result.data))
                }
                is Result.Error -> {
                    emitUiModel(onShowMessageEvent = Event(result.errorMessage))
                }
            }
        }
    }

    private suspend fun emitUiModel(
        onShowMessageEvent: Event<String>? = null,
        onUpdateComicList: Event<List<Comic>>? = null,
    ) = withContext(coroutinesDispatchers.main) {
        _uiState.value = MainFragmentUiModel(
            onShowMessageEvent = onShowMessageEvent,
            onUpdateComicList = onUpdateComicList,
        )
    }
}