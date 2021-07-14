package com.example.chunggo801.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.chunggo801.databinding.ListitemChatBinding
import com.example.chunggo801.model.ChatList
// 내가 작성한 클래스임 =================================================================================
class ChatListAdapter: ListAdapter<ChatList, ChatListAdapter.ChatListViewHolder>(diffUtil) {

    // 내부 클래스로 가져야 하는 ViewHolder
    inner class ChatListViewHolder(private val binding: ListitemChatBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(chatListModel: ChatList){
            // 이미지는 Glide 이용하면 될 듯
            binding.chatNickname.text = chatListModel.chat_nickname
            binding.chatContent.text = chatListModel.chat_content
            /*
            // 서버로부터 이미지 정보를 가져와서 이미지 로딩을 처리하기
            Glide
                .with(binding.coverImageView.context)  // binding이 가리키는 것은 xml 즉, ViewGroup
                .load(bookModel.coverSmallUrl)
//                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(binding.coverImageView)

             */
        }
    }/////////////////////////////////end of inner: ChatListViewHolder

    /* ================================== [[ 미리 만들어진 뷰홀더ViewHolder가 없을 때 실행될 함수 ]] ================================== */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ChatListViewHolder {
        return ChatListViewHolder(ListitemChatBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /* ============================= [[ 뷰홀더ViewHolder가 바인딩binding되었을 때 실제 데이터를 그려주기 ]] ============================= */
    override fun  onBindViewHolder(holder: ChatListViewHolder, position: Int){
        holder.bind(currentList[position])
    }

    /* ======== [[ 리사이클러Recycler의 뷰 포지션의 값이 변경되었지만 똑같은 값일 때 또 가져올 필요가 없으므로 효율적 처리를 위해... ]] ======== */
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<ChatList>(){
            // 아이템이 같은지 여부를 체크
            override fun areItemsTheSame(oldItem: ChatList, newItem: ChatList): Boolean {
                return oldItem == newItem
            }
            // 내용이 같은지 여부를 체크 - id가 같으면 내용이 같을 것이므로 id로 체크
            override fun areContentsTheSame(oldItem: ChatList, newItem: ChatList): Boolean {
                // 임시로 내용으로 비교하도록 했음...
                return oldItem.chat_content == newItem.chat_content
            }

        }
    }

}