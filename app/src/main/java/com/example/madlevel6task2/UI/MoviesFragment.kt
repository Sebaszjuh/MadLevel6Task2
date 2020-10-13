package com.example.madlevel6task2.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.ViewTreeObserver
import android.widget.Toast
import androidx.fragment.app.activityViewModels
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.madlevel6task2.R
import com.example.madlevel6task2.ViewModel.MovieViewModel
import com.example.madlevel6task2.model.Movie
import com.example.madlevel6task2.model.MovieAdapter
import kotlinx.android.synthetic.main.fragment_movies.*

/**
 * A simple [Fragment] subclass as the second destination in the navigation.
 */
class MoviesFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()
    private val movies = arrayListOf<Movie>()
    private val movieSelectAdapter = MovieAdapter(movies) { movie -> onMovieClick(movie) }

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movies, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initView()
        observeMovies()
    }

    private fun initView() {
        val gridLayoutManager = GridLayoutManager(requireContext(), 1, RecyclerView.VERTICAL, false)
        rvMovies.layoutManager = gridLayoutManager
        rvMovies.viewTreeObserver.addOnGlobalLayoutListener(object :
            ViewTreeObserver.OnGlobalLayoutListener {
            override fun onGlobalLayout() {
                rvMovies.viewTreeObserver.removeOnGlobalLayoutListener(this)
                gridLayoutManager.spanCount =2
                gridLayoutManager.requestLayout()
            }
        })

        rvMovies.adapter = movieSelectAdapter

        btn_submit.setOnClickListener { onSubmitClick() }
    }

    private fun observeMovies() {
        viewModel.movies.observe(viewLifecycleOwner, Observer {
            movies.clear()
            movies.addAll(it)
            movieSelectAdapter.notifyDataSetChanged()
        })

        viewModel.errorText.observe(viewLifecycleOwner, Observer {
            Toast.makeText(activity, it, Toast.LENGTH_SHORT).show()
        })
    }

    private fun onSubmitClick() {
        viewModel.getMoviesByYear(inputYear.text.toString())
    }

    private fun onMovieClick(movieIndex: Int) {
        viewModel.setCurrentSelectedMovie(movieIndex)
        findNavController().navigate(R.id.SecondFragment)
    }
}