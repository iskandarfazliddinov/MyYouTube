package com.example.myyoutube.diffutils

import androidx.recyclerview.widget.DiffUtil
import com.example.myyoutube.models.PlaylistYtModel
import com.example.myyoutube.models.VideoYtModel

class PlaylistDiffUtil(
    private val oldList: List<PlaylistYtModel.PlalistItem>,
    private val newList: List<PlaylistYtModel.PlalistItem>
):DiffUtil.Callback() {
    override fun getOldListSize(): Int {
        return oldList.size
    }

    override fun getNewListSize(): Int {
        return newList.size
    }

    override fun areItemsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        return oldList[oldItemPosition] == newList[newItemPosition]
    }

    override fun areContentsTheSame(oldItemPosition: Int, newItemPosition: Int): Boolean {
        val oldVideo = oldList[oldItemPosition]
        val newVideo = newList[newItemPosition]
        return oldVideo.snippetYt.title == newVideo.snippetYt.title
    }
}