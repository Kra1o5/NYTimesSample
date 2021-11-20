package com.randomdroids.nytimes.ui.main

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.randomdroids.nytimes.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch

@AndroidEntryPoint
class MainFragment : Fragment() {

    companion object {
        private val TAG = MainFragment::class.qualifiedName
    }

    /** View model */
    private val viewModel: MainViewModel by viewModels()

    /** View binding */
    private lateinit var binding: FragmentMainBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentMainBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        viewLifecycleOwner.lifecycleScope.launch {
            observeArticles()
            viewModel.requestListArticles()
        }
    }
    /**
     *  Function to observe the articles stateFlow
     *
     */
    private fun observeArticles() {
        viewModel.articles.onEach {
            Log.d(TAG, it.toString())
        }.launchIn(lifecycleScope)
    }
}