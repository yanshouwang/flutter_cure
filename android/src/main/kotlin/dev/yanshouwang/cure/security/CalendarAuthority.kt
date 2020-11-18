package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context

class CalendarAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(
                Manifest.permission.READ_CALENDAR,
                Manifest.permission.WRITE_CALENDAR)
}