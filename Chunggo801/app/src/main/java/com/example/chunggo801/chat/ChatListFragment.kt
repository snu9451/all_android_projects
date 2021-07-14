package com.example.chunggo801.chat

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chunggo801.R
import com.example.chunggo801.adapter.ChatListAdapter
import com.example.chunggo801.databinding.FragmentChatlistBinding
import com.example.chunggo801.model.ChatList

class ChatListFragment : Fragment(R.layout.fragment_chatlist) {
    companion object {
        val chatListData = ArrayList<ChatList>()
//        val nicknameList: ArrayList<String> = ArrayList()
//            arrayOf("김은영", "이순신도라에몽", "강감찬보노보노", "홍길동둘리")
//        val contentList: ArrayList<String> = ArrayList()
//            arrayOf("첫번째 테스트 내용", "HI!! HELLO!!", "897654565564", "last content")
    }
    private var chatListBinding: FragmentChatlistBinding? = null
    private lateinit var chatListAdapter: ChatListAdapter
    // 코드 출처: https://developer.android.com/topic/libraries/view-binding?hl=ko#fragments
//    private val binding get() = chatListBinding!!   // 나는 null이 아니야!! 라는 의미에서 느낌표 두개
    private val binding get()= chatListBinding!!   // 나는 null이 아니야!! 라는 의미에서 느낌표 두개
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        // 데이터 담아주기
        val chatOne = ChatList(null, "김은영", "첫번째 테스트 내용")
        val chatTwo = ChatList(null, "이순신도라에몽", "HI!!! HELLO!!!")
        chatListData.add(chatOne)
        chatListData.add(chatTwo)


        chatListBinding = FragmentChatlistBinding.inflate(layoutInflater)
        val view = binding.root
        chatListAdapter = ChatListAdapter()
        binding.rvChatList.layoutManager = LinearLayoutManager(context)
        binding.rvChatList.adapter = chatListAdapter
        chatListAdapter.submitList(chatListData)
        return view
    }





    override fun onDestroyView() {
        super.onDestroyView()
        chatListBinding = null
    }
}