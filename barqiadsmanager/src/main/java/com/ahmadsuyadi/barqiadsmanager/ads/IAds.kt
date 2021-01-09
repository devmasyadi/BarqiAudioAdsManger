package com.ahmadsuyadi.barqiadsmanager.ads

import android.app.Activity
import android.widget.RelativeLayout

interface IAds {
    fun initialize(activity: Activity)
    fun showBanner(adView: RelativeLayout)
    fun showInterstitial()
}