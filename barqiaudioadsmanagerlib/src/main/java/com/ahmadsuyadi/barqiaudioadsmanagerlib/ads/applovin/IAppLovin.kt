package com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.applovin

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.IAds

interface IAppLovin : IAds {
    fun showBanner(adView: LinearLayout)
}