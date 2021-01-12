package com.paxet.evoapp.lesson7.ui.fragments.movieslist

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
import com.paxet.evoapp.lesson7.R
import java.util.*

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
            private var timer = Timer()
            private val DELAY: Long = 1000L

            override fun afterTextChanged(s: Editable?) {
                timer.cancel()
                timer = Timer()
                timer.schedule(object : TimerTask() {
                    override fun run() {
                        val searchLine = view.findViewById<EditText>(R.id.search).text.toString()
                        if(searchLine == "") {
                            viewModel.initMoviesList()
                            //Clear search line in shared preferences
                            editor.remove("search_line")
                            editor.commit()
                        } else {
                            viewModel.searchMoviesList(searchLine)
                            //Store search line via shared preferences
                            editor.putString("search_line", searchLine)
                            editor.commit()
                        }
                    }
                }, DELAY)
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
        searchLineView.setText(pref.getString("search_line", ""))
    }

    private fun initObserver(moviesListAdapter: MoviesListAdapter) {
        viewModel.moviesListLD.observe(viewLifecycleOwner, { moviesList ->
            moviesList?.run {
                moviesListAdapter.movies = moviesList
            }
        })
    }
}