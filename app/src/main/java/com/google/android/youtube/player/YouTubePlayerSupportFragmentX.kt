package com.google.android.youtube.player

import android.os.Bundle
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.google.android.youtube.player.internal.ab
import com.google.android.youtube.player.internal.e


/**
 * @author Udhaya
 * Created on 18-06-2022
 */
/**
 * Please create this directories schema com.google.android.youtube.player and post the file there
 */

class YouTubePlayerSupportFragmentX : Fragment(),
    YouTubePlayer.Provider {
    private val a: A = A(this)
    private var b: Bundle? = null
    private var c: YouTubePlayerView? = null
    private var d: String? = null
    var e: YouTubePlayer.OnInitializedListener? = null
    private val f = false
    override fun initialize(var1: String, var2: YouTubePlayer.OnInitializedListener?) {
        d = ab.a(var1, "Developer key cannot be null or empty")
        e = var2
        a()
    }

    private fun a() {
        if (c != null && e != null) {
            c!!.a(f)
            c!!.a(this.activity, this, d, e, b)
            b = null
            e = null
        }
    }

    override fun onCreate(var1: Bundle?) {
        super.onCreate(var1)
        b = var1?.getBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE")
    }

    override fun onCreateView(var1: LayoutInflater, var2: ViewGroup?, var3: Bundle?): View? {
        c = YouTubePlayerView(this.activity, null as AttributeSet?, 0, a)
        a()
        return c
    }

    override fun onStart() {
        super.onStart()
        c?.a()
    }

    override fun onResume() {
        super.onResume()
        c!!.b()
    }

    override fun onPause() {
        c!!.c()
        super.onPause()
    }

    override fun onSaveInstanceState(var1: Bundle) {
        super.onSaveInstanceState(var1)
        val var2 = if (c != null) c!!.e() else b!!
        var1.putBundle("YouTubePlayerSupportFragment.KEY_PLAYER_VIEW_STATE", var2)
    }

    override fun onStop() {
        c!!.d()
        super.onStop()
    }

    override fun onDestroyView() {
        c!!.c(this.activity!!.isFinishing)
        c = null
        super.onDestroyView()
    }

    override fun onDestroy() {
        if (c != null) {
            val var1 = this.activity
            c!!.b(var1 == null || var1.isFinishing)
        }
        super.onDestroy()
    }

    companion object {
        fun newInstance(): YouTubePlayerSupportFragmentX {
            return YouTubePlayerSupportFragmentX()
        }
    }
}


class A(private val youTubePlayerSupportFragmentX: YouTubePlayerSupportFragmentX) : YouTubePlayerView.b {
    override fun a(
        var1: YouTubePlayerView?,
        var2: String,
        var3: YouTubePlayer.OnInitializedListener?,
    ) {
        youTubePlayerSupportFragmentX.initialize(var2, youTubePlayerSupportFragmentX.e)
    }


    override fun a(p0: YouTubePlayerView?) {}
}