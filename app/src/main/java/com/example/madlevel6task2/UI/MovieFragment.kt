package com.example.madlevel6task2.UI

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Button
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import com.bumptech.glide.Glide
import com.example.madlevel6task2.R
import com.example.madlevel6task2.ViewModel.MovieViewModel
import kotlinx.android.synthetic.main.fragment_movie.*

/**
 * A simple [Fragment] subclass as the default destination in the navigation.
 */
class MovieFragment : Fragment() {

    private val viewModel: MovieViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_movie, container, false)
    }

    /**
     * Method which populates the variables and gets the posters
     */
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val index = viewModel.currentSelectedMovie
        val movie = viewModel.movies.value?.get(index)
        if (movie != null) {
            tvOverview.text = movie.overview
            tvRating.text = movie.rating.toString()
            tvRelease.text = movie.releaseDate
            tvTitle.text = movie.title
            Glide.with(requireContext()).load(movie.getPosterUrl()).into(ivPoster)
            Glide.with(requireContext()).load(movie.getBackdropUrl()).into(ivBackdrop)
        }
    }
}