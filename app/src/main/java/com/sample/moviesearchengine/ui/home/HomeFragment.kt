package com.sample.moviesearchengine.ui.home

import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.liveData
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.sample.moviesearchengine.R
import com.sample.moviesearchengine.model.Movie
import com.sample.moviesearchengine.ui.home.adapter.MovieAdapter
import com.sample.moviesearchengine.utils.Progress
import kotlinx.android.synthetic.main.home_fragment.*
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class HomeFragment : Fragment(), MovieAdapter.Listener {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()
    private lateinit var mAdapter:MovieAdapter

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.home_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)

        setupRecyclerView()

        editSearchMovie.addTextChangedListener(object : TextWatcher {
            override fun afterTextChanged(s: Editable) {}
            override fun beforeTextChanged(
                s: CharSequence, start: Int,
                count: Int, after: Int
            ) {
            }

            override fun onTextChanged(
                s: CharSequence, start: Int,
                before: Int, count: Int
            ) {
                pullMovies(s.toString())
            }
        })


    }

    private fun setupRecyclerView() {
        mAdapter = MovieAdapter(this)
        recyclerMovies.apply {
            adapter = mAdapter
            layoutManager = LinearLayoutManager(requireContext())
        }

    }

    private fun pullMovies(title:String){

        viewModel.ioScope.launch {
            val it = viewModel.getMoviesByTitle(title)

            when (it.status) {
                Progress.Status.SUCCESS -> {
                 //   progressView.isRefreshing = false
                    if (!it.data.isNullOrEmpty()) mAdapter.setItems(ArrayList(it.data[0].results))
                }
                Progress.Status.ERROR ->
                    Toast.makeText(requireContext(), it.message, Toast.LENGTH_SHORT).show()

               // Progress.Status.LOADING -> progressView.isRefreshing = true
            }

            withContext(Dispatchers.Main) {

            }
        }
    }

    override fun onItemClick(movie: Movie) {
        findNavController().navigate(
            R.id.action_homeFragment_to_detailFragment,
            bundleOf("movie" to movie))
    }

}