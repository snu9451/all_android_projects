package com.example.workout2021_step1;

import android.os.Bundle;

import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

public class WorkoutDetailFragment extends Fragment {
    private long workoutId = 0;
    public void setWorkoutId(long workoutId){
        this.workoutId = workoutId;

    }
    /*
        프래그먼트를 사용하는 액티비티가 있으면, 액티비티는 어떤 방식으로든
        프래그먼트와 의사소통을 해야 한다.
        예를 들어, 레코드를 자세히 표시하는 프래그먼트가 있다면, 액티비티가
        어떤 레코드를 자세히 표시할지 프래그먼트에게 알려주어야 한다.
    */
    public WorkoutDetailFragment(){
        // Required empty public constructor
    }
    // 프래그먼트의 레이아웃이 필요할 때 안드로이드가 호출하는 메소드이다.
    // Activity에서는 setContentView() 메소드이다.

    /***********************************************************
     * 프래그먼트 레이아웃이 필요할 때마다 안드로이드가 호출하는 onCreateView 메소드 구현
     * @param inflater: XML View를 자바 객체로 변환
     * @param container: ViewGroup은 프래그먼트를 포함할 액티비티의 레이아웃을 가리킴.
     * @param savedInstanceState: 프래그먼트의 상태를 저장했다가 다시 살려낼 때 사용.
     * @return View: 프래그먼트의 사용자 인터페이스를 가리키는 View 객체를 반환한다.
     ***********************************************************/
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // 첫 번째 파라미터 R.layout.fragment_workout_detail은 프래그먼트가
        // 어떤 레이아웃을 사용하는지 안드로이드에게 알려준다.
        // fragment_workout_detail.xml
        // Container 파라미터는 프래그먼트 레이아웃을 추가할 액티비티의 ViewGroup을 가리킨다.
        return inflater.inflate(R.layout.fragment_workout_detail, container, false);
    }
    public void onStart(){
        super.onStart();
        View view = getView();
        if(view != null){
            TextView tv_title = view.findViewById(R.id.tv_title);
            Workout workout = Workout.workouts[(int)workoutId];
            tv_title.setText(workout.getName());
            TextView tv_description = view.findViewById(R.id.tv_description);
            tv_title.setText(workout.getDescription());

        }
    }
}