package com.ahmadsuyadi.barqiadsmanager.ads.fan

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ads.IAds

interface IFan : IAds {
    fun showBanner(adView: LinearLayout)
}