package com.example.workout2021_step1;

public class Workout {

    // 운동의 이름
    private String name = null;
    // 운동에 대한 설명
    private String description = null;

    // 초기화를 위해 생성자 오버로딩
    public Workout(String name, String description){
        this.name = name;
        this.description = description;
    }

    public String getName(){
        return name;
    }

    public String getDescription(){
        return description;
    }

    public static Workout[] workouts = {
            new Workout("수영", "1 물에서\n2 하는\n3 운동"),
            new Workout("야구", "공을 가지고 하는 운동"),
            new Workout("피겨스케이팅", "빙상 위에서 하는 운동")
    };
}
