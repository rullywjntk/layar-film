package com.example.layarfilm.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.lifecycle.MutableLiveData
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.layarfilm.data.NewUpload
import com.example.layarfilm.databinding.ItemMovieBinding

class NewUploadAdapter: RecyclerView.Adapter<NewUploadAdapter.ViewHolder>() {

    private var list = mutableListOf<NewUpload>()

    fun setMovie(movie: ArrayList<NewUpload>) {
        this.list = movie.toMutableList()
        notifyDataSetChanged()
    }

//    fun setData(items: ArrayList<NewUpload>) {
//        mData.clear()
//        mData.addAll(items)
//        notifyDataSetChanged()
//    }

    inner class ViewHolder(private val binding: ItemMovieBinding) :
        RecyclerView.ViewHolder(binding.root) {
        fun bind(movie: NewUpload) {
            binding.apply {
                tvTitle.text = movie.title
                tvDuration.text = movie.duration
                Glide.with(itemView)
                    .load(movie.thumbnail)
                    .centerCrop()
                    .into(iVNewUpload)
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val view = ItemMovieBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ViewHolder(view)
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bind(list[position])
    }

    override fun getItemCount(): Int = list.size
}