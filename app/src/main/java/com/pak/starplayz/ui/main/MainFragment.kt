package com.pak.starplayz.ui.main

import android.app.AlertDialog
import android.content.DialogInterface
import android.util.Log
import android.view.View
import androidx.appcompat.widget.SearchView
import androidx.core.os.bundleOf
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import com.pak.lib_module.language.LanguageHelper
import com.pak.lib_module.models.Media
import com.pak.lib_module.models.MediaTypeList
import com.pak.lib_module.utils.Constants
import com.pak.lib_module.utils.Response
import com.pak.lib_module.utils.Utils
import com.pak.starplayz.R
import com.pak.starplayz.adapter.MediaListAdapter
import com.pak.starplayz.base.BaseFragment
import com.pak.starplayz.databinding.FragmentMainBinding
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject


@AndroidEntryPoint
class MainFragment : BaseFragment<FragmentMainBinding>(FragmentMainBinding::inflate) {

    private val viewModel: MainViewModel by viewModels()

    lateinit var adapter: MediaListAdapter
    @Inject
    lateinit var languageHelper: LanguageHelper

    override fun setupView() {
        binding?.switchLanguage?.setOnClickListener {
            showLanguageDialog()
        }
        binding?.searchView?.setOnQueryTextListener(object : SearchView.OnQueryTextListener {
            override fun onQueryTextSubmit(query: String?): Boolean {
                query?.let {
                    viewModel.searchMedia(it, requireActivity())
                }
                closeKeyboard(context, binding?.searchView)
                return false
            }

            override fun onQueryTextChange(newText: String?): Boolean {

                return false
            }
        })
        adapter = MediaListAdapter(::onItemSelected)
        binding?.mediaRecyclerView?.adapter = adapter
        binding?.mediaRecyclerView?.layoutManager = LinearLayoutManager(requireContext())
    }

    private fun onItemSelected(media: Media) {
        val bundle = bundleOf(Constants.mediaKey to media)
        findNavController().navigate(R.id.action_mainFragment_to_mediaDetailFragment, bundle)
    }

    override fun initObservations() {
        viewModel.mediaList.observe(viewLifecycleOwner) {
            when (it) {
                is Response.Loading -> {
                    binding?.progressBar?.visibility = View.VISIBLE
                }
                is Response.Success -> {
                    binding?.progressBar?.visibility = View.GONE
                    it.data?.let { data ->
                        val mediaList = filterMediaTypesList(data, filterMediaTypes((data)))
                        if (mediaList.isNotEmpty()) {
                            adapter.replaceList(mediaList)
                        }
                        Log.d("List Size ", data.size.toString())
                    }
                }
                is Response.Failure -> {
                    binding?.progressBar?.visibility = View.GONE
                    Log.d("Failure ", it.errorMessage.toString())
                }
            }
        }
    }

    private fun filterMediaTypes(mediaList: List<Media>): ArrayList<String> {
        val mediaTypes = arrayListOf<String>()
        for (media in mediaList) {
            if (!mediaTypes.contains(media.media_type)) {
                mediaTypes.add(media.media_type)
            }
        }
        return mediaTypes
    }

    private fun filterMediaTypesList(
        mediaList: List<Media>,
        mediaTypeList: ArrayList<String>
    ): ArrayList<MediaTypeList> {
        val listOfResponse = arrayListOf<MediaTypeList>()
        for (mediaType in mediaTypeList) {
            listOfResponse.add(
                MediaTypeList(
                    mediaType,
                    mediaList.filter { s -> s.media_type == mediaType })
            )
        }
        return listOfResponse
    }

    private fun showLanguageDialog() {
        val builder: AlertDialog.Builder = AlertDialog.Builder(context)
        builder.setTitle(getString(R.string.language_dialog_title))
        val languages = languageHelper.getLanguageList(requireActivity())
        builder.setSingleChoiceItems(languages.second.toTypedArray(), languages.first, DialogInterface.OnClickListener { dialog, which ->
            Utils.saveSelectedLanguage(requireActivity(), languages.second.get(which))
            dialog.dismiss()
        })
        val dialog: AlertDialog = builder.create()
        dialog.show()
    }


}