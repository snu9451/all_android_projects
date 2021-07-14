package com.example.ktbook802.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.example.ktbook802.model.History

@Dao
interface HistoryDao {
    // 조건 검색 이력 전체를 가져오는 함수
    @Query("SELECT * FROM history")
    fun getAll(): List<History>
    // 검색 시 키워드를 등록하는 함수
    @Insert
    fun insertHistory(history: History)
    // 검색 이력에서 [x]버튼을 클릭 시 삭제 처리를 해줄 함수
    @Query("DELETE FROM history WHERE keyword == :keyword")
    fun delete(keyword: String)
}