package com.example.foodapp801;

import android.os.Bundle;
// androidx 버전을 사용해야 구글에서 제공하는 다른 라이브러리들을 이용할 수 있다.
// 되도록이면 표준이 아니라 androidx로 사용하도록 하자.
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class HomeFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_home, container, false);
    }
}