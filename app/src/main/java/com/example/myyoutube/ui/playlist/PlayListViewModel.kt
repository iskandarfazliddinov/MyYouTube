package com.example.myyoutube.ui.playlist


import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myyoutube.models.PlaylistYtModel
import com.example.myyoutube.network.ApiConfig
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response

class PlayListViewModel : ViewModel() {

    private  val _playlist = MutableLiveData<PlaylistYtModel>()
    val playlist = _playlist
    private val _isLoading = MutableLiveData<Boolean>()
    val isLoading = _isLoading
    private val _isAllPlaylistLoaded = MutableLiveData<Boolean>()
    val isAllPlaylistLoaded = _isAllPlaylistLoaded
    var nextPageToken:String? = null

    init {
        getPlaylist()
    }

   fun getPlaylist(){
        _isLoading.value = true
        val client = ApiConfig
            .getService()
            .getPlaylist("snippet,contentDetails",
                "UCcWjuBiWNoCMlVza0VNf7-A",
                "10",
                nextPageToken)
        client.enqueue(object : Callback<PlaylistYtModel>{
            override fun onResponse(
                call: Call<PlaylistYtModel>,
                response: Response<PlaylistYtModel>
            ) {
                _isLoading.value = false
                if(response.isSuccessful){
                    val data = response.body()
                    if(data != null){
                        if(data.nextPageToken != null){
                            nextPageToken = data.nextPageToken
                        }else{
                            _isAllPlaylistLoaded.value = true
                        }
                        if(data.items.isNotEmpty()){
                            _playlist.value = data!!
                        }
                    }
                }
            }

            override fun onFailure(call: Call<PlaylistYtModel>, t: Throwable) {
                _isLoading.value = false
                Log.e(TAG,"Failure: ",t)
            }

        })
    }
    companion object{
        private val TAG = PlayListViewModel::class.java.simpleName
    }
}