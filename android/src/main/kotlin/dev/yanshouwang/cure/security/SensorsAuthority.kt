package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build
import androidx.annotation.RequiresApi

@RequiresApi(Build.VERSION_CODES.KITKAT_WATCH)
class SensorsAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(Manifest.permission.BODY_SENSORS)
}