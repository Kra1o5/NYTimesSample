package com.randomdroids.nytimes.ui.detail

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.activity.OnBackPressedCallback
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.randomdroids.nytimes.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class DetailFragment : Fragment() {

    companion object {
        private val TAG = DetailFragment::class.qualifiedName
    }

    /** View model */
    private val viewModel: DetailViewModel by viewModels()

    /** View binding */
    private lateinit var binding: FragmentDetailBinding

    private lateinit var adapter: DetailAdapter

    /** Get passed arguments */
    private val args: DetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentDetailBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        adapter = DetailAdapter { viewModel.onArticleClicked(it) }
        binding.recyclerview.adapter = adapter

        lifecycleScope.launchWhenStarted {
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)

            var socialMediaValue = ""
            if (args.socialMediaArg != "NOTUSED") {
                socialMediaValue = args.socialMediaArg
            }

            viewModel.requestListArticles(args.typeArg, args.publishDateArg, socialMediaValue)

            viewModel.articles.onEach { adapter.articlesList = it }.launchIn(this)

            viewModel.navigateToArticleDetail.onEach { articleUrl ->
                if (articleUrl.isNotEmpty()) {
                    val navigateAction =
                        DetailFragmentDirections.actionDetailFragmentToBrowseFragment(articleUrl, args.typeArg, args.publishDateArg, socialMediaValue)
                    findNavController().navigate(navigateAction)
                }
            }.launchIn(this)
        }
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                val navigateAction =
                    DetailFragmentDirections.actionDetailFragmentToMainFragment()
                findNavController().navigate(navigateAction)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }
}