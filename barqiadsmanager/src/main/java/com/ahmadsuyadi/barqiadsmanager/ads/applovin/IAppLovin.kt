package com.ahmadsuyadi.barqiadsmanager.ads.applovin

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ads.IAds

interface IAppLovin : IAds {
    fun showBanner(adView: LinearLayout)
}