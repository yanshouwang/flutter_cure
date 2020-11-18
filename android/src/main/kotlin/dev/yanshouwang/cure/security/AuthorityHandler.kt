package dev.yanshouwang.cure.security

import android.app.Activity
import android.content.Context
import android.content.pm.PackageManager
import android.os.Build
import androidx.core.app.ActivityCompat
import io.flutter.plugin.common.MethodCall
import io.flutter.plugin.common.MethodChannel.MethodCallHandler
import io.flutter.plugin.common.MethodChannel.Result
import io.flutter.plugin.common.PluginRegistry.RequestPermissionsResultListener

class AuthorityHandler(private val context: Context) : MethodCallHandler, RequestPermissionsResultListener {
    private val results: MutableMap<Int, Result> = mutableMapOf()
    var activity: Activity? = null

    override fun onMethodCall(call: MethodCall, result: Result) {
        when (call.method) {
            "checkAsync" -> this.check(call, result)
            "requestAsync" -> this.request(call, result)
            else -> result.notImplemented()
        }
    }

    private fun check(call: MethodCall, result: Result) {
        val key = call.arguments as Int
        val authority = this.getAuthority(key)
        val state = authority.check()
        result.success(state.ordinal)
    }

    private fun request(call: MethodCall, result: Result) {
        val key = call.arguments as Int
        if (this.results.containsKey(key))
            throw IllegalArgumentException("duplicated request: $key")
        val authority = this.getAuthority(key)
        this.results[key] = result
        authority.request(key)
    }

    private fun getAuthority(key: Int): IAuthority {
        return when (key) {
            0 -> CalendarAuthority(this.context, this.activity!!)
            1 -> CameraAuthority(this.context, this.activity!!)
            2 -> ContactsAuthority(this.context, this.activity!!)
            3 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
                    LocationAlwaysAuthority(this.context, this.activity!!)
                } else {
                    throw UnsupportedOperationException("VERSION.SDK_INT < Q")
                }
            }
            4 -> LocationWhenUseAuthority(this.context, this.activity!!)
            5 -> MicrophoneAuthority(this.context, this.activity!!)
            6 -> PhoneAuthority(this.context, this.activity!!)
            7 -> {
                if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT_WATCH) {
                    SensorsAuthority(this.context, this.activity!!)
                } else {
                    throw UnsupportedOperationException("VERSION.SDK_INT < KITKAT_WATCH")
                }
            }
            8 -> SMSAuthority(this.context, this.activity!!)
            9 -> StorageAuthority(this.context, this.activity!!)
            else -> throw IllegalArgumentException("invalid key: $key")
        }
    }

    override fun onRequestPermissionsResult(requestCode: Int, permissions: Array<out String>?, grantResults: IntArray?): Boolean {
        val result = this.results.remove(requestCode) ?: return false
        val state = dealWithResult(permissions!!, grantResults!!)
        result.success(state.ordinal)
        return true
    }

    private fun dealWithResult(permissions: Array<out String>, grantResults: IntArray): AuthorityState {
        if (permissions.isEmpty())
            throw IllegalArgumentException("empty arguments, this happens when you request authority before the last request complete")
        val results = grantResults.withIndex()
        for (result in results) {
            if (result.value != PackageManager.PERMISSION_GRANTED) {
                val value = permissions[result.index]
                return when {
                    ActivityCompat.shouldShowRequestPermissionRationale(this.activity!!, value) -> AuthorityState.Undetermined
                    else -> AuthorityState.Dined
                }
            }
        }
        return AuthorityState.Authorized
    }
}