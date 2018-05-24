package com.example.garethbizley.spotifyalarm.utils

import java.util.*

/**
 * Created by Gaz Biz on 24/5/18.
 */
class AlarmUtils {

    companion object {

        val CLIENT_ID = "5c685a98402b4688984cd83feab38d63"
        val REDIRECT_URI = "spotifyalarm-custom-login://callback"
        val REQUEST_CODE = 1337
        val PLAYLIST_URL = "spotify:user:gazbiz:playlist:5qQnteFoTyZcQU9eZuDvpG"
        fun rand(from: Int, to: Int) : Int {
            val random = Random()
            return random.nextInt(to - from) + from
        }
    }

}