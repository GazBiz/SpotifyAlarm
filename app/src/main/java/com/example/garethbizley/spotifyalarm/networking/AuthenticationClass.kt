package com.example.garethbizley.spotifyalarm.networking

import android.util.Log
import com.example.garethbizley.spotifyalarm.PlayAlarmActivity
import com.spotify.sdk.android.player.ConnectionStateCallback
import com.spotify.sdk.android.player.Error

/**
 * Created by Gaz Biz on 7/12/17.
 */
class AuthenticationClass constructor (alarmActivity: PlayAlarmActivity): ConnectionStateCallback {

    var TAG = "authenticationClass"
    var alarmActivity = alarmActivity

    override fun onLoggedIn() {
        Log.d(TAG, "User logged in")
        alarmActivity.startPlayer()
    }

    override fun onLoggedOut() {
        Log.d(TAG, "User logged out")
    }

    override fun onLoginFailed(error: Error) {
    }

    override fun onTemporaryError() {
        Log.d(TAG, "Temporary error occurred")
    }

    override fun onConnectionMessage(message: String) {
        Log.d(TAG, "Received connection message: " + message)
    }
}