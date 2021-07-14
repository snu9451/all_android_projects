package com.example.workout80;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import androidx.fragment.app.Fragment;

public class WorkoutDetailFragment extends Fragment {

    /*************************************************************************
     * 프래그먼트 레이아웃이 필요할 때마다 안드로이드가 호출하는 onCreateView를 구현
     * @param inflater : XML View를 자바 객체로 변환
     * @param container : ViewGroup은 프래그먼트를 포함한 액티비티 레이아웃을 가리킨다.
     *                  this | getApplicationContext() | getContext()를 대신함
     * @param savedInstanceState : 프래그먼트의 상태를 저장했다가 다시 살려낼 때 사용한다.
     * @return View : 프래그먼트의 사용자 인터페이스를 가리키는 View 객체를 반환한다.
     */
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState){
        View view = inflater.inflate(R.layout.fragment_workout_detail, container, false);
        return view;
    }
}
