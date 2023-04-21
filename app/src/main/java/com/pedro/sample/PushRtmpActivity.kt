package com.pedro.sample

import android.os.Bundle
import android.util.Log
import android.view.SurfaceHolder
import android.view.WindowManager
import android.widget.Button
import androidx.appcompat.app.AppCompatActivity
import com.pedro.encoder.input.video.CameraHelper
import com.pedro.rtmp.utils.ConnectCheckerRtmp
import com.pedro.rtplibrary.rtmp.RtmpCamera1
import com.pedro.rtplibrary.view.OpenGlView


class PushRtmpActivity : AppCompatActivity(), ConnectCheckerRtmp, SurfaceHolder.Callback {

    private lateinit var rtmpCamera1: RtmpCamera1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        window.addFlags(WindowManager.LayoutParams.FLAG_KEEP_SCREEN_ON)
        setContentView(R.layout.activity_camera_demo)

        val openGlView: OpenGlView = findViewById(R.id.surfaceView)

        rtmpCamera1 = RtmpCamera1(openGlView, this)
        rtmpCamera1.setLogs(false)
        openGlView.holder.addCallback(this)

        val buttonStart: Button = findViewById(R.id.b_start_stop)
        buttonStart.setOnClickListener {
            startStream()
        }
    }

    private fun startStream() {
        val rotation = CameraHelper.getCameraOrientation(this)
        if (rtmpCamera1.prepareAudio() && rtmpCamera1.prepareVideo(1280, 720, 18, 2 * 1024 * 1024, rotation)) {
            rtmpCamera1.startStream(Utils.getRTMPServer("push"))
        }
    }

    override fun onAuthErrorRtmp() {

    }

    override fun onAuthSuccessRtmp() {
    }

    override fun onConnectionFailedRtmp(reason: String) {
    }

    override fun onConnectionStartedRtmp(rtmpUrl: String) {
        Log.e("====pushUrl：", rtmpUrl)
        Log.e("====playUrl：", Utils.getWebRtcUrl())
    }

    override fun onConnectionSuccessRtmp() {
    }

    override fun onDisconnectRtmp() {
    }

    override fun onNewBitrateRtmp(bitrate: Long) {
    }

    override fun surfaceCreated(holder: SurfaceHolder) {
    }

    override fun surfaceChanged(holder: SurfaceHolder, format: Int, width: Int, height: Int) {
        rtmpCamera1.startPreview()
    }

    override fun surfaceDestroyed(holder: SurfaceHolder) {
        if (rtmpCamera1.isStreaming) {
            rtmpCamera1.stopStream()
        }
        rtmpCamera1.stopPreview()
    }


}