package com.example.foodapp801;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.google.android.material.tabs.TabLayout;

import org.jetbrains.annotations.NotNull;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // 제네릭 - 타입 맞춰줌. 따라서 아래와 같은 코드로 작성하지 않아도 됨
        // Toolbar toolbar = (Toolbar)findViewById(R.id.toolbar);
        Toolbar toolbar = findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        SectionPagerAdaptor pagerAdaptor = new SectionPagerAdaptor(getSupportFragmentManager());
        ViewPager pager = findViewById(R.id.pager);
        pager.setAdapter(pagerAdaptor);
        TabLayout tabLayout = findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(pager);

    }

    /*
    * 뷰 페이저를 정의하면서 pager라는 ID를 할당하자.
    * 각 뷰 페이저는 액티비티에서 참조할 수 있도록 반드시 ID를 가져야 한다.
    * ID가 없으면 뷰 페이지의 각 페이지에 어떤 프래그먼트를 표시할지를 지정할 수 없다.
    */
    
    // 내부 클래스
    private class SectionPagerAdaptor extends FragmentPagerAdapter {

        public SectionPagerAdaptor(@NonNull @NotNull FragmentManager fm) {
            super(fm);
        }

        @Override
        public Fragment getItem(int position) {
            switch (position){
                case 0:
                    return new HomeFragment();
                case 1:
                    return new PizzaFragment(); // List(화면없음)
                case 2:
                    return new PastaFragment(); // List(화면없음)
                case 3:
                    return new StoreFragment(); // List(화면없음) // 지도 서비스 결합 예정
            }
            return null;
        }
        
        public CharSequence getPageTitle(int position){
            switch (position){
                case 0:
                    return "Home";
                case 1:
                    return "Pizze";
                case 2:
                    return "Pasta";
                case 3:
                    return "Store";
            }
            return null;
        }

        @Override
        public int getCount() {
            return 0;
        }
    }
}