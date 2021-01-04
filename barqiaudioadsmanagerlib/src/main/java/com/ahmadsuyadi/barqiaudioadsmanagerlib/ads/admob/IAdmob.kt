package com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.admob

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiaudioadsmanagerlib.ads.IAds

interface IAdmob : IAds {
    fun showBanner(adView: LinearLayout)
}