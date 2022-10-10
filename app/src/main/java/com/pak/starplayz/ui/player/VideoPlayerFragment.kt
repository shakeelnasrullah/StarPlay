package com.pak.starplayz.ui.player

import android.content.pm.ActivityInfo
import android.os.Bundle
import androidx.fragment.app.viewModels
import com.pak.lib_module.models.Media
import com.pak.lib_module.utils.Constants
import com.pak.starplayz.base.BaseFragment
import com.pak.starplayz.base.loadImageFromUrl
import com.pak.starplayz.databinding.FragmentVideoPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment :
    BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {


    private val viewModel  : PlayerViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setMedia(arguments?.getSerializable(Constants.mediaKey) as Media)
    }

    override fun onResume() {
        super.onResume()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_LANDSCAPE
    }

    override fun onPause() {
        super.onPause()
        activity?.requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
    }

    override fun setupView() {
    }

    override fun initObservations() {
        viewModel.media.observe(viewLifecycleOwner){
            binding?.imageView?.loadImageFromUrl(it)
        }
    }
}