package com.mike.mvpapptracker.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import coil.load
import coil.size.Scale
import com.mike.mvpapptracker.response.PopularMoviesListResponse
import com.mike.mvpapptracker.databinding.ItemMoviesCommonBinding
import com.mike.mvpapptracker.utils.Constants.POSTER_BASE_URL
import javax.inject.Inject

class PopularMoviesAdapter @Inject constructor() : RecyclerView.Adapter<PopularMoviesAdapter.ViewHolder>() {

    private lateinit var binding: ItemMoviesCommonBinding
    private var moviesList = emptyList<PopularMoviesListResponse.Result>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        binding = ItemMoviesCommonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder()
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.setData(moviesList[position])
        holder.setIsRecyclable(false)
    }

    override fun getItemCount(): Int = moviesList.size

    inner class ViewHolder() : RecyclerView.ViewHolder(binding.root) {
        fun setData(item: PopularMoviesListResponse.Result) {
            binding.apply {
                movieNameTxt.text = item.title
                movieRateTxt.text = item.voteAverage.toString()
                movieYearTxt.text = item.releaseDate
                movieCountryTxt.text = item.originalLanguage

                val moviePosterURL = POSTER_BASE_URL + item?.posterPath
                moviePosterImg.load(moviePosterURL) {
                    crossfade(true)
                    crossfade(800)
                    scale(Scale.FIT)
                }
                root.setOnClickListener {
                    onItemClickListener?.let {
                        it(item)
                    }
                }
            }
        }
    }

    private var onItemClickListener : ((PopularMoviesListResponse.Result) -> Unit)? = null
    fun setonItemClickListener(listener: (PopularMoviesListResponse.Result) -> Unit) {
        onItemClickListener=listener
    }

    fun bind(data:List<PopularMoviesListResponse.Result>){
        val moviesDiffUtils = MoviesDiffUtils(moviesList,data)
        val diffUtils = DiffUtil.calculateDiff(moviesDiffUtils)
        moviesList=data
        diffUtils.dispatchUpdatesTo(this)
    }

    //callback
    class MoviesDiffUtils(private val oldItem:List<PopularMoviesListResponse.Result>, private val newItem:List<PopularMoviesListResponse.Result>) : DiffUtil.Callback(){
        override fun getOldListSize(): Int {
            return oldItem.size
        }

        override fun getNewListSize(): Int {
            return newItem.size
        }

        override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            // === data type is compred here
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

        override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
            return oldItem[oldItemPosition] === newItem[newItemPosition]
        }

    }
}