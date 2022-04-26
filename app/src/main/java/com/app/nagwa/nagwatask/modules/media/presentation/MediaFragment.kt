package com.app.nagwa.nagwatask.modules.media.presentation

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.ViewModelProvider
import com.app.nagwa.nagwatask.common.di.viewmodel.DaggerViewModelProviderFactory
import com.app.nagwa.nagwatask.databinding.FragmentMediaBinding
import com.app.nagwa.nagwatask.modules.media.presentation.adapter.MediaAdapter
import dagger.android.support.DaggerFragment
import javax.inject.Inject

class MediaFragment : DaggerFragment() {
    @Inject
    lateinit var factory: DaggerViewModelProviderFactory

    val viewModel: MediaViewModel by lazy {
        ViewModelProvider(this, factory).get(
            MediaViewModel::class.java
        )
    }
    private var _binding: FragmentMediaBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var adapter: MediaAdapter
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentMediaBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        initUI()
        addObservers()
        setListeners()
    }

    private fun initUI() {
        binding.recyclerView.adapter = adapter
    }

    private fun addObservers() {
        viewModel.mediaList.observe(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

    private fun setListeners() {
        adapter.itemClickedCallBack = {
            Log.d("MediaDebug", "setListeners: $it")
        }
    }
}