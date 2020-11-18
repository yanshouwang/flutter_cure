package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context

class CameraAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(Manifest.permission.CAMERA)
}