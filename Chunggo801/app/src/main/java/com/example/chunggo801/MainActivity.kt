package com.example.chunggo801

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import com.example.chunggo801.adapter.ChatListAdapter
import com.example.chunggo801.chat.ChatListFragment
import com.example.chunggo801.databinding.ActivityMainBinding
import com.example.chunggo801.databinding.FragmentChatlistBinding
import com.example.chunggo801.home.HomeFragment
import com.example.chunggo801.map.MapFragment
import com.example.chunggo801.mypage.MyPageFragment
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity() {

    ////////////////////////////////////// 테스트용
    private lateinit var binding: ActivityMainBinding
    private lateinit var chatLisFragment: ChatListFragment


    // chatFragment가 올라올 때 채팅 내용 넣어주기
    fun getChatList() {

    }
    //////////////////////////////////////////////


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        ////////////////////////////////////////테스트용
        //
        ////////////////////////////////////////테스트용


        // 프래그먼트를 초기화/인스턴스화
        val homeFragment = HomeFragment()
        val chatListFragment = ChatListFragment()
        val mapFragment = MapFragment()
        val myPageFragment = MyPageFragment()

        replaceFragment(myPageFragment)

        // bnv 쪽 아이콘 4개에 대한 이벤트 처리를 위한 작업
        val bottomNavigationView = findViewById<BottomNavigationView>(R.id.bottomNavigationView)
        // deprecated이지만 일단 사용하기로 함
        bottomNavigationView.setOnNavigationItemSelectedListener {
            // Kotliln에는 switch문이 없다.
            Log.i("mymymy","누른게뭐지: $it")
            when(it.itemId) {
                R.id.home -> replaceFragment(homeFragment)
                R.id.chat -> replaceFragment(chatListFragment)
                R.id.map -> replaceFragment(mapFragment)
                R.id.myPage -> replaceFragment(myPageFragment)
            }
            true
        }
    }/////////////////////////////////end of onCreate

    // 프래그먼트를 갈아 끼우기
    private fun replaceFragment(fragment : Fragment) {
        supportFragmentManager.beginTransaction() // 이 자체가 [객체 생성]이라고 볼 수 있다.
            .apply {
                replace(R.id.fragmentContainer, fragment) // fragmentContainer는 사전에 activity_main.xml에 준비해둔다.
                commit()
            }
    }

}