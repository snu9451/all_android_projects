package com.example.ktbook802.adapter

import android.util.Log
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
//import com.example.ktbook802.databinding.activitymainbinding
import com.example.ktbook802.databinding.ItemHistoryBinding
import com.example.ktbook802.model.History

class HistoryAdapter(val historyDeleteClickedListener:(String) -> Unit): ListAdapter<History, HistoryAdapter.ViewHolder>(diffUtil) {
    inner class ViewHolder(private val binding: ItemHistoryBinding): RecyclerView.ViewHolder(binding.root) {
        fun bind(historyModel: History) {
            binding.historyKeywordTextView.text = historyModel.keyword
            Log.i("HistoryAdaptor", historyModel.keyword.toString())
            binding.historyKeywordDeleteButton.setOnClickListener {
                historyDeleteClickedListener(historyModel.keyword.orEmpty())
            }
        }/////////////////////////////////end of inner: BookItemViewHolder
    }
    /* ================================== [[ 미리 만들어진 뷰홀더ViewHolder가 없을 때 실행될 함수 ]] ================================== */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder{
        Log.i("HistoryAdaptor", "onCreateViewHolder 호출 성공")
        return ViewHolder(ItemHistoryBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /* ============================= [[ 뷰홀더ViewHolder가 바인딩binding되었을 때 실제 데이터를 그려주기 ]] ============================= */
    override fun onBindViewHolder(holder: ViewHolder, position: Int){
        Log.i("HistoryAdaptor", "onBindViewHolder 호출 성공")
        holder.bind(currentList[position])
    }

    /* ======== [[ 리사이클러Recycler의 뷰 포지션의 값이 변경되었지만 똑같은 값일 때 또 가져올 필요가 없으므로 효율적 처리를 위해... ]] ======== */
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<History>(){
            // 아이템이 같은지 여부를 체크
            override fun areItemsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem == newItem
            }
            // 내용이 같은지 여부를 체크 - id가 같으면 내용이 같을 것이므로 id로 체크
            override fun areContentsTheSame(oldItem: History, newItem: History): Boolean {
                return oldItem.keyword == newItem.keyword
            }

        }
    }
}