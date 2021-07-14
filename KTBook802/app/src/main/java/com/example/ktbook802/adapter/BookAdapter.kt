package com.example.ktbook802.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
//import com.example.ktbook802.databinding.activitymainbinding
import com.example.ktbook802.databinding.ItemBookBinding
import com.example.ktbook802.model.Book

class BookAdapter: ListAdapter<Book, BookAdapter.BookItemViewHolder>(diffUtil) {
    // json으로 받아온 정보를 처리하기 위해서 ViewHolder를 구현해야 함
    inner class BookItemViewHolder(private val binding: ItemBookBinding): RecyclerView.ViewHolder(binding.root){
        fun bind(bookModel: Book){
            binding.titleTextView.text = bookModel.title // bookModel을 통해 json에서 읽어들인 title을 대입해준다.
            binding.descriptionTextView.text = bookModel.description // bookModel을 통해 json에서 읽어들인 description을 대입해준다.
            // 서버로부터 이미지 정보를 가져와서 이미지 로딩을 처리하기
            Glide
                .with(binding.coverImageView.context)  // binding이 가리키는 것은 xml 즉, ViewGroup
                .load(bookModel.coverSmallUrl)
//                .centerCrop()
//                .placeholder(R.drawable.loading_spinner)
                .into(binding.coverImageView)
        }
    }/////////////////////////////////end of inner: BookItemViewHolder

    /* ================================== [[ 미리 만들어진 뷰홀더ViewHolder가 없을 때 실행될 함수 ]] ================================== */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookItemViewHolder{
        return BookItemViewHolder(ItemBookBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    /* ============================= [[ 뷰홀더ViewHolder가 바인딩binding되었을 때 실제 데이터를 그려주기 ]] ============================= */
    override fun  onBindViewHolder(holder: BookItemViewHolder, position: Int){
        holder.bind(currentList[position])
    }

    /* ======== [[ 리사이클러Recycler의 뷰 포지션의 값이 변경되었지만 똑같은 값일 때 또 가져올 필요가 없으므로 효율적 처리를 위해... ]] ======== */
    companion object{
        val diffUtil = object: DiffUtil.ItemCallback<Book>(){
            // 아이템이 같은지 여부를 체크
            override fun areItemsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem == newItem
            }
            // 내용이 같은지 여부를 체크 - id가 같으면 내용이 같을 것이므로 id로 체크
            override fun areContentsTheSame(oldItem: Book, newItem: Book): Boolean {
                return oldItem.id == newItem.id
            }

        }
    }
}