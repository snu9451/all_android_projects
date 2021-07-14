package com.example.ch7fragment;

import android.os.Bundle;
// 안드로이드의 지원 라이브러리의 Fragment를 사용함.
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

public class FragmentA extends Fragment {
    // Fragment Layout이 필요할 때 안드로이드가 호출하는 메소드이다.
    // 아래 메소드는 선택사항이지만, Layout을 포함하는 Fragment에서는
    // 이 메소드를 구현해야 한다.
    // Fragment 사용자의 인터페이스를 가리키는 View 객체를 반환한다.
    /*
    @param1: LayoutInflater는 Fragment Layout을 inflate하는 데 사용한다.
    XML View를 자바 객체로 변환한다.
    @param2: ViewGroup은 Fragment를 포함할 Activity의 Layout을 가리킨다.
    @param3: Bundle은 Fragment 상태를 저장했다가 다시 살려낼 때 사용한다.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Fragment가 어떤 Layout을 사용하는지 안드로이드에게 알려준다.
        return inflater.inflate(R.layout.fragment_a, container, false);
    }
}