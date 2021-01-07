package com.ahmadsuyadi.barqiadsmanager.ads.admob

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ads.IAds

interface IAdmob : IAds {
    fun showBanner(adView: LinearLayout)
}