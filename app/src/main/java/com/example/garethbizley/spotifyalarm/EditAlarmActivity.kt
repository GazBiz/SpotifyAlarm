package com.example.garethbizley.spotifyalarm

import android.app.Activity
import android.os.Bundle
import kotlinx.android.synthetic.main.edit_alarm_view.*

/**
 * Created by Gaz Biz on 24/5/18.
 */
class EditAlarmActivity: Activity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_alarm_view)
        timePicker.setIs24HourView(true)

        createAlarm.setOnClickListener{
            //create pending intent and fire
            val minute = timePicker.currentMinute
            val hour = timePicker.currentHour

        }

    }
    //set up view with ability to set time and save
    //on save, create pending intent
    //logic for resetting intent for tomoz in some other class - should come from PlayAlarm actually
}