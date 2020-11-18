package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.Q)
class LocationAlwaysAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(Manifest.permission.ACCESS_BACKGROUND_LOCATION)
}