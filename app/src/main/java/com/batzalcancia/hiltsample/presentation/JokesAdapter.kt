package com.batzalcancia.hiltsample.presentation

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.paging.PagingDataAdapter
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import androidx.viewbinding.ViewBinding
import com.batzalcancia.hiltsample.R
import com.batzalcancia.hiltsample.databinding.ItemJokeBinding
import com.batzalcancia.hiltsample.domain.entities.Joke

class JokesAdapter(private val setupBinding: (View) -> ItemJokeBinding = ItemJokeBinding::bind) :
    PagingDataAdapter<Joke, ViewBindingViewHolder<ItemJokeBinding>>(JokesDiffUtil) {

    override fun onBindViewHolder(holder: ViewBindingViewHolder<ItemJokeBinding>, position: Int) {
        val joke = getItem(position)
        holder.viewBinding.run {
            txtJoke.text = joke?.joke
        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): ViewBindingViewHolder<ItemJokeBinding> {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.item_joke, parent, false)
        return ViewBindingViewHolder(setupBinding(view))
    }

    object JokesDiffUtil : DiffUtil.ItemCallback<Joke>() {
        override fun areItemsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem.id == newItem.id
        }

        override fun areContentsTheSame(oldItem: Joke, newItem: Joke): Boolean {
            return oldItem == newItem
        }
    }
}

class ViewBindingViewHolder<VB : ViewBinding>(
    val viewBinding: VB
) : RecyclerView.ViewHolder(viewBinding.root)
