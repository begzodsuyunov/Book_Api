package com.example.bookapi.utils

import android.annotation.SuppressLint
import android.app.Dialog
import android.content.Context
import android.os.Bundle
import android.widget.Toast
import com.example.bookapi.data.remote.dto.book.request.PostBookRequest
import com.example.bookapi.data.remote.dto.book.request.PutBookRequest
import com.example.bookapi.databinding.DialogBinding


class InitialDialog(context: Context) : Dialog(context) {
    private lateinit var binding: DialogBinding

    private val title by lazy { binding.etTitle }
    private val desc by lazy { binding.etDesc }
    private val author by lazy { binding.etAuthor }
    private val pageCount by lazy { binding.etPageCount }
    private var id = -1


    private var okListener: ((PostBookRequest) -> Unit)? = null

    fun setOkListener(block: (PostBookRequest) -> Unit) {
        okListener = block
    }

    private var okPutListener: ((PutBookRequest) -> Unit)? = null

    fun setPutOkListener(block: (PutBookRequest) -> Unit) {
        okPutListener = block
    }


    @SuppressLint("UseCompatLoadingForDrawables")
    override fun onCreate(savedInstanceState: Bundle?) {
        binding = DialogBinding.inflate(layoutInflater)
        config(binding)


        binding.btnOk.setOnClickListener {
            if (title.text.length >= 3 && desc.text.length >= 10 && author.text.length >= 10 && pageCount.text.isNotEmpty()) {
                if (id != -1) {
                    okPutListener?.invoke(
                        PutBookRequest(
                            id,
                            title.text.toString(),
                            author.text.toString(),
                            desc.text.toString(),
                            pageCount.text.toString().toInt()
                        )
                    )
                    dismiss()
                } else {
                    okListener?.invoke(
                        PostBookRequest(
                            title.text.toString(),
                            author.text.toString(),
                            desc.text.toString(),
                            pageCount.text.toString().toInt()
                        )
                    )
                    dismiss()
                }
            } else Toast.makeText(context, "ma'lumotlar to'liq emas", Toast.LENGTH_SHORT).show()
        }
    }

    override fun dismiss() {
        super.dismiss()
        title.setText("")
        desc.setText("")
        author.setText("")
        pageCount.setText("")
        id = -1

    }

    fun putBook(data: PutBookRequest) {
        id = data.id
        title.setText(data.title)
        desc.setText(data.description)
        author.setText(data.author)
        pageCount.setText(data.pageCount.toString())
    }
}