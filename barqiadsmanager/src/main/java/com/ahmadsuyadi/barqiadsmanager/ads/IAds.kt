package com.ahmadsuyadi.barqiadsmanager.ads

import android.app.Activity

interface IAds {

    fun initialize(activity: Activity)

    fun showInterstitial()
}