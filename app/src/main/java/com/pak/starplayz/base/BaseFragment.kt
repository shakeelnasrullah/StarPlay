package com.pak.starplayz.base

import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.inputmethod.InputMethodManager
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding

abstract class BaseFragment<VB : ViewBinding>(private val bindingInflater: (layoutInflater: LayoutInflater) -> VB) :
    Fragment() {
    private var _binding: VB? = null
    protected val binding get() = _binding
    
    abstract fun setupView()
    abstract fun initObservations()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = bindingInflater.invoke(inflater)
        return binding?.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupView()
        initObservations()
    }

    fun closeKeyboard(context: Context?, view: View?) {
        context?.let { cntx ->
            view?.let { myView ->
                val manager: InputMethodManager? =
                    cntx.getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager?
                manager?.hideSoftInputFromWindow(myView.windowToken, 0)
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}