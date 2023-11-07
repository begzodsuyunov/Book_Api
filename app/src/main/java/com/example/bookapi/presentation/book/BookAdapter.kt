package com.example.bookapi.presentation.book

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.bookapi.R
import com.example.bookapi.data.remote.dto.book.request.ChangeFavRequest
import com.example.bookapi.data.remote.dto.book.request.DeleteBookRequest
import com.example.bookapi.data.remote.dto.book.request.PutBookRequest
import com.example.bookapi.data.remote.dto.book.response.GetBooksResponse


class BookAdapter : ListAdapter<GetBooksResponse, BookAdapter.VH>(CallBack) {

    private var editClickListener: ((PutBookRequest) -> Unit)? = null

    private var deleteClickListener: ((DeleteBookRequest) -> Unit)? = null

    private var favClickListener: ((ChangeFavRequest) -> Unit)? = null

    fun setEditClickListener(block: ((PutBookRequest) -> Unit)) {
        editClickListener = block
    }

    fun setDeleteClickListener(block: ((DeleteBookRequest) -> Unit)) {
        deleteClickListener = block
    }

    fun setFavClickListener(block: (ChangeFavRequest) -> Unit) {
        favClickListener = block
    }

    object CallBack : DiffUtil.ItemCallback<GetBooksResponse>() {
        override fun areItemsTheSame(
            oldItem: GetBooksResponse,
            newItem: GetBooksResponse
        ): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(
            oldItem: GetBooksResponse,
            newItem: GetBooksResponse
        ): Boolean {
            return oldItem.id == newItem.id &&
                    oldItem.author == newItem.author &&
                    oldItem.description == newItem.description &&
                    oldItem.title == newItem.title &&
                    oldItem.pageCount == newItem.pageCount &&
                    oldItem.isFav == newItem.isFav
        }
    }

    inner class VH(view: View) : RecyclerView.ViewHolder(view) {
        val title = view.findViewById<TextView>(R.id.tv_title)
        val desc = view.findViewById<TextView>(R.id.tv_desc)
        val author = view.findViewById<TextView>(R.id.tv_author)
        val fav = view.findViewById<ImageView>(R.id.img_fav)
        val edit = view.findViewById<ImageView>(R.id.img_edit)
        val delete = view.findViewById<ImageView>(R.id.img_delete)

        fun bind() {
            val item = getItem(absoluteAdapterPosition)
            title.text = item.title
            desc.text = item.description
            author.text = item.author
            Log.d("FAVV", "adapter bind ${item.id} vs ${item.isFav} ")
            if (item.isFav) {
                fav.setImageResource(R.drawable.ic_baseline_favorite_24)
            } else {
                fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
                Log.d("FAVV", "adapter else ")
            }
        }

        init {
            edit.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                editClickListener?.invoke(
                    PutBookRequest(
                        item.id,
                        item.title,
                        item.author,
                        item.description,
                        item.pageCount
                    )
                )
            }

            delete.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
                deleteClickListener?.invoke(DeleteBookRequest(item.id))
            }

            fav.setOnClickListener {
                val item = getItem(absoluteAdapterPosition)
//                if (item.isFav) {
//                    fav.setImageResource(R.drawable.ic_baseline_favorite_24)
//                } else {
//                    fav.setImageResource(R.drawable.ic_baseline_favorite_border_24)
//                }
                favClickListener?.invoke(ChangeFavRequest(item.id))
                Log.d("FAVV", "adapter - > ${item.isFav}")
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): BookAdapter.VH {
        return VH(
            LayoutInflater.from(parent.context).inflate(R.layout.item_book, parent, false)
        )
    }

    override fun onBindViewHolder(holder: BookAdapter.VH, position: Int) {
        holder.bind()
    }
}