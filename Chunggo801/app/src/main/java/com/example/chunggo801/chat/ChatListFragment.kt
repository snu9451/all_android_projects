package com.example.chunggo801.chat

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.chunggo801.R
import com.example.chunggo801.adapter.ChatListAdapter
import com.example.chunggo801.databinding.FragmentChatlistBinding
import com.example.chunggo801.model.ChatList
import com.google.firebase.database.*
import com.google.firebase.database.ktx.database
import com.google.firebase.database.ktx.getValue
import com.google.firebase.ktx.Firebase

class ChatListFragment : Fragment(R.layout.fragment_chatlist) {
    companion object {
//        /*  =================================== [[ 테스트용 :: ArrayList ]] =======================================
        val chatListData = ArrayList<ChatList>()
//        val nicknameList: ArrayList<String> = ArrayList()
//            arrayOf("김은영", "이순신도라에몽", "강감찬보노보노", "홍길동둘리")
//        val contentList: ArrayList<String> = ArrayList()
//            arrayOf("첫번째 테스트 내용", "HI!! HELLO!!", "897654565564", "last content")
//        */
        val TAG = "mymymy"
    }

    // 파이어베이스 연동에 필요한 선언
    private lateinit var database: FirebaseDatabase
    private lateinit var chatRef: DatabaseReference
    // 데이터 바인딩을 위해 필요한 선언
    private var chatListBinding: FragmentChatlistBinding? = null
    private lateinit var chatListAdapter: ChatListAdapter
    // 코드 출처: https://developer.android.com/topic/libraries/view-binding?hl=ko#fragments
//    private val binding get() = chatListBinding!!   // 나는 null이 아니야!! 라는 의미에서 느낌표 두개
    private val binding get()= chatListBinding!!   // 나는 null이 아니야!! 라는 의미에서 느낌표 두개


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
    }

    // 파이어베이스에서 데이터 가져오기, 이벤트 달기
    private fun initFirebase() {
        database = Firebase.database("https://androidnds-9ac2f-default-rtdb.asia-southeast1.firebasedatabase.app/")
        Log.i(TAG, "$database")
        chatRef = database.getReference()
        Log.i(TAG, "$chatRef")
        val chatChildAddListener = object: ChildEventListener {
            override fun onChildAdded(snapshot: DataSnapshot, previousChildName: String?) {
                Log.i(TAG, "스냅샷0 ===> ${snapshot.children}")
                Log.i(TAG, "스냅샷1 ===> ${snapshot.children.toList()}")
                Log.i(TAG, "스냅샷2 ===> ${snapshot.child("chat-"+1+"/content").value}")
                Log.i(TAG, "스냅샷2 ===> ${snapshot.getValue()}")
                Log.i(TAG, "스냅샷2 ===> ${snapshot.value}")
            }

            override fun onChildChanged(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onChildRemoved(snapshot: DataSnapshot) {
                TODO("Not yet implemented")
            }

            override fun onChildMoved(snapshot: DataSnapshot, previousChildName: String?) {
                TODO("Not yet implemented")
            }

            override fun onCancelled(error: DatabaseError) {
                TODO("Not yet implemented")
            }

        }
        chatRef.addChildEventListener(chatChildAddListener)

    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        Log.i(TAG, "onCreateView 호출 성공")
//        /*  =================================== [[ 테스트용 :: ArrayList ]] =======================================
        // 데이터 담아주기
        val chatOne = ChatList(null, "김은영", "첫번째 테스트 내용")
        val chatTwo = ChatList(null, "이순신도라에몽", "HI!!! HELLO!!!")
        val chatThree = ChatList(null, "강감찬보노보노", "897654565564")
        val chatFour = ChatList(null, "홍길동둘리", "last content")
        chatListData.add(chatOne)
        chatListData.add(chatTwo)
        chatListData.add(chatThree)
        chatListData.add(chatFour)
//        */
        initFirebase()
        chatListBinding = FragmentChatlistBinding.inflate(layoutInflater)
        Log.i(TAG, "chatListBinding: $chatListBinding")
        val view = binding.root
        chatListAdapter = ChatListAdapter()
        binding.rvChatList.layoutManager = LinearLayoutManager(context)
        binding.rvChatList.adapter = chatListAdapter
        chatListAdapter.submitList(chatListData)
        return view
    }

    override fun onDestroyView() {
        super.onDestroyView()
        Log.i(TAG, "onDestroyView 호출 성공")
        chatListBinding = null
        Log.i(TAG, "chatListBinding: $chatListBinding")
    }
}