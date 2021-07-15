package com.example.chunggo803

import BaseFragment
import androidx.fragment.app.Fragment
import com.example.chunggo803.databinding.FragmentChatBinding
import com.example.chunggo803.databinding.FragmentHomeBinding
import org.koin.android.viewmodel.ext.android.viewModel

class ChatListFragment : BaseFragment<ChatListViewModel, FragmentChatBinding>() {
    override val viewModel by viewModel<ChatListViewModel>()
    override fun getViewBinding(): FragmentChatBinding = FragmentChatBinding.inflate(layoutInflater)
    override fun observeData() {

    }

    companion object {
        fun newInstance() = ChatListFragment()
        const val TAG = "ChatListFragment"
    }
}
