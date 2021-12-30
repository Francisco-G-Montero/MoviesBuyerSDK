package com.frommetoyou.moviesbuyer.presentation.ui.fragment

import android.app.Activity
import android.content.Intent
import androidx.fragment.app.viewModels
import androidx.lifecycle.Observer
import com.frommetoyou.moviesbuyer.R
import com.frommetoyou.moviesbuyer.data.model.comics.Comic
import com.frommetoyou.moviesbuyer.databinding.FragmentMoviesBinding
import com.frommetoyou.moviesbuyer.presentation.ui.adapters.ComicAdapter
import com.frommetoyou.moviesbuyer.presentation.ui.adapters.OnClickListener
import com.frommetoyou.moviesbuyer.presentation.ui.uimodel.MainFragmentUiModel
import com.frommetoyou.moviesbuyer.presentation.viewmodel.MainFragmentViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.InternalCoroutinesApi

@ExperimentalCoroutinesApi
@InternalCoroutinesApi
@AndroidEntryPoint
class MoviesFragment : BaseFragment<FragmentMoviesBinding, MainFragmentViewModel>(), OnClickListener {
    //TODO eliminar clase example ↑
    override val mViewModel: MainFragmentViewModel by viewModels()
    override val layoutId: Int
        get() = R.layout.fragment_movies
    private var comicAdapter: ComicAdapter? = null

    private val uiModelObserver = Observer<MainFragmentUiModel> {
        it.onShowMessageEvent?.consume()?.let { message -> showMessage(message) }
        it.onUpdateComicList?.consume()?.let { comicList -> updateComicAdapter(comicList) }
    }

    override fun setupUiElements() {
        super.setupUiElements()
        setupLeafAdapter()
    }

    override fun setupViewModel() {
        super.setupViewModel()
        mViewModel.uiState.observe(viewLifecycleOwner, uiModelObserver)
        mViewModel.getComics()
    }

    private fun setupLeafAdapter() {
        val adapterVal = ComicAdapter(this)
        comicAdapter = adapterVal
        with(mBinding.recyclerView) {
            adapter = adapterVal
            comicAdapter?.submitList(listOf())
        }
    }

    private fun updateComicAdapter(comicList: List<Comic>) {
        if (comicList.isNotEmpty()) {
            comicAdapter?.submitList(comicList)
        }
    }

    override fun onItemClick(comic: Comic) {
        val data = Intent()
        data.putExtra("comic", "Felicidades, usted ha comprado: ${comic.title}")
        requireActivity().setResult(Activity.RESULT_OK, data)
    }
}