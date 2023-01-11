package com.example.myyoutube.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.myyoutube.databinding.ItemPlaylistBinding
import com.example.myyoutube.diffutils.PlaylistDiffUtil
import com.example.myyoutube.models.PlaylistYtModel

class PlaylistAdapter:RecyclerView.Adapter<RecyclerView.ViewHolder>() {

    private val oldItem = ArrayList<PlaylistYtModel.PlalistItem>()

    class PlaylistHolder(itemView:ItemPlaylistBinding):
        RecyclerView.ViewHolder(itemView.root){
        private val binding = itemView

        fun setData(data:PlaylistYtModel.PlalistItem ){
            binding.tvPlaylistTitle.text = data.snippetYt.title
            val videoCount = "${data.contentDetail.itemCount} videos"
            binding.tvVideoCount.text = videoCount
            Glide.with(binding.root).load(data.snippetYt.thumbnails.high.url)
                .into(binding.thumbnail)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        val view = ItemPlaylistBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return PlaylistHolder(view)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        (holder as PlaylistHolder).setData(oldItem[position])
    }

    override fun getItemCount(): Int {
        return oldItem.size
    }

    fun setData(newList:List<PlaylistYtModel.PlalistItem>, rv:RecyclerView){
        val playlistDiff = PlaylistDiffUtil(oldItem,newList)
        val diff = DiffUtil.calculateDiff(playlistDiff)
        oldItem.addAll(newList)
        diff.dispatchUpdatesTo(this)
        rv.scrollToPosition(oldItem.size-newList.size)
    }
}