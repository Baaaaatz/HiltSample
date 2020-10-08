package com.batzalcancia.hiltsample.presentation

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.batzalcancia.hiltsample.R
import com.batzalcancia.hiltsample.databinding.FragmentJokesListBinding
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class JokesFragment : Fragment(R.layout.fragment_jokes_list) {

    lateinit var viewBinding: FragmentJokesListBinding

    private val viewModel: JokesViewModel by viewModels()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewBinding = FragmentJokesListBinding.bind(view)
        viewModel //lazily kasi yung viewmodel. Hanapan ko pa workaround pano ma call agad. haha
    }

}