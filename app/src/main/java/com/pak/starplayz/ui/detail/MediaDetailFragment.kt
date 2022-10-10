package com.pak.starplayz.ui.detail

import android.os.Bundle
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.pak.lib_module.models.Media
import com.pak.lib_module.utils.Constants
import com.pak.starplayz.R
import com.pak.starplayz.base.BaseFragment
import com.pak.starplayz.base.loadImageFromUrl
import com.pak.starplayz.databinding.FragmentDetailBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MediaDetailFragment : BaseFragment<FragmentDetailBinding>(FragmentDetailBinding::inflate) {
    private val viewModel: DetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        viewModel.setMedia(arguments?.getSerializable(Constants.mediaKey) as Media)
    }

    override fun setupView() {
        binding?.playBtn?.setOnClickListener {
            val bundle = bundleOf(Constants.mediaKey to viewModel.media.value)
            findNavController().navigate(
                R.id.action_mediaDetailFragment_to_videoPlayerFragment,
                bundle
            )
        }
    }

    override fun initObservations() {
        viewModel.media.observe(viewLifecycleOwner) {
            setUpData(it)
        }
    }

    private fun setUpData(media: Media) {
        binding?.mediaTitleTv?.text = media.name
        binding?.mediaDescriptionTv?.text = media.overview
        binding?.imageView?.loadImageFromUrl(media)
    }
}