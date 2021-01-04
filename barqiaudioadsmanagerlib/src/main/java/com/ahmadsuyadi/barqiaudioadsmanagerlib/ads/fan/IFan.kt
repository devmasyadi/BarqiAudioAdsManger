package com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.fan

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.IAds

interface IFan : IAds {
    fun showBanner(adView: LinearLayout)
}