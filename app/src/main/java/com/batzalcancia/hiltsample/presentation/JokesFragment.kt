package com.batzalcancia.hiltsample.presentation

import android.content.Context
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.lifecycle.lifecycleScope
import com.batzalcancia.hiltsample.R
import com.batzalcancia.hiltsample.databinding.FragmentJokesListBinding
import com.batzalcancia.hiltsample.databinding.ItemJokeBinding
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.flow.catch
import kotlinx.coroutines.flow.launchIn
import kotlinx.coroutines.flow.onEach

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes_list) {

    private lateinit var viewBinding: FragmentJokesListBinding

    private val viewModel: JokesViewModel by viewModels()

    private val jokesAdapter by lazy { JokesAdapter() }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentJokesListBinding.bind(view)

        setupViews()
        setupInputs()

        viewModel.pagingData.onEach {
            jokesAdapter.submitData(it)
        }.launchIn(viewLifecycleOwner.lifecycleScope)
    }

    private fun setupInputs() {

    }
    private fun setupViews() {
        viewBinding.rcvJokes.adapter = jokesAdapter
    }

}