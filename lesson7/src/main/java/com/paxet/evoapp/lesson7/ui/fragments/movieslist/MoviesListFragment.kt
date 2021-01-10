package com.paxet.evoapp.lesson7.ui.fragments.movieslist

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paxet.evoapp.lesson7.R

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    val viewModel : MoviesListVM by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val moviesListAdapter = MoviesListAdapter(view)
        initObserver(moviesListAdapter)
        view.findViewById<RecyclerView>(R.id.rv_movies_list).run {
            adapter = moviesListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }
        viewModel.initConfiguration()
        viewModel.initGenres()
        viewModel.initMoviesList()
    }

    private fun initObserver(moviesListAdapter : MoviesListAdapter) {
        viewModel.moviesListLD.observe(viewLifecycleOwner, {moviesList ->
            moviesList?.run{
                moviesListAdapter.movies = moviesList
            }
        })
    }
}