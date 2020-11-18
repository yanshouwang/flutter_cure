package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context

class SMSAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(
                Manifest.permission.READ_SMS,
                Manifest.permission.RECEIVE_MMS,
                Manifest.permission.RECEIVE_SMS,
                Manifest.permission.RECEIVE_WAP_PUSH,
                Manifest.permission.SEND_SMS)
}