package com.ahmadsuyadi.barqiadsmanager.ads

import android.app.Activity
import android.widget.LinearLayout

interface IAds {
    fun initialize(activity: Activity)
    fun showBanner(adView: LinearLayout)
    fun showInterstitial()
}