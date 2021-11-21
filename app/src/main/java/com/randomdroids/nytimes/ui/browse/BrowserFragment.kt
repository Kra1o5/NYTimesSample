package com.randomdroids.nytimes.ui.browse

import android.content.ComponentName
import android.content.Context
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.activity.OnBackPressedCallback
import androidx.browser.customtabs.*
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import com.randomdroids.nytimes.R
import com.randomdroids.nytimes.databinding.FragmentBrowserBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.withContext


@AndroidEntryPoint
class BrowserFragment : Fragment() {

    companion object {
        private val TAG = BrowserFragment::class.qualifiedName
        private const val CUSTOM_TAB_PACKAGE_NAME = "com.android.chrome"
    }

    /** View model */
    private val viewModel: BrowserViewModel by viewModels()

    /** View binding */
    private lateinit var binding: FragmentBrowserBinding

    /** Get passed arguments */
    private val args: BrowserFragmentArgs by navArgs()

    /** Custom tab sessions */
    private lateinit var customTabSession: CustomTabsSession

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentBrowserBinding.inflate(layoutInflater)
        return binding.root
    }

    override fun onResume() {
        super.onResume()
        val navigateAction =
            BrowserFragmentDirections.actionBrowseFragmentToDetailFragment(
                args.typeArg,
                args.publishDateArg,
                args.socialMediaArg
            )
        findNavController().navigate(navigateAction)
    }

    override fun onAttach(context: Context) {
        super.onAttach(context)
        val callback: OnBackPressedCallback = object : OnBackPressedCallback(
            true
        ) {
            override fun handleOnBackPressed() {
                val navigateAction =
                    BrowserFragmentDirections.actionBrowseFragmentToDetailFragment(
                        args.typeArg,
                        args.publishDateArg,
                        args.socialMediaArg
                    )
                findNavController().navigate(navigateAction)
            }
        }
        requireActivity().onBackPressedDispatcher.addCallback(
            this,
            callback
        )
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        lifecycleScope.launchWhenStarted {
            viewModel.loading.onEach {
                // Setting the status of the progress bar
                binding.progressBar.visibility = if (it) View.VISIBLE else View.GONE
            }.launchIn(this)
            withContext(Dispatchers.IO) {
                CustomTabsClient.bindCustomTabsService(
                    requireContext(),
                    CUSTOM_TAB_PACKAGE_NAME,
                    customTabServiceConnection
                )
            }
        }
    }

    private val customTabServiceConnection = object : CustomTabsServiceConnection() {
        override fun onCustomTabsServiceConnected(
            componentName: ComponentName,
            client: CustomTabsClient
        ) {
            Log.d(TAG, "onCustomTabsServiceConnected")
            customTabSession = client.newSession(customTabCallback)!!
            val builder = CustomTabsIntent.Builder(customTabSession)
            builder.setShowTitle(true).build()
                .launchUrl(requireContext(), Uri.parse(args.urlArg))
        }

        override fun onServiceDisconnected(name: ComponentName?) {
            Log.d(TAG, "onServiceDisconnected")
        }
    }

    private val customTabCallback = object : CustomTabsCallback() {
        override fun onNavigationEvent(navigationEvent: Int, extras: Bundle?) {
            when (navigationEvent) {
                NAVIGATION_FAILED -> {
                    Toast.makeText(
                        requireContext(),
                        requireContext().resources.getText(R.string.browserError),
                        Toast.LENGTH_LONG
                    ).show()
                    val navigateAction =
                        BrowserFragmentDirections.actionBrowseFragmentToDetailFragment(
                            args.typeArg,
                            args.publishDateArg,
                            args.socialMediaArg
                        )
                    findNavController().navigate(navigateAction)
                }
            }
        }
    }
}