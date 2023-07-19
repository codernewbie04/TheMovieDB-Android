package com.akmalmf.themoviedb.ui.detail_movie

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.akmalmf.themoviedb.abstraction.base.BaseBottomSheetFragment
import com.akmalmf.themoviedb.databinding.FragmentVideoBinding
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.YouTubePlayer
import com.pierfrancescosoffritti.androidyoutubeplayer.core.player.listeners.AbstractYouTubePlayerListener


/**
 * Created by Akmal Muhamad Firdaus on 2023/07/19 08:29
 * akmalmf007@gmail.com
 */
class VideoBottomSheet: BaseBottomSheetFragment() {
    private var _binding: FragmentVideoBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentVideoBinding.inflate(inflater, container, false)
        return binding.root
    }
    override fun initView(savedInstanceState: Bundle?) {

        arguments?.let {
            val videoId = it.getString(KEY)
            binding.youtubePlayer.addYouTubePlayerListener(object : AbstractYouTubePlayerListener() {
                override fun onReady(youTubePlayer: YouTubePlayer) {
                    videoId?.let { it1 -> youTubePlayer.loadVideo(it1, 0f) }
                }
            })

        }
    }

    override fun initObservable() {
    }

    companion object {
        private const val KEY = "video_id"
        fun newInstance(videoId: String): VideoBottomSheet {
            val fragment = VideoBottomSheet()
            val args = Bundle()
            args.putString(KEY, videoId)
            fragment.arguments = args
            return fragment
        }
    }
}