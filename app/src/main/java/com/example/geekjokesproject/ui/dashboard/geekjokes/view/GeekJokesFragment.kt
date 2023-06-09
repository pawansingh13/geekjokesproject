package com.example.geekjokesproject.ui.dashboard.geekjokes.view

import android.os.Bundle
import android.os.CountDownTimer
import android.os.Handler
import android.os.Looper
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.geekjokesproject.databinding.FragmentCurrentWeatherBinding
import com.example.geekjokesproject.db.Jokes
import com.example.geekjokesproject.network.Resource
import com.example.geekjokesproject.ui.dashboard.geekjokes.adapter.GeekJokesAdapter
import com.example.geekjokesproject.ui.dashboard.geekjokes.viewmodel.GeekJokesViewModel
import com.example.geekjokesproject.utill.AppConstants
import com.example.geekjokesproject.utill.AppPreference
import com.example.geekjokesproject.utill.AppUtil
import dagger.hilt.android.AndroidEntryPoint
import java.util.*

@AndroidEntryPoint
class GeekJokesFragment : Fragment() {
    val timer = object : CountDownTimer(10000, 1000) {
        override fun onTick(millisUntilFinished: Long) {}

        override fun onFinish() {
            doApiCall()

        }
    }

    private lateinit var adapter: GeekJokesAdapter
    lateinit var jokesList: MutableList<Jokes>
    private val viewModel by viewModels<GeekJokesViewModel>()
    private lateinit var binding: FragmentCurrentWeatherBinding
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentCurrentWeatherBinding.inflate(layoutInflater)
        AppPreference.init(requireContext())
        initAdapter()
        initObserver()
        if (AppPreference.getBooleanValue(AppConstants.OPEN_AGAIN)) {
            viewModel.getAllJokesList(requireContext())
        } else {
            doApiCall()
        }
        return binding.root

    }

    private fun initAdapter() {
        jokesList = mutableListOf()
        binding.rvGeekJokes.layoutManager = LinearLayoutManager(requireContext())
        adapter = GeekJokesAdapter(requireContext())
        binding.rvGeekJokes.adapter = adapter
    }


    override fun onDestroy() {
        stopTimer()
        super.onDestroy()
    }

    override fun onStop() {
        super.onStop()
        AppPreference.writeToSharedPreferences(AppConstants.OPEN_AGAIN, true)
    }
    private fun doApiCall() {
        if (AppUtil.checkForInternet(requireContext())) {
            viewModel.getJokes()
        } else {
            Toast.makeText(
                requireActivity(),
                AppConstants.NO_INTERNET_CONNECTION_AVAILABLE,
                Toast.LENGTH_LONG
            ).show()
        }


    }

    private fun initObserver() {
        viewModel._getAllJokesList.observe(viewLifecycleOwner) {
            if(AppPreference.getBooleanValue(AppConstants.OPEN_AGAIN)){
                adapter.addJokes(it.reversed())
            }else{
                if (it.size > 10) {
                    adapter.addJokes(it.takeLast(10).reversed())
                } else {
                    adapter.addJokes(it.reversed())
                }
            }
            startTimer()
        }

        viewModel.getJokesResponse.observe(viewLifecycleOwner) {
            when (it) {
                is Resource.Loading -> {}
                is Resource.Success -> {
                    it.value.let { jokesResponse ->
                        AppPreference.writeToSharedPreferences(AppConstants.OPEN_AGAIN, false)
                        jokesResponse.joke?.let {
                            viewModel.geekJokesStoreInDB(Jokes(0, jokes = it), requireContext())
                            viewModel.getAllJokesList(requireContext())
                        }
                    }
                }
                is Resource.Failure -> {
                    Toast.makeText(
                        requireActivity(),
                        AppConstants.SOMETHING_WENT_WRONG,
                        Toast.LENGTH_LONG
                    ).show()
                }
            }
        }
    }

    private fun stopTimer() {
        timer.cancel()
    }

    private fun startTimer() {
        timer.start()
    }
}