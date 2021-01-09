package com.ahmadsuyadi.barqiadsmanager.ads.mopub

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ads.IAds
import com.mopub.mobileads.MoPubView

interface IMopub : IAds {
    fun showBanner(moPubView: LinearLayout)
}