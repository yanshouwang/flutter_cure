package dev.yanshouwang.cure

import androidx.annotation.NonNull
import dev.yanshouwang.cure.security.AuthorityHandler
import io.flutter.embedding.engine.plugins.FlutterPlugin
import io.flutter.embedding.engine.plugins.activity.ActivityAware
import io.flutter.embedding.engine.plugins.activity.ActivityPluginBinding
import io.flutter.plugin.common.MethodChannel

/** CurePlugin */
class CurePlugin : FlutterPlugin, ActivityAware {
    companion object {
        const val AUTHORITY = "dev.yanshouwang.cure/authority"
    }

    /// The MethodChannel that will the communication between Flutter and native Android
    ///
    /// This local reference serves to register the plugin with the Flutter Engine and unregister it
    /// when the Flutter Engine is detached from the Activity
    private lateinit var authorityChannel: MethodChannel
    private lateinit var authorityHandler: AuthorityHandler

    private var binding: ActivityPluginBinding? = null
        set(value) {
            field?.removeRequestPermissionsResultListener(this.authorityHandler)
            field = value
            field?.addRequestPermissionsResultListener(this.authorityHandler)
            this.authorityHandler.activity = value?.activity
        }

    override fun onAttachedToEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        this.authorityChannel = MethodChannel(binding.binaryMessenger, AUTHORITY)
        this.authorityHandler = AuthorityHandler(binding.applicationContext)
        this.authorityChannel.setMethodCallHandler(authorityHandler)
    }

    override fun onDetachedFromEngine(@NonNull binding: FlutterPlugin.FlutterPluginBinding) {
        authorityChannel.setMethodCallHandler(null)
    }

    override fun onAttachedToActivity(binding: ActivityPluginBinding) {
        this.binding = binding
    }

    override fun onDetachedFromActivityForConfigChanges() {
        this.binding = null
    }

    override fun onReattachedToActivityForConfigChanges(binding: ActivityPluginBinding) {
        this.binding = binding
    }

    override fun onDetachedFromActivity() {
        this.binding = null
    }
}
