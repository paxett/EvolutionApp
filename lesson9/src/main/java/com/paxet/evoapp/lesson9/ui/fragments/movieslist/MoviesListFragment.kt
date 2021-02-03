package com.paxet.evoapp.lesson9.ui.fragments.movieslist

import android.content.SharedPreferences
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.widget.EditText
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.paxet.evoapp.lesson9.R

class MoviesListFragment : Fragment(R.layout.fragment_movies_list) {
    val viewModel : MoviesListVM by viewModels()
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val moviesListAdapter = MoviesListAdapter(view)
        initObserver(moviesListAdapter)
        view.findViewById<RecyclerView>(R.id.rv_movies_list).run {
            adapter = moviesListAdapter
            layoutManager = GridLayoutManager(requireContext(), 2)
        }

        //Use shared preferences to store search line
        val pref: SharedPreferences = requireContext().getSharedPreferences("searchLine", 0)
        val editor: SharedPreferences.Editor = pref.edit()
        val searchLineView = view.findViewById<EditText>(R.id.search)

        val mTextWatcher: TextWatcher = object : TextWatcher {
            override fun afterTextChanged(s: Editable?) {
                editor.putString("search_line", searchLineView.text.toString())
                editor.apply()
                viewModel.initTimer(pref.getString("search_line", "") ?: "")
            }

            override fun beforeTextChanged(s: CharSequence?, start: Int, count: Int, after: Int) {
            }

            override fun onTextChanged(s: CharSequence?, start: Int, before: Int, count: Int) {
            }

        }
        viewModel.initConfiguration()
        viewModel.initGenres()
        //Set search line from shared preferences
        searchLineView.addTextChangedListener(mTextWatcher)
        if (pref.getString("search_line", "") == "") {
            viewModel.initMoviesList()
        }
    }

    private fun initObserver(moviesListAdapter: MoviesListAdapter) {
        viewModel.moviesListLD.observe(viewLifecycleOwner, { moviesList ->
            moviesList?.run {
                moviesListAdapter.movies = moviesList
            }
        })
    }
}