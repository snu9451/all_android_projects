package com.example.ktbook801

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Parcel
import android.os.Parcelable
import android.util.Log
import androidx.recyclerview.widget.RecyclerView
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        // 레트로핏 구현체 생성
        val retrofit = Retrofit.Builder()
            .baseUrl("https://book.interpark.com")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val bookService = retrofit.create(BookService::class.java)

        bookService.getBestSellerBooks("23B7D0EC2D47DCBD19515C28734B9E52486E8819FAF85957E77F53C1EE38CE4C")
            .enqueue(object: Callback<BestSellerDTO>{
                override fun onResponse(
                    call: Call<BestSellerDTO>,
                    response: Response<BestSellerDTO>
                ) {
                    if (response.isSuccessful.not()){
                        Log.e(TAG, "**NOT**SUCCESS")
                        return;
                    }
                    // let - scope
                    response.body()?.let {
                        Log.d(TAG, "body에서 꺼내오기"+it.toString())
                        it.books.forEach { book ->
                            Log.d(TAG, book.toString())
                        }
                    }
                }

                override fun onFailure(call: Call<BestSellerDTO>, t: Throwable) {
                    // 실패 처리하는 부분
                    // override 관계에 있기 때문에 무조건 override해야 함.
                    Log.e(TAG, t.toString())
                }


            })
    }////////////////////end of onCreate
    companion object {
        private const val TAG="MainActivity"
    }
}