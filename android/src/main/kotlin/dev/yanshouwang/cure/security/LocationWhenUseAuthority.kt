package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context

class LocationWhenUseAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.ACCESS_FINE_LOCATION)
}