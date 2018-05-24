package com.example.garethbizley.spotifyalarm

import android.app.Activity
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.view.WindowManager
import com.example.garethbizley.spotifyalarm.networking.AuthenticationClass
import com.example.garethbizley.spotifyalarm.networking.SpotifyService
import com.example.garethbizley.spotifyalarm.utils.AlarmUtils
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.*
import kotlinx.android.synthetic.main.play_alarm_view.*

/**
 * Created by Gaz Biz on 2/12/17.
 */
class PlayAlarmActivity: Activity() {

    var spotifyPlayer:SpotifyPlayer? = null
    var TAG = "playAlarmActivity"
    var authenticator = AuthenticationClass(this)
    val spotifyService = SpotifyService(this)

    override fun onCreate(savedInstanceState: Bundle?) {
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_SHOW_WHEN_LOCKED);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_DISMISS_KEYGUARD);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON);
        getWindow().addFlags(WindowManager.LayoutParams.FLAG_TURN_SCREEN_ON);
        super.onCreate(savedInstanceState)
        setContentView(R.layout.play_alarm_view)

        spotifyService.authenticateUser()

        stopButton.setOnClickListener {
            spotifyPlayer!!.shutdown()
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, intent: Intent) {
        super.onActivityResult(requestCode, resultCode, intent)

        // Check if result comes from the correct activity
        if (requestCode == AlarmUtils.REQUEST_CODE) {
            val response = AuthenticationClient.getResponse(resultCode, intent)
            if (response.type == AuthenticationResponse.Type.TOKEN) {
                Log.d(TAG, response.accessToken)
                val playerConfig = Config(this, response.accessToken, AlarmUtils.CLIENT_ID)

                Spotify.getPlayer(playerConfig, this, object : SpotifyPlayer.InitializationObserver {
                    override fun onInitialized(player: SpotifyPlayer) {

                        spotifyPlayer = player
                        (spotifyPlayer as SpotifyPlayer).addConnectionStateCallback(authenticator)
                        (spotifyPlayer as SpotifyPlayer).addNotificationCallback(spotifyService)
                    }

                    override fun onError(throwable: Throwable) {
                        Log.e("MainActivity", "Could not initialize player: " + throwable.message)
                    }
                })
            }
        }
    }

    override fun onDestroy() {
        Spotify.destroyPlayer(this)
        super.onDestroy()
    }


    fun startPlayer(){
        var trackNum = AlarmUtils.rand(0, 77)
        spotifyPlayer!!.playUri(spotifyService, AlarmUtils.PLAYLIST_URL, trackNum, 1)
    }

}