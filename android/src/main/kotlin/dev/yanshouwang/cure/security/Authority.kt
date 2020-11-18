package dev.yanshouwang.cure.security

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat

abstract class Authority(private val context: Context, private val activity: Activity) : IAuthority {
    protected abstract val values: Array<String>

    private fun throwWhenUndeclared() {
        val packageName = context.packageName
        val flag = PackageManager.GET_PERMISSIONS
        val declared = context.packageManager.getPackageInfo(packageName, flag).requestedPermissions.toList()
        val undeclared = this.values.subtract(declared)
        if (undeclared.any()) {
            val message = "the following values are undeclared in AndroidManifest.xml: ${undeclared.joinToString()}"
            throw SecurityException(message)
        }
    }

    override fun check(): AuthorityState {
        throwWhenUndeclared()
        for (value in this.values) {
            val result = ContextCompat.checkSelfPermission(this.context, value)
            if (result != PackageManager.PERMISSION_DENIED)
                return AuthorityState.Dined
        }
        return AuthorityState.Authorized
    }

    override fun request(requestCode: Int) {
        throwWhenUndeclared()
        ActivityCompat.requestPermissions(this.activity, this.values, requestCode)
    }
}