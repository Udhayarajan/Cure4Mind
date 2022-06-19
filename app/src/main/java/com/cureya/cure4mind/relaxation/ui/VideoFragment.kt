package com.cureya.cure4mind.relaxation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.navigation.fragment.navArgs
import com.cureya.cure4mind.R
import com.cureya.cure4mind.databinding.FragmentRelaxationVideoBinding
import com.cureya.cure4mind.util.API_KEY
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.youtube.player.*

class VideoFragment : Fragment() {

    private lateinit var binding: FragmentRelaxationVideoBinding
    private val navArgument: VideoFragmentArgs by navArgs()

    private val _videoLoadingStatus = MutableLiveData(false)
    val videoLoadingStatus: LiveData<Boolean> get() = _videoLoadingStatus

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        activity?.window?.setFlags(
            WindowManager.LayoutParams.FLAG_FULLSCREEN,
            WindowManager.LayoutParams.FLAG_FULLSCREEN)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentRelaxationVideoBinding.inflate(inflater, container, false)

        initializeYoutubeFragment()

        return binding.root
    }

    // YouTubePlayerApi is buggy; compiler is unable to detect
    // YouTubeSupportFragment as a Fragment.
    // While the following block compiles and runs
    // without any issue, I am looking for a solution
    private fun initializeYoutubeFragment() {
        val videoUrl = navArgument.videoUrl

        val youtubePlayerFragment = YouTubePlayerSupportFragmentX.newInstance()
        val transaction = childFragmentManager.beginTransaction()
        transaction.replace(R.id.youtube_fragment, youtubePlayerFragment)
        transaction.commit()

        youtubePlayerFragment.initialize(
            API_KEY,
            object: YouTubePlayer.OnInitializedListener {

                override fun onInitializationSuccess(
                    provider: YouTubePlayer.Provider?,
                    ytPlayer: YouTubePlayer?,
                    p2: Boolean
                ) {
                    val videoId = videoUrl.split('=')[1]
                    ytPlayer?.loadVideo(videoId)
                    ytPlayer?.play()
                    _videoLoadingStatus.value = true
                }
                override fun onInitializationFailure(
                    provider: YouTubePlayer.Provider?,
                    p1: YouTubeInitializationResult?
                ) {
                    Log.e("VideoViewModel", "Video Player Failed $p1")
                }
            }
        )
    }

    override fun onStart() {
        val bottomView = (activity as AppCompatActivity)
            .findViewById<BottomNavigationView>(R.id.nav_view)
        bottomView.visibility = View.GONE

        super.onStart()
    }

    override fun onStop() {
        activity?.window?.clearFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN)

        val bottomView = (activity as AppCompatActivity)
            .findViewById<BottomNavigationView>(R.id.nav_view)
        bottomView.visibility = View.VISIBLE

        super.onStop()
    }
}