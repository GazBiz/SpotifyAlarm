package com.example.garethbizley.spotifyalarm.networking

import android.util.Log
import com.example.garethbizley.spotifyalarm.PlayAlarmActivity
import com.example.garethbizley.spotifyalarm.utils.AlarmUtils
import com.spotify.sdk.android.authentication.AuthenticationClient
import com.spotify.sdk.android.authentication.AuthenticationRequest
import com.spotify.sdk.android.authentication.AuthenticationResponse
import com.spotify.sdk.android.player.Error
import com.spotify.sdk.android.player.Player
import com.spotify.sdk.android.player.PlayerEvent

/**
 * Created by Gaz Biz on 24/5/18.
 */
class SpotifyService(playAlarmActivity: PlayAlarmActivity) : Player.NotificationCallback, Player.OperationCallback {

    val TAG = this.javaClass.name
    val playAlarmActivity = playAlarmActivity

    override fun onPlaybackEvent(playerEvent: PlayerEvent) {
        Log.d("MainActivity", "Playback event received: " + playerEvent.name)
    }

    override fun onPlaybackError(error: Error) {
        Log.d("MainActivity", "Playback error received: " + error.name)
    }

    override fun onSuccess() {
        Log.d(TAG, "onSuccess()")
    }

    override fun onError(p0: Error?) {
        Log.d(TAG, "Error: " + p0.toString())
    }

    fun authenticateUser(){
        val builder = AuthenticationRequest.Builder(AlarmUtils.CLIENT_ID,
                AuthenticationResponse.Type.TOKEN,
                AlarmUtils.REDIRECT_URI)
        builder.setScopes(arrayOf("user-read-private", "streaming"))
        val request = builder.build()

        AuthenticationClient.openLoginActivity(playAlarmActivity, AlarmUtils.REQUEST_CODE, request)
    }
}