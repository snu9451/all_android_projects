import android.os.Bundle
import androidx.lifecycle.ViewModel
//    implementation 'androidx.core:core-ktx:1.6.0'
//    implementation 'androidx.fragment:fragment-ktx:1.3.4'
import androidx.lifecycle.viewModelScope
//implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-core:1.4.2'
//implementation 'org.jetbrains.kotlinx:kotlinx-coroutines-android:1.4.1'
import kotlinx.coroutines.Job
import kotlinx.coroutines.launch

abstract class BaseViewModel: ViewModel() {
    protected var stateBundle: Bundle? = null
    //뷰모델 스코프로 특정 동작을 구현
    //코르틴 라이브러리 사용해서 특정 동작을 구현할 수 있게 한다.
    open fun fetchData(): Job = viewModelScope.launch {  }
    //Activity나 Fragment가 완전히 종료가 되기전 까지는  Bundle이 계속 값을 유지해줌.
    open fun storeState(stateBundle: Bundle) {
        this.stateBundle = stateBundle
    }
}