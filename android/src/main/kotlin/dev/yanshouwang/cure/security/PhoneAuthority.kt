package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context
import android.os.Build

class PhoneAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() {
            val values = arrayOf(
                    Manifest.permission.ADD_VOICEMAIL,
                    Manifest.permission.CALL_PHONE,
                    Manifest.permission.READ_CALL_LOG,
                    Manifest.permission.READ_PHONE_STATE,
                    Manifest.permission.USE_SIP,
                    Manifest.permission.WRITE_CALL_LOG)
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                values.plus(Manifest.permission.ANSWER_PHONE_CALLS)
            }
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                values.plus(Manifest.permission.BIND_CALL_REDIRECTION_SERVICE)
            }
            return values
        }
}