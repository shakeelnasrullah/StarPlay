package com.pak.starplayz.ui.player

import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import android.view.View
import android.widget.MediaController
import androidx.core.content.ContextCompat
import androidx.fragment.app.viewModels
import com.pak.lib_module.models.Media
import com.pak.lib_module.utils.Constants
import com.pak.starplayz.R
import com.pak.starplayz.base.BaseFragment
import com.pak.starplayz.base.loadImageFromUrl
import com.pak.starplayz.databinding.FragmentVideoPlayerBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class VideoPlayerFragment :
    BaseFragment<FragmentVideoPlayerBinding>(FragmentVideoPlayerBinding::inflate) {


    private val viewModel  : PlayerViewModel by viewModels()
    private var isVideoShowing : Boolean = false

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
        binding?.let { binding ->
            val mediaController = MediaController(requireContext())
            binding.videoView.setMediaController(mediaController)
            binding.videoView.setVideoURI(Uri.parse(Constants.VIDEO_URL))
            mediaController.setAnchorView(binding.videoView)

            binding.fab.setOnClickListener {
                if (isVideoShowing) {
                    isVideoShowing = false
                    binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_play
                        )
                    )
                    binding.imageView.visibility = View.VISIBLE
                    binding.videoView.visibility = View.GONE
                    binding.videoView.stopPlayback()

                } else {
                    isVideoShowing = true
                    binding.fab.setImageDrawable(
                        ContextCompat.getDrawable(
                            requireContext(),
                            R.drawable.ic_default_image
                        )
                    )
                    binding.imageView.visibility = View.GONE
                    binding.videoView.visibility = View.VISIBLE
                    binding.videoView.start()
                }
            }
        }
    }

    override fun initObservations() {
        viewModel.media.observe(viewLifecycleOwner){
            binding?.imageView?.loadImageFromUrl(it)
        }
    }
}