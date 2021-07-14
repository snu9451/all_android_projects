package com.example.ktbook801

import com.google.gson.annotations.SerializedName

// 코틀린에는 data class라는 게 제공된다. 이 경우 중괄호 대신 소괄호를 사용한다.
data class SearchBookDTO(
    // json과 매핑시켜주는 어노테이션
    @SerializedName("title") val title: String,
    @SerializedName("item") val books: List<Book>
)