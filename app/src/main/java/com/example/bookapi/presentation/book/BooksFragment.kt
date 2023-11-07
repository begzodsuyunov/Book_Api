package com.example.bookapi.presentation.book

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import by.kirich1409.viewbindingdelegate.viewBinding
import com.example.bookapi.R
import com.example.bookapi.databinding.FragmentBooksBinding
import com.example.bookapi.presentation.book.vm.BooksViewModel
import com.example.bookapi.presentation.viewmodel.BooksViewModelImpl
import com.example.bookapi.utils.InitialDialog
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class BooksFragment : Fragment(R.layout.fragment_books) {
    private val viewBinding: FragmentBooksBinding by viewBinding(FragmentBooksBinding::bind)
    private val viewModel: BooksViewModel by viewModels<BooksViewModelImpl>()
    private val adapter by lazy { BookAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val dialog = InitialDialog(requireContext())

        viewBinding.recyclerView.adapter = adapter

        viewModel.getAllBooks.onEach {
            Log.d("CHANGEF", "screen allBooks-> ${it}")
            adapter.submitList(it)

        }.launchIn(viewLifecycleOwner.lifecycleScope)

        viewBinding.fab.setOnClickListener {
            dialog.show()
        }
        dialog.setOkListener {
            viewModel.postBook(it)
        }
        adapter.setEditClickListener {
            dialog.show()
            dialog.putBook(it)
        }
        adapter.setFavClickListener {
            viewModel.changeFavBook(it)
            Log.d("FAVV", "screen -> ${it.bookId}")
            Log.d("CHANGEF", "screen -> ${it.bookId}")
            Toast.makeText(requireContext(), "fav ${it.bookId}", Toast.LENGTH_SHORT).show()

        }
        adapter.setDeleteClickListener {
            Toast.makeText(requireContext(), "delete ${it.bookId}", Toast.LENGTH_SHORT).show()
            viewModel.deleteBook(it)
        }
        dialog.setPutOkListener {
            viewModel.putBook(it)
            Toast.makeText(requireContext(), "edit ${it.title}", Toast.LENGTH_SHORT).show()
        }

    }
}