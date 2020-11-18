package dev.yanshouwang.cure.security

import android.Manifest
import android.app.Activity
import android.content.Context

class ContactsAuthority(context: Context, activity: Activity) : Authority(context, activity) {
    override val values: Array<String>
        get() = arrayOf(
                Manifest.permission.GET_ACCOUNTS,
                Manifest.permission.READ_CONTACTS,
                Manifest.permission.WRITE_CONTACTS)
}