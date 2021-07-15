package com.example.chunggo803

import BaseFragment
import com.example.chunggo803.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class HomeFragment : BaseFragment<HomeViewModel, FragmentHomeBinding>() {
    override val viewModel by viewModel<HomeViewModel>()
    override fun getViewBinding(): FragmentHomeBinding = FragmentHomeBinding.inflate(layoutInflater)
    override fun observeData() {

    }

    companion object {
        fun newInstance() = HomeFragment()
        const val TAG = "HomeFragment"
    }
}