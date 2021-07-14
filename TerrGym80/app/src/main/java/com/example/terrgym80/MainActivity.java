package com.example.terrgym80;

import androidx.annotation.NonNull;
import androidx.appcompat.app.ActionBarDrawerToggle;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.MenuItem;
import android.view.View;

import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity implements NavigationView.OnNavigationItemSelectedListener {

    FragmentManager fragmentManager = getSupportFragmentManager();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(this.getClass().getName(), "onCreate");
        Toolbar toolbar = findViewById(R.id.toolbar);
        // 툴바 장착하기
        setSupportActionBar(toolbar);
        // 버거아이콘으로 나브바 열 수 있도록 하기
        /**********************************************************************
         * @param1 : 현재 액티비티(this, getApplicationContext(), getContext())
         * @액티비티의 drawer
         * @액티비티의 툴바
         * @드로워 열기, 드로워 닫기
         **********************************************************************/
        DrawerLayout drawer = findViewById(R.id.drawer_layout);
        ActionBarDrawerToggle toggle = new ActionBarDrawerToggle(this,
                drawer,
                toolbar,
                R.string.nav_open_drawer,
                R.string.nav_close_drawer);
        drawer.addDrawerListener(toggle);
        toggle.syncState();
        NavigationView navigationView = findViewById(R.id.nav_view);
        // 이벤트 소스와 이벤트 핸들러를 연결
        navigationView.setNavigationItemSelectedListener(this);
//        fragmentManager.beginTransaction()
//                .replace(R.id.content_frame, LoginFragment.class, null)
//                .setReorderingAllowed(true)
//                .addToBackStack(null)
//                .commit();
    }

    @Override
    protected void onStart() {
        super.onStart();
        Log.i(this.getClass().getName(), "onStart");
    }
    public void resList(View v){
        Log.i(this.getClass().getName(), "resList: "+v);
        // 이동
        Intent intent = new Intent(this, ResListActivity.class);
        startActivity(intent);
    }

    @Override
    public boolean onNavigationItemSelected(@NonNull MenuItem item) {
        // 어떤 메뉴 아이템을 선택했니?
        int id = item.getItemId();
        Log.i(this.getClass().getName(), "서낵한 메뉴의 아이디 값은 ===> "+ id);
        Fragment fragment = null;
        Activity activity = null;
        Intent intent = null;
        switch (id) {
            case R.id.nav_login:
//                activity = new LoginActivity();
                intent = new Intent(this, LoginActivity.class);
                break;
            case R.id.nav_coin:
                intent = new Intent(this, CoinActivity.class);
                break;
            case R.id.nav_chat:
                intent = new Intent(this, ChatActivity.class);
                break;
            case R.id.nav_map:
                intent = new Intent(this, MapActivity.class);
                break;

            default:
                break;
        }
        if(fragment != null){   // 선택한 메뉴 아이템이 프래그먼트이니?
            getSupportFragmentManager().beginTransaction()
                    .replace(R.id.content_frame, fragment)
                    .commit();
        }
        else { // 선택한 메뉴 아이템이 엑티비니?
            startActivity(intent);
        }
        // 사용자가 옵션 중 하나를 선택하면 홤ㄴ을 닫는다.
        DrawerLayout drawerLayout = findViewById(R.id.drawer_layout);
        drawerLayout.closeDrawer(GravityCompat.START);
        return true;
    }
}