package com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.unityads

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.IAds

interface IUnityAds : IAds {
    fun showBanner(adView: LinearLayout)
}