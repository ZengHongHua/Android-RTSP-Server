package com.pedro.sample

import android.Manifest
import android.content.Context
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat

/**
 * babazhn zengHongHua
 */
class MainActivity : AppCompatActivity() {

    private val permission = arrayOf(
        Manifest.permission.RECORD_AUDIO, Manifest.permission.CAMERA,
        Manifest.permission.WRITE_EXTERNAL_STORAGE
    )


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_main)

        val button: Button = findViewById(R.id.b_camera_demo)
        button.setOnClickListener {
            if (!hasPermissions(this, *permission)) {
                ActivityCompat.requestPermissions(this, permission, 1)
            } else {
                startActivity(Intent(this, CameraDemoActivity::class.java))
            }
        }

        val rtmpBtn :Button= findViewById(R.id.b_push_rtmp)
        rtmpBtn.setOnClickListener {
            if (!hasPermissions(this, *permission)) {
                ActivityCompat.requestPermissions(this, permission, 1)
            } else {
                startActivity(Intent(this, PushRtmpActivity::class.java))
            }
        }
    }

    private fun hasPermissions(context: Context?, vararg permissions: String): Boolean {
        if (context != null) {
            for (permission in permissions) {
                if (ActivityCompat.checkSelfPermission(
                        context,
                        permission
                    ) != PackageManager.PERMISSION_GRANTED
                ) {
                    return false
                }
            }
        }
        return true
    }
}