package com.batzalcancia.hiltsample.data.paging

import android.util.Log
import androidx.paging.PagingSource
import com.batzalcancia.hiltsample.domain.entities.Joke
import com.batzalcancia.hiltsample.domain.usecases.GetJokes
import javax.inject.Inject

class JokesPaging @Inject constructor(private val getJokes: GetJokes) :
    PagingSource<Pair<Int, Int>, Joke>() {

    override suspend fun load(params: LoadParams<Pair<Int, Int>>): LoadResult<Pair<Int, Int>, Joke> {
        return try {
            val from = params.key?.first ?: 0
            val to = params.key?.second ?: 5
            val response = getJokes("$from-$to")

            LoadResult.Page(
                data = if(from < 0 || to <0) emptyList() else response,
                prevKey = Pair(from - 6, to - 6),
                nextKey = Pair(from + 6, to + 6)
            )

        } catch (e: Exception) {
            LoadResult.Error(e)
        }
    }

}