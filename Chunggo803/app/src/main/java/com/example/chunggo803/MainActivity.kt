package com.example.chunggo803

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.MenuItem
import androidx.fragment.app.Fragment
import com.example.chunggo803.databinding.ActivityMainBinding
import com.google.android.material.bottomnavigation.BottomNavigationView

class MainActivity : AppCompatActivity(), BottomNavigationView.OnNavigationItemSelectedListener {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
    }

    // 네비게이션 바에 대한 이벤트 핸들러 감지 코드 추가 및 디폴트 프래그먼트 페이지 연결
    private fun initViews() = with(binding) {
        bottom
    }

    private fun replaceFragment(fragment: Fragment, tag: String) {
        val findFragment = supportFragmentManager.findFragmentByTag(tag)
        // 전처리기 추가 - 여기서 처리하는 것: 프래그먼트가 교체될 때 나머지 프래그먼트는 hide로 만들어줄 코드가 필요하.
        // 마치 html에서 div hide-show
        // 이 전처리 부분이 반드시 있어야 오작동을 줄일 수 있다.
        // 프래그먼트 가져오고 그 프래그먼트들을 하나씩 돌면서 처리
        supportFragmentManager.fragments.forEach { fm ->
            supportFragmentManager.beginTransaction().hide(fm).commitAllowingStateLoss()
        }

        // 만약 프래그먼트가 있다면, - 있다면? 즉, null일수도 있다.
        findFragment?.let {
            supportFragmentManager.beginTransaction().show(it).commitAllowingStateLoss()
        } ?: kotlin.run {   // 없다면,
            supportFragmentManager.beginTransaction()
                .add(R.id.fragmentContainer, fragment, tag)
                .commitAllowingStateLoss()
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        retrun when (item.itemId) {
            R.id.home -> {
                replaceFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }
            R.id.home -> {
                replaceFragment(ChatListFragment.newInstance(), HomeFragment.TAG)
                true
            }
            R.id.home -> {
                replaceFragment(HomeFragment.newInstance(), HomeFragment.TAG)
                true
            }
            else -> false
        }
    }
}