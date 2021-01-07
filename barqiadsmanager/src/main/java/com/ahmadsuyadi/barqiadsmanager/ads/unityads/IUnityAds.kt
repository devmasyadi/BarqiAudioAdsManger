package com.ahmadsuyadi.barqiadsmanager.ads.unityads

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ads.IAds

interface IUnityAds : IAds {
    fun showBanner(adView: LinearLayout)
}