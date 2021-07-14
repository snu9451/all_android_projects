package com.example.lotto80

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import android.widget.Button
import android.widget.NumberPicker
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.isVisible

class MainActivity3 : AppCompatActivity() {
    // 지우기 버튼, add버튼 추가하기
    private val clearButton: Button by lazy {
        findViewById(R.id.clearButton)
    }
    private val addButton: Button by lazy {
        findViewById(R.id.addButton)
    }
    // 선언 뒤에 lazy가 오면 activity_main4.xml을 스캔할 때까지 기다린다.
    private val runButton: Button by lazy {
        findViewById(R.id.runButton)
    }

    // NumberPicker의 범위를 정해주자. 지금은 0만 보이며 움직이지도 않음.
    private val numberPicker : NumberPicker by lazy {
        findViewById(R.id.numberPicker)
    }

    // 채번된 숫자가 출력될 컴포넌트를 배열에 담기
    private val numberTextViewList : List<TextView> by lazy {
        listOf<TextView>(
            findViewById(R.id.tv_choice1),
            findViewById(R.id.tv_choice2),
            findViewById(R.id.tv_choice3),
            findViewById(R.id.tv_choice4),
            findViewById(R.id.tv_choice5),
            findViewById(R.id.tv_choice6),
        )
    }

    // 예외처리할 사항...
    // 이미 자동생성 시작이라는 걸 눌러서 번호를 추가할 수 없는 상태일 수 있음.
    private var didRun = false

    // 1부터 45까지 중에 중복이 되지 않도록 막기 - 이럴 땐 Set 계열을 쓰는 것이 좋다.
    private val pickNumberSet = hashSetOf<Int>()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main3)
        numberPicker.minValue = 1
        numberPicker.maxValue = 45

        // 자동 채번 버튼 구현
        initRunButton()
        initAddButton()
    }/////////////////////////end of onCreate

    private fun initAddButton() {
        addButton.setOnClickListener {
            if(didRun) {
                Toast.makeText(this, "초기화 후에 시도해주세요.", Toast.LENGTH_SHORT).show()
            /*
            return과 return@setOnClickListener의 차이
            전자는 initAddButton을 빠져 나가게 하고, 후자는 이벤트만 빠져나가게 한다.
            setOnClickListener를 붙이는 이유: 안에 있는 setOnClickListener를 리턴할지
            밖에 있는 initAddButton을 리턴할 지 모르기 때문이다. 알아보기 쉽게 파랑색임.
            */
                return@setOnClickListener
            }
            if(pickNumberSet.size >= 6) {
                Toast.makeText(this, "번호는 최대 6개까지만 선택할 수 있습니다.", Toast.LENGTH_SHORT).show()
            }
            // 이미 선택한 번호를 또 선택한 경우
            if(pickNumberSet.contains(numberPicker.value)) {
                Toast.makeText(this, "이미 선택된 번호입니다.", Toast.LENGTH_SHORT).show()
            }
            // insert here - TextView에 선택한 숫자를 출력해주기.
            // numberTextViewList.get()으로 가져올 수도 있지만 배열처럼 가져올 수도 있다.
            val textView = numberTextViewList[pickNumberSet.size]
            textView.isVisible = true
            // (Java) findViewById(R.id.tv).setText(numberPicker.value.toString()) 와 같음
            textView.text = numberPicker.value.toString()
            pickNumberSet.add(numberPicker.value)
        }
    }

    // 자동 생성 시작 버튼을 눌렀을 때
    // 기존에 추첨한 숫자가 있는 경우, 그 나머지만 숫자를 계속 바꿔서 출력해야 한다.
    private fun initRunButton() {
        runButton.setOnClickListener {
            var rbtn = it.id
            Log.i("MainActivity", "버튼 주소번지: $rbtn")
            val list = getRandomNumber()
            didRun = true
            list.forEachIndexed { index, number ->
                val textView = numberTextViewList[index]
                textView.text = number.toString()
                textView.isVisible = true
            }
            Log.d("MainActivity", list.toString())
        }
    }
    override fun onStart() {
        super.onStart()
        // 실행이 되었다가 (백스택에 머물러 있다가) 다시 소환될 때 onCreate를 타는 게 아니라 여기로 온다.
        // initRunButton()
    }

    // 채번 알고리즘
    private fun getRandomNumber() : List<Int> {
        // List<Integer> numberList2 = new ArrayList<>();
        val numberList = mutableListOf<Int>()
            .apply {
                for(i in 1..45) {
                    // 이미 추첨한 숫자가 있으면 그것만큼 제외시킴
                    if(pickNumberSet.contains(i)) continue
                    this.add(i)
                }/////////////end of for
            }/////////////////end of initialized
        numberList.shuffle()
        val newList = numberList.subList(0, 6)
        return newList.sorted()
    }////////////////////////end of getRandomNumber
}