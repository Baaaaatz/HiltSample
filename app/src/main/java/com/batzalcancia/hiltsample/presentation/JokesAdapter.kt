package com.batzalcancia.hiltsample.presentation

import android.app.UiModeManager
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.batzalcancia.hiltsample.R
import com.batzalcancia.hiltsample.databinding.ItemJokeBinding
import com.batzalcancia.hiltsample.databinding.ItemSeparatorBinding
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.utils.UiModel
import com.batzalcancia.hiltsample.utils.ViewBindingViewHolder

class JokesAdapter(
    private val setupJokesBinding: (View) -> ViewBinding,
    private val setupSeparatorBinding: (View) -> ViewBinding
) : PagingDataAdapter<UiModel, ViewBindingViewHolder<ViewBinding>>(JokesDiffUtil) {

    override fun onBindViewHolder(holder: ViewBindingViewHolder<ViewBinding>, position: Int) {
        val uiModel = getItem(position)
        uiModel?.let {
            when (it) {
                is UiModel.Jokes -> {
                    (holder.viewBinding as ItemJokeBinding).txtJoke.text = it.joke.joke
                }
                is UiModel.TypeSeparator -> {
                    (holder.viewBinding as ItemSeparatorBinding).txtSeparator.text = it.category
                }
            }
        }
    }

    override fun getItemViewType(position: Int): Int {
        return when (getItem(position)) {
            is UiModel.Jokes -> R.layout.item_joke
            is UiModel.TypeSeparator -> R.layout.item_separator
            null -> throw UnsupportedOperationException("Unknown view")
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<ViewBinding> {
        return when (viewType) {
            R.layout.item_joke -> {
                val view =
                    LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
                ViewBindingViewHolder(setupJokesBinding(view))
            }
            R.layout.item_separator -> {
                val view = LayoutInflater.from(parent.context)
                    .inflate(R.layout.item_separator, parent, false)
                ViewBindingViewHolder(setupSeparatorBinding(view))
            }
            else -> throw UnsupportedOperationException("Unknown ViewHolder")
        }
    }

    object JokesDiffUtil : DiffUtil.ItemCallback<UiModel>() {
        override fun areContentsTheSame(oldItem: UiModel, newItem: UiModel): Boolean =
            oldItem == newItem

        override fun areItemsTheSame(oldItem: UiModel, newItem: UiModel): Boolean {
            return (oldItem is UiModel.Jokes && newItem is UiModel.Jokes && oldItem.joke.id == newItem.joke.id
                    || oldItem is UiModel.TypeSeparator && newItem is UiModel.TypeSeparator && oldItem.category == newItem.category)
        }
    }
}
