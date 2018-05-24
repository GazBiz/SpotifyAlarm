package com.example.garethbizley.spotifyalarm

import android.app.Activity
import android.os.Bundle
import android.util.Log
import com.example.garethbizley.spotifyalarm.system.AlarmIntentManager
import kotlinx.android.synthetic.main.edit_alarm_view.*
import java.util.*

/**
 * Created by Gaz Biz on 24/5/18.
 */
class EditAlarmActivity: Activity() {

    val TAG = javaClass.name

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.edit_alarm_view)
        timePicker.setIs24HourView(true)

        val alarmIntentManager = AlarmIntentManager(this)

        createAlarm.setOnClickListener{

            //get the minute & hour values from the spinner and set them on todays date
            val minute = timePicker.currentMinute
            val hour = timePicker.currentHour
            val alarmTime = Calendar.getInstance()

            alarmTime.set(Calendar.MINUTE, minute)
            alarmTime.set(Calendar.HOUR_OF_DAY, hour)

            //get the time now and the time for the alarm in milliseconds
            val alarmMillis = alarmTime.timeInMillis
            val nowMillis = Date().time

            //compare - if that time for alarm has already passed, set it for tomorrow instead
            if (alarmMillis < nowMillis){
                Log.d(TAG, "Alarm is earlier than now, set for tomoz")
                alarmTime.add(Calendar.DAY_OF_YEAR, 1)
            }
            alarmIntentManager.setNewAlarmIntent(alarmTime)
        }

    }
}