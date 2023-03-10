package com.example.myyoutube.activities

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import com.example.myyoutube.BuildConfig
import com.example.myyoutube.R
import com.example.myyoutube.databinding.ActivityPlayerBinding
import com.google.android.material.snackbar.Snackbar
import com.google.android.youtube.player.YouTubeBaseActivity
import com.google.android.youtube.player.YouTubeInitializationResult
import com.google.android.youtube.player.YouTubePlayer

class PlayerActivity : YouTubeBaseActivity() {

    private lateinit var binding:ActivityPlayerBinding
    private var youtubePlayer: YouTubePlayer? = null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityPlayerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val videoId = intent.getStringExtra("video_id")
        val videoTitle = intent.getStringExtra("video_title")
        val videoDescription = intent.getStringExtra("video_description")

        binding.tvVideoTitle.text = videoTitle
        binding.tvVideoDescription.text = videoDescription


        binding.youtubePlayer.initialize(BuildConfig.YOUTUBE_API_KEY,
            object : YouTubePlayer.OnInitializedListener {
                override fun onInitializationSuccess(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubePlayer?,
                    p2: Boolean
                ) {
                    youtubePlayer = p1
                    youtubePlayer?.loadVideo(videoId)
                    youtubePlayer?.play()
                }

                override fun onInitializationFailure(
                    p0: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Snackbar.make(
                        binding.root,
                        "Failed to initialize youtube player",
                        Snackbar.LENGTH_SHORT
                    ).show()
                }

            }
        )

    }
}