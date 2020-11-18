package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context

class StorageAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE)
}