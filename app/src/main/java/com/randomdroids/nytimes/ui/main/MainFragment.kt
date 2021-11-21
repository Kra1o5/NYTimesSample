package com.randomdroids.nytimes.ui.main

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import com.randomdroids.nytimes.R
import com.randomdroids.nytimes.data.server.ServerConstants
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

    private var type = ""
    private var publishDate = ""
    private var socialMedia = ""

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
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
            observeNavigation()
            observeChips()
        }

        binding.searchButton.setOnClickListener {
            val chipGroup = binding.chipGroupSocialMedia

            socialMedia = if (chipGroup.isVisible) {
                when {
                    chipGroup.checkedChipIds.size == 2 -> ServerConstants.ALL_SOCIAL
                    chipGroup.checkedChipIds.isNotEmpty() && chipGroup.checkedChipIds[0] == R.id.chipFacebook -> ServerConstants.FACEBOOK
                    chipGroup.checkedChipIds.isNotEmpty() && chipGroup.checkedChipIds[0] == R.id.chipTwitter -> ServerConstants.TWITTER
                    else -> ""
                }
            } else {
                ""
            }
            if (type.isNotBlank() && publishDate.isNotBlank() && (socialMedia.isNotBlank() || !chipGroup.isVisible)) {
                viewModel.dataFilter()
            }
        }
    }

    private fun observeChips() {
        binding.chipGroupPublishDate.setOnCheckedChangeListener { _, checkedId ->
            publishDate = when (checkedId) {
                R.id.chipPublishDateLastDay -> {
                    ServerConstants.LAST_DAY
                }
                R.id.chipPublishDateSeven -> {
                    ServerConstants.LAST_SEVEN_DAYS
                }
                else -> {
                    ServerConstants.LAST_THIRTY_DAYS
                }
            }
        }
        binding.chipGroupType.setOnCheckedChangeListener { _, checkedId ->
            when (checkedId) {
                R.id.chipTypeMostViewed -> {
                    type = ServerConstants.MOST_VIEWED
                    if (binding.chipGroupSocialMedia.isVisible) {
                        binding.textSocialMedia.visibility = View.INVISIBLE
                        binding.chipGroupSocialMedia.visibility = View.INVISIBLE
                    }
                }
                R.id.chipTypeMostEmailed -> {
                    type = ServerConstants.MOST_EMAILED
                    if (binding.chipGroupSocialMedia.isVisible) {
                        binding.textSocialMedia.visibility = View.INVISIBLE
                        binding.chipGroupSocialMedia.visibility = View.INVISIBLE
                    }
                }
                else -> {
                    type = ServerConstants.MOST_SHARED
                    if (!binding.chipGroupSocialMedia.isVisible) {
                        binding.textSocialMedia.visibility = View.VISIBLE
                        binding.chipGroupSocialMedia.visibility = View.VISIBLE
                    }
                }
            }
        }
    }

    /**
     *  Function to observe the navigation stateFlow
     *
     */
    private fun observeNavigation() {
        viewModel.navigateToArticleDetail.onEach { state ->
            if (state) {
                val action = MainFragmentDirections.actionMainFragmentToDetailFragment(
                    type,
                    publishDate,
                    socialMedia
                )
                findNavController().navigate(action)
            }
        }.launchIn(lifecycleScope)
    }
}