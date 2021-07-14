package com.example.ktbook802

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.KeyEvent
import android.view.MotionEvent
import androidx.core.view.isVisible
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.room.Room
import com.example.ktbook802.adapter.BookAdapter
import com.example.ktbook802.adapter.HistoryAdapter
import com.example.ktbook802.api.BookService
import com.example.ktbook802.databinding.ActivityMainBinding
import com.example.ktbook802.model.BestSellerDTO
import com.example.ktbook802.model.History
import com.example.ktbook802.model.SearchBookDTO
import com.example.ktbook802.util.AppDatabase
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private lateinit var adapter: BookAdapter
    private lateinit var historyAdapter: HistoryAdapter
    private lateinit var bookService: BookService
    // 데이터베이스 가져오기
    private lateinit var db: AppDatabase


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        // 바인딩된 정보를 가지고 뿌려야 함 - 데이터 화면을 출력해야 함
//        setContentView(R.layout.activity_main)
        setContentView(binding.root)
        adapter = BookAdapter()
        binding.bookRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.bookRecyclerView.adapter = adapter
        initHistoryRecyclerView()
        db = Room.databaseBuilder(applicationContext, AppDatabase::class.java, "BookSearchDB").build()
        // 레트로핏 구현체 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        bookService = retrofit.create(BookService::class.java)
        /* ================================================ [[ 베스트 셀러 목록 시작 ]] ================================================ */
        bookService.getBestSellerBooks(getString(R.string.bookApiKey))
            .enqueue(object: Callback<BestSellerDTO> {
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if (response.isSuccessful.not()){
                        Log.e(TAG, "**NOT**SUCCESS")
                        return
                    }
                    // let 함수- scope
                    adapter.submitList(response.body()?.books.orEmpty())
//                    response.body()?.let {
//                        Log.d(TAG, "=================================================================")
//                        Log.d(TAG, "body에서 꺼내오기"+it.toString())
//                        it.books.forEach { book ->
//                            Log.d(TAG, book.toString())
//                        }
//                    }
                }
                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    // 실패 처리하는 부분
                    // override 관계에 있기 때문에 무조건 override해야 함.
                    Log.e(TAG, t.toString())
                }


            })
        /* ================================================ [[ 베스트 셀러 목록 끝 ]] ================================================ */
        initSearchEditText()
    }////////////////////end of onCreate
    private fun initSearchEditText() {
        Log.i(TAG, "initSearchEditText 호출 성공")
        binding.searchEditText.setOnKeyListener { v, keyCode, event ->
            if (keyCode == KeyEvent.KEYCODE_ENTER && event.action == MotionEvent.ACTION_DOWN) {
                search(binding.searchEditText.text.toString())
                Log.i(TAG, "키입력하고 터치")
                return@setOnKeyListener true    // 이벤트 처리가 되었음
            }
            return@setOnKeyListener false   // 이벤트 처리가 되지 않았음
        }
        binding.searchEditText.setOnTouchListener { v, event ->
            if (event.action == MotionEvent.ACTION_DOWN) {
                showHistoryView()
            }
            return@setOnTouchListener false
        }
    }


    /* ================================================ [[ 검색기능 구현 ]] ================================================ */
    private fun search(keyword: String){
        Log.i(TAG, "search 호출 성공")
        bookService.getBooksByName(getString(R.string.bookApiKey), keyword)
            .enqueue(object: Callback<SearchBookDTO> {
                override fun onResponse(
                    call: Call<SearchBookDTO>,
                    response: Response<SearchBookDTO>
                ) {
                    hideHistoryView()
                    Log.i(TAG, "keyword ===> "+keyword)
                    saveSearchKeyword(keyword)  // ;insertHistory
                    if (response.isSuccessful.not()){
                        Log.e(TAG, "**NOT**SUCCESS")
                        return
                    }
                    adapter.submitList(response.body()?.books.orEmpty())
                }
                override fun onFailure(call: Call<SearchBookDTO>, t: Throwable) {
                    // 실패 처리
                    hideHistoryView()
                    Log.e(TAG, t.toString())
                }
            })
    }
    private fun initHistoryRecyclerView(){
        historyAdapter = HistoryAdapter(historyDeleteClickedListener = {
            deleteSearchKeyword(it)
        })
        binding.historyRecyclerView.layoutManager = LinearLayoutManager(this)
        binding.historyRecyclerView.adapter = historyAdapter
    }
    // 삭제 후 뷰 갱신처리하기
    private fun showHistoryView(){
        Log.i(TAG, "showHistoryView 호출 성공")
        Thread{
            val keywords = db.historyDao().getAll().reversed()  // 내림차순 정렬
            // 아래의 코드가 없으면, 저장은 되더라도 화면에는 보이지 않는다.
            runOnUiThread{
                binding.historyRecyclerView.isVisible = true
                // 키워드에 null이 올 수도 있으므로
                historyAdapter.submitList(keywords.orEmpty())
            }
        }
    }
    // 검색이 실패했을 때도 지워준다.
    private fun hideHistoryView(){
        binding.historyRecyclerView.isVisible = false
    }
    // 검색된 키워드 등록 처리
    private fun saveSearchKeyword(keyword: String){
        Log.i(TAG, "saveSearchKeyword 호출 성공")
        Thread{
            db.historyDao().insertHistory(History(null, keyword))
            Log.i(TAG, db.historyDao().getAll().toString()) //[History(uid=1, keyword=ajax), History(uid=2, keyword=ajax), History(uid=3, keyword=ajax), History(uid=4, keyword=ajax)]
        }.start()

    }
    // 검색 이력 삭제하기
    private fun deleteSearchKeyword(keyword: String){
        Thread{
            db.historyDao().delete(keyword)
            // 삭제하고 나서 뷰View 갱신처리하기
            showHistoryView()
        }.start()
    }

    companion object {
        private const val TAG="MainActivity"
    }
}