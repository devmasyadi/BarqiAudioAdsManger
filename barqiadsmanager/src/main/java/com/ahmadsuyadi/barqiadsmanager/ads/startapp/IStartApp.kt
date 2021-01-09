package com.ahmadsuyadi.barqiadsmanager.ads.startapp

import android.widget.LinearLayout
import com.ahmadsuyadi.barqiadsmanager.ads.IAds

interface IStartApp : IAds {
    fun showBanner(banner: LinearLayout)
}