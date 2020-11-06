package com.sample.moviesearchengine.ui.detail

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.viewModels
import com.sample.moviesearchengine.R
import com.sample.moviesearchengine.model.Movie
import kotlinx.android.synthetic.main.detail_fragment.*

class DetailFragment : Fragment() {

    companion object {
        fun newInstance() = DetailFragment()
    }

    private val viewModel: DetailViewModel by viewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.detail_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        viewModel.movie = arguments?.get("movie") as Movie

        setupView()
    }

    private fun setupView(){
        textViewTitle.text =  viewModel.movie.title
        textViewOverviewTitle.text = viewModel.movie.original_title
        textViewOverView.text = viewModel.movie.overview
    }

}