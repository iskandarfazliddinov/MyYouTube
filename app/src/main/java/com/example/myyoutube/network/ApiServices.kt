package com.example.myyoutube.network

import com.example.myyoutube.models.ChannelModel
import com.example.myyoutube.models.PlaylistYtModel
import com.example.myyoutube.models.VideoYtModel
import retrofit2.Call
import retrofit2.http.GET
import retrofit2.http.Query

interface ApiServices {

    @GET("channels")
    fun getChannel(
        @Query("part") part:String,
        @Query("id") id:String
    ):Call<ChannelModel>

    @GET("search")
    fun getVideo(
        @Query("part") part: String,
        @Query("channelId") channelId:String,
        @Query("order") order:String,
        @Query("pageToken") pageToken:String?,
        @Query("q") query : String?
    ):Call<VideoYtModel>

    @GET("playlists")
    fun getPlaylist(
        @Query("part") part: String,
        @Query("channelId") channelId: String,
        @Query("maxResults") maxResults: String,
        @Query("pageToken") pageToken:String?
    ):Call<PlaylistYtModel>
}